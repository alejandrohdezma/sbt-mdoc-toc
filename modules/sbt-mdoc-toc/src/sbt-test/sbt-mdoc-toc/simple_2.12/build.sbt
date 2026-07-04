ThisBuild / scalaVersion := "2.12.21"

lazy val root = project
  .in(file("."))
  .enablePlugins(MdocPlugin)
  .settings(mdocOut := baseDirectory.value / "target" / "mdoc")
