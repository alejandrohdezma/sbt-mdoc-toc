/*
 * Copyright 2020 Alejandro Hernández <https://github.com/alejandrohdezma>
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

import mdoc.{PostModifier, PostModifierContext}

import scala.meta.inputs.Input

class TOCGenerator extends PostModifier {

  override val name = "toc"

  override def process(ctx: PostModifierContext): String = {
    ctx.originalCode match {
      case Input.Slice(Input.VirtualFile(_, text), _, _) => generateTOC(text)
      case _ =>
        ctx.reporter.error("Unable to get file")
        ""
    }
  }

  @SuppressWarnings(Array("scalafix:DisableSyntax.!="))
  private def generateTOC(text: String): String =
    text.linesIterator
      .filter(_.startsWith("##"))
      .map(_.span(_ != ' '))
      .map { case (h, header) => h.length -> header.drop(1) }
      .map { case (h, header) => (h, header, anchor(header)) }
      .map { case (h, header, anchor) => s"${"  " * (h - 2)}- [$header](#$anchor)" }
      .mkString("---\n\n", "\n", "\n")

  private def anchor(header: String): String =
    header
      .replaceAll(" ", "-")
      .replaceAll("""[/?!:\[\]`.,()*"';{}+=<>~$|#@&–—]""", "")
      .replaceAll("[。？！，、；：“”【】（）〔〕［］﹃﹄‘’﹁﹂—…－～《》〈〉「」]", "")
      .toLowerCase
}
