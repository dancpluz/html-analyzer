# 📚 HTML Analyzer

> A Java command-line tool designed to parse an HTML document from a given URL and find the deepest text snippet within its structure. This project leverages fundamental data structures like stacks and N-ary trees to efficiently navigate and analyze the HTML hierarchy.

<picture>
  <img src="tree.gif" align="right" width="20%" alt="A GIF illustrating the concept of a tree data structure, with nodes branching out from a root, and a specific path being highlighted, representing the 'deepest' text snippet." />
</picture>

## 🚀 About

This project was a great challenge that allowed me to deepen my understanding of core computer science concepts. The goal was to build a robust Java application that could fetch and parse a simplified HTML structure from any given URL and identify the text located at the highest nesting level.

The solution was built from the ground up, focusing on a solid architectural foundation using two key data structures: the N-ary Tree and the Stack. This approach made it possible to represent the hierarchical nature of HTML and perform an elegant depth-first search to find the required text.

## 📌 Features

- **Deepest Text Snippet**: Accurately identifies and returns the text snippet at the deepest level of the HTML document.
- **URL Fetching**: Connects to and retrieves HTML content from any given URL.
- **Error Handling**: Provides clear console messages for connection failures or malformed HTML structures.
- **Lightweight**: A command-line tool with a minimal footprint.

## 🛠 Built With

![Java](https://skillicons.dev/icons?i=java)

### Key Data Structures

#### N-Ary Tree

The N-ary Tree was the perfect choice for modeling the HTML DOM structure. HTML is inherently a tree: it starts with a single root (`<html>`) and branches out with nested tags. By representing the document as a tree, a depth-first search (DFS) algorithm could be easily implemented to navigate to the deepest leaf node, which in our case corresponds to the deepest text snippet.

#### Stack

The Stack was instrumental in both building the N-ary Tree and validating the HTML structure. Its LIFO (Last-In, First-Out) nature is a natural fit for processing opening and closing HTML tags. As the program reads each line, it uses the stack to keep track of the current nesting level, making it easy to connect child nodes to their parent tags and detect any mismatched or malformed tags.

## 👨‍💻 How to Run

### Prerequisites

-   [**Java JDK 17**](https://www.oracle.com/java/technologies/downloads/#jdk17-windows) or newer installed on your system.

### Running the application

1.  **Clone the repository**

```bash
git clone https://github.com/dancpluz/HtmlAnalyzer
````

2.  **Navigate to the project directory**

<!-- end list -->

```bash
cd HtmlAnalyzer
```

3.  **Compile the Java source code**

<!-- end list -->

```bash
javac HtmlAnalyzer.java
```

4.  **Run the application**

<!-- end list -->

```bash
java HtmlAnalyzer https://www.example.com
```

> **Note**: Replace `https://www.example.com` with the URL of your choice.

## 👥 Group / Author(s)

  - **[Daniel Luz](https://github.com/dancpluz)**
