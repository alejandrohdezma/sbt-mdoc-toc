/*
 * Copyright 2020-2021 Alejandro Hernández <https://github.com/alejandrohdezma>
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

object Generator {

  def toc(text: String): String =
    text
      .split('\n')
      .filter(_.startsWith("##"))
      .map {
        case H2(normalize(header)) => s"- [$header](#${link(header)})"
        case H3(normalize(header)) => s"  - [$header](#${link(header)})"
        case H4(normalize(header)) => s"    - [$header](#${link(header)})"
        case H5(normalize(header)) => s"      - [$header](#${link(header)})"
        case H6(normalize(header)) => s"        - [$header](#${link(header)})"
        case H7(normalize(header)) => s"          - [$header](#${link(header)})"
        case _                     => ""
      }
      .filter(_.nonEmpty)
      .mkString("---\n\n", "\n", "\n")

  def link(header: String): String =
    header
      .replaceAll(" ", "-")
      .replaceAll("""[/?!:\[\]`.,()*"';{}+=<>~$|#@&–—]""", "")
      .replaceAll("[。？！，、；：“”【】（）〔〕［］﹃﹄‘’﹁﹂—…－～《》〈〉「」]", "")
      .toLowerCase

  private val H2 = s"## (.*)".r

  private val H3 = s"### (.*)".r

  private val H4 = s"#### (.*)".r

  private val H5 = s"##### (.*)".r

  private val H6 = s"###### (.*)".r

  private val H7 = s"####### (.*)".r

  object normalize {

    val linkRegex = """\[(.*)\]\(.*\)""".r

    def unapply(header: String): Option[String] = header match {
      case linkRegex(linkName) => unapply(linkName)
      case _                   => Some(header)
    }

  }

}
