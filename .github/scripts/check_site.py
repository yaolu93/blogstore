"""Check production HTML before uploading the Pages artifact (stdlib only)."""

import csv
from html.parser import HTMLParser
from pathlib import Path
import sys
from urllib.parse import unquote, urljoin, urlsplit


class LanguageLinks(HTMLParser):
    def __init__(self):
        super().__init__()
        self.links = []

    def handle_starttag(self, tag, attrs):
        attrs = dict(attrs)
        if tag == "a" and "hreflang" in attrs:
            self.links.append((attrs["hreflang"], attrs.get("href", "")))


def check_site(output, base_url, inventory):
    base = urlsplit(base_url)
    prefix = base.path.rstrip("/") + "/"

    def output_file(href):
        url = urlsplit(urljoin(base_url, href))
        if (url.scheme, url.netloc) != (base.scheme, base.netloc):
            raise ValueError(f"Language link points outside this site: {href}")
        if not url.path.startswith(prefix):
            raise ValueError(f"URL is missing the site prefix {prefix}: {href}")
        target = output / unquote(url.path[len(prefix):])
        if url.path.endswith("/"):
            target /= "index.html"
        if not target.resolve().is_relative_to(output.resolve()):
            raise ValueError(f"URL points outside the output directory: {href}")
        return target

    homes = {"en": output / "index.html", "zh-CN": output / "zh-cn/index.html"}
    for language, home in homes.items():
        if not home.is_file():
            raise ValueError(f"Missing {language} home page: {home}")
        parser = LanguageLinks()
        parser.feed(home.read_text(encoding="utf-8"))
        for target_language, target_home in homes.items():
            if not any(
                lang == target_language and href and output_file(href) == target_home
                for lang, href in parser.links
            ):
                raise ValueError(f"Missing {target_language} home link on {home}")

    links_checked = 0
    for page in output.rglob("*.html"):
        parser = LanguageLinks()
        parser.feed(page.read_text(encoding="utf-8"))
        for language, href in parser.links:
            if not href or not output_file(href).is_file():
                raise ValueError(f"Broken {language} language link on {page}: {href}")
            links_checked += 1

    drafts_checked = 0
    with inventory.open(encoding="utf-8", newline="") as handle:
        for row in csv.DictReader(handle):
            if row["draft"].lower() == "true":
                if output_file(row["permalink"]).exists():
                    raise ValueError(f"Draft was included in production: {row['path']}")
                drafts_checked += 1

    print(f"Checked both home pages, {links_checked} language links, and {drafts_checked} drafts.")


if __name__ == "__main__":
    if len(sys.argv) != 4:
        sys.exit("Usage: check_site.py OUTPUT_DIR BASE_URL CONTENT_INVENTORY.csv")
    try:
        check_site(Path(sys.argv[1]), sys.argv[2], Path(sys.argv[3]))
    except (ValueError, OSError, KeyError) as error:
        sys.exit(f"Site check failed: {error}")
