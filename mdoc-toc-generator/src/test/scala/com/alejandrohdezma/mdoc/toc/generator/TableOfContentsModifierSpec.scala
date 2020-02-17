package com.alejandrohdezma.mdoc.toc.generator

import org.specs2.mutable.Specification

class TableOfContentsModifierSpec extends Specification {

  "Generates table of contents for markdown file" >> {
    val markdown =
      """# The title
        |
        |## 1
        |### 1.1
        |### 1.2
        |## 2
        |## 3
        |### 3.1
        |#### 3.1.1
        |##### 3.1.1.1
        |###### 3.1.1.1.1
        |####### 3.1.1.1.1.1
        |## 4
        |## 5
        |### 5.1
        |#### 5.1.1
        |""".stripMargin

    val toc = Generator.toc(markdown)

    val expected =
      """---
        |
        |- [1](#1)
        |  - [1.1](#11)
        |  - [1.2](#12)
        |- [2](#2)
        |- [3](#3)
        |  - [3.1](#31)
        |    - [3.1.1](#311)
        |      - [3.1.1.1](#3111)
        |        - [3.1.1.1.1](#31111)
        |          - [3.1.1.1.1.1](#311111)
        |- [4](#4)
        |- [5](#5)
        |  - [5.1](#51)
        |    - [5.1.1](#511)
        |""".stripMargin

    toc must be equalTo expected
  }

}
