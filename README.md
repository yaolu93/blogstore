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
