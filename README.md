EASTER_EGG_URLS

# Code Challenge for Axur Software Development Internship

## Objective

Develop a Java program that gets the deepest HTML text snippet of an URL

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
c. URL connection error" message (if it is not possible to get HTML content due to connection failure). 

## Result

I used two major data structures to solve the challenge:
1. N-Ary Tree
2. Stack

### N-Ary Tree
I used the non-binary tree structure because it's exactly how HTML is designed, it starts with a root (`<html>`) and branches with the use of opening and closing tags. The structure made possible the usage of a depth search algorithm, which was used to determine the deepest text snippet as asked.

### Stack
The stack structure was useful for both creating a complete tree from the html fetched and checking for malformed HTML. I used stack because of the opening and closing structure of HTML files, so with every line considered as a branch I was able to connect branches according to the tag types.

### Explanation

The code consists in fetching the HTML from an URL, using Java's native HTTP client, reading each line with a buffered reader, and making a tree for every line, but adding to a stack according to conditions. These conditions are:

> "root" is the current tree being used \
> "top tree" is the tree at the top of the stack

- If it's only text, add the text as children of the root.
- If it's an opening tag, add the root to the stack, create a new tree with the tag and change it to root.
- If it's a closing tag, add the root as children of the top tree, change the root to the top tree and remove it from the stack.

There's also a stack of past tags to make sure every tag is being opened and closed according to HTML syntax, using some conditions I was able to detect the code malformation.

## Usage
> Made with Java JDK 17

Run this command with your url of choice:

`java HtmlAnalyzer insert-url-here`
