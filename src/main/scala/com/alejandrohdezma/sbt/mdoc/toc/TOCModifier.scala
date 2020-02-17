package com.alejandrohdezma.sbt.mdoc.toc

import scala.meta.inputs.Input

import mdoc.{PostModifier, PostModifierContext}

class TOCModifier extends PostModifier {

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
