# `mdoc` table-of-contents generator

[![][github-action-badge]][github-action] [![][maven-badge]][maven] [![][steward-badge]][steward] 

This plugin enables generating table of contents for markdown files using [`mdoc`](https://scalameta.org/mdoc/) by adding the following code:

````markdown
```scala mdoc:toc
```
````
  
```scala mdoc:toc
```

## Installation

Add the following line to your `plugins.sbt` file:

```sbt
addSbtPlugin("com.alejandrohdezma" %% "sbt-mdoc-toc" % "@VERSION@")
```

[github-action]: https://github.com/alejandrohdezma/sbt-mdoc-toc/actions
[github-action-badge]: https://img.shields.io/endpoint.svg?url=https%3A%2F%2Factions-badge.atrox.dev%2Falejandrohdezma%2Fsbt-mdoc-toc%2Fbadge%3Fref%3Dmaster&style=flat

[maven]: https://search.maven.org/search?q=g:%20com.alejandrohdezma%20AND%20a:sbt-mdoc-toc
[maven-badge]: https://maven-badges.herokuapp.com/maven-central/com.alejandrohdezma/sbt-mdoc-toc/badge.svg?kill_cache=1

[steward]: https://scala-steward.org
[steward-badge]: https://img.shields.io/badge/Scala_Steward-helping-brightgreen.svg?style=flat&logo=data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAA4AAAAQCAMAAAARSr4IAAAAVFBMVEUAAACHjojlOy5NWlrKzcYRKjGFjIbp293YycuLa3pYY2LSqql4f3pCUFTgSjNodYRmcXUsPD/NTTbjRS+2jomhgnzNc223cGvZS0HaSD0XLjbaSjElhIr+AAAAAXRSTlMAQObYZgAAAHlJREFUCNdNyosOwyAIhWHAQS1Vt7a77/3fcxxdmv0xwmckutAR1nkm4ggbyEcg/wWmlGLDAA3oL50xi6fk5ffZ3E2E3QfZDCcCN2YtbEWZt+Drc6u6rlqv7Uk0LdKqqr5rk2UCRXOk0vmQKGfc94nOJyQjouF9H/wCc9gECEYfONoAAAAASUVORK5CYII=
