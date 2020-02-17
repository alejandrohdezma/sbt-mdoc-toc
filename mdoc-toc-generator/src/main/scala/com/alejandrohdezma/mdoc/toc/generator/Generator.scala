package com.alejandrohdezma.mdoc.toc.generator

object Generator {

  def toc(text: String): String =
    text.split('\n').filter(_.startsWith("##")).map {
      case H2(header) => s"- [$header](#${link(header)})"
      case H3(header) => s"  - [$header](#${link(header)})"
      case H4(header) => s"    - [$header](#${link(header)})"
      case H5(header) => s"      - [$header](#${link(header)})"
      case H6(header) => s"        - [$header](#${link(header)})"
      case H7(header) => s"          - [$header](#${link(header)})"
    } mkString ("---\n\n", "\n", "\n")

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

}
