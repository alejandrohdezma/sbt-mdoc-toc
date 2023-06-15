/*
 * Copyright 2020-2023 Alejandro Hernández <https://github.com/alejandrohdezma>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alejandrohdezma.mdoc.toc.generator

class TableOfContentsModifierSuite extends munit.FunSuite {

  test("Generates table of contents for markdown file") {
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
        |### [`something`](https://example.com)
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
        |  - [`something`](#something)
        |- [4](#4)
        |- [5](#5)
        |  - [5.1](#51)
        |    - [5.1.1](#511)
        |""".stripMargin

    assertEquals(toc, expected)
  }

}
