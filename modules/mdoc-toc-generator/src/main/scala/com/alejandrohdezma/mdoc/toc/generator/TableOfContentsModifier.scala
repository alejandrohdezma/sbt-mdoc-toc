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

import scala.meta.inputs.Input

import mdoc.PostModifier
import mdoc.PostModifierContext

/** Parses markdown files and generates its table of contents when a `mdoc:toc` scala code block is encountered. */
class TableOfContentsModifier extends PostModifier {

  override val name = "toc"

  override def process(ctx: PostModifierContext): String =
    ctx.originalCode match {
      case Input.Slice(Input.VirtualFile(_, text), _, _) => Generator.toc(text)
      case _                                             => ""
    }

}
