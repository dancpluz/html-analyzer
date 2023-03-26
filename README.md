EASTER_EGG_URLS

# Code Challenge for Axur Software Development Internship

## Objective

Develop a java program that gets the deepest HTML text snippet of an URL

## Considerations

1. HTML code divided by lines;
2. Each line can be of only one of the following types:
- Opening tag (example: `<div>`)
- Closing tag (example: `</div>`)
- Text snippet (example: `"This is the body."`)
3. The same line cannot contain two types of content;
4. Only HTML elements with opening and closing tag pairs are used (example: `<div>` and `</div>`, but not `<br/>`)
5. Opening tags have no attributes (counterexample: `<a href="link.html">`).

## Output

The program should only generate the following types of output on the console:

a. Line of text snippet identified in the HTML; or \
b. A "malformed HTML" message (if implemented feature that is worth bonus points); or \
c. URL connection error" message (if it is not possible HTML content due to connection failure). 

## Result

I used two major data structures to solve the challenge:
1. N-Ary Tree
2. Stack

### N-Ary Tree
I used the tree structure because it's exactly how HTML is designed, it starts with a root (`<html>`) and branches with the use of opening and closing tags. The structure made possible the usage of a depth search algorithm, which was used to determine the deepest text snippet as asked.

### Stack
The stack structure was useful for both creating a complete tree from the html fetched and checking for malformed HTML. I used stack because of the opening and closing structure of HTML files, so with every line considered as a branch I was able to connect branches according to the tag types.

## Usage
> Made with Java JDK 17

Run this command with your url of choice:

`java HtmlAnalyzer insert-url-here`
