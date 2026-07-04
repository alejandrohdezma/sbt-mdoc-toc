ThisBuild / scalaVersion := "3.3.8"

lazy val root = project
  .in(file("."))
  .enablePlugins(MdocPlugin)
  .settings(mdocOut := baseDirectory.value / "target" / "mdoc")
