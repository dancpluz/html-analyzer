EASTER_EGG_URLS

# Code Challenge for Axur Software Development Intern

## Objective

Get the deepest HTML structure of an URL

## Considerations

1. HTML code divided by lines;
2. Each line can be of only one of the following types:
    a. Opening tag (example: <div>)
    b. Closing tag (example: </div>)
    c. Text snippet (example: "This is the body.")
3. The same line cannot contain two types of content;
4. Only HTML elements with opening and closing tag pairs are used (example: <div> and </div>, but not <br/>)
5. Opening tags have no attributes (counterexample: <a href=”link.html”>).

## Output

The program should only generate the following types of output on the console:
a. Line of text snippet identified in the HTML; or
b. A "malformed HTML" message (if implemented feature that is worth bonus points); or
c. URL connection error" message (if it is not possible HTML content due to connection failure).

## 