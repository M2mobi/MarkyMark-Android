# Headers

---

# H1
## H2
### H3
#### H4
##### H5
###### H6
### __*bold & italic*__

# Lists

---

## Ordered list

1. Number 1
2. Number 2
3. Number 3
4. Number 4
  1. Nested 1
  2. Nested 2
5. Number 5
6. Number 6
7. Number 7
8. Number 8
9. Number 9
10. Number 10
11. Number 11


## Unordered list

- Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
- Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
- Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.

## Unordered list with nesting

- Item 1
- Item 2
- Item 3
  - Nested item 1
  - ~~Nested item 2~~
    - Subnested item 1
    - Subnested item 2
      - Subsubnested item 1
      - Subsubnested item 2 [Velit](https://m2mobi.com)
      - Subsubnested item 3 *(with single **space**)*
        - Subsubsubnested item 1
        - Subsubsubnested item 2
        - Subsubsubnested item 3
- Item 4
  - Nested item 1
  - Nested item 2

## Combo

1. Ordered 1
2. Ordered 2
  - Unordered 1
  - Unordered 2
    - Nested unordered 1
    - Nested unordered 2
      1. Subnested ordered 1
      2. Subnested ordered 2

# Paragraphs

---

## Quotes
> Markdown is *awesome*
> Seriously..

## Links

[This is a test link](https://m2mobi.com)
Inline links are also possible, click [here](https://m2mobi.com)
Phone numbers as well [+06-23459392](tel:06-23459392)

## Code

inline code `TextView textView = new TextView(pContext);`

### code block

```
@Override
public View getMarkdownView(Context pContext) {
    TextView textView = new TextView(pContext);
    textView.setText(Html.fromHtml(mContent));
    textView.setTextSize(10);
    return textView;
}
```

# Styled text
This is __bold__, this is *italic*, this is ~~striked out~~, this is everything __~~*combined*~~__.
Special html symbols: `&euro; &copy;` become -> &euro; &copy;

# Media

---

![800px-Android 5.0-en](https://images.contentful.com/7hqxd0h2npta/16JgtcyfEIeakoOqqKqiC/f80f6b559228a17c50ef52a064df047c/800px-Android_5.0-en.png)
