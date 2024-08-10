# `mdoc` table-of-contents generator

This plugin enables generating a table of contents for markdown files using [`mdoc`](https://scalameta.org/mdoc/) by adding the following code:

````markdown
```scala mdoc:toc
```
````

## Installation

Add the following line to your `plugins.sbt` file:

```sbt
addSbtPlugin("com.alejandrohdezma" % "sbt-mdoc-toc" % "0.4.2")
```

## Usage

Just add the following where you want to add the table of contents in your markdown file:

````markdown
```scala mdoc:toc
```
````

And run `sbt mdoc`.
