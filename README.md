# blogstore
Yao Lu's Blog

Hi, Nice to meet you!

## Bilingual posts

English is the default language and keeps the existing URLs. Simplified Chinese
pages are generated under `/zh-cn/`. Use the language selector to switch between
translations. If a post has no translation, the selector links to that language's
home page.

Save both versions with the same base filename:

```text
content/post/example.en.md
content/post/example.zh-cn.md
```

Existing `.md` files without a language suffix belong to the English site. Hugo
links matching translations but does not translate article text automatically.

Keep `draft: true` while editing. Preview both versions with `hugo server -D`.
To publish a translation, set `draft: false` in that file.

## Build and deployment checks

Pull requests to `master` build the production site and check both language home
pages, language selector links, and draft exclusion. Only pushes to `master` and
manual runs on `master` publish to GitHub Pages. README, editor settings, and local
helper script changes alone do not trigger a push deployment.

CI builds into a new temporary directory and uploads only that directory. The
legacy checked-in `public/` directory is not used as the deployment artifact.
Hugo stays pinned to 0.128.0 Extended to match the current theme.
