ThisBuild / scalaVersion := "2.13.18"

lazy val root = project
  .in(file("."))
  .enablePlugins(MdocPlugin)
  .settings(mdocOut := baseDirectory.value / "target" / "mdoc")
