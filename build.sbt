ThisBuild / scalaVersion := "2.12.10"
ThisBuild / organization := "com.alejandrohdezma"

Global / onChangedBuildSource := ReloadOnSourceChanges

addCommandAlias("ci-test", "fix --check; mdoc")
addCommandAlias("ci-docs", "mdoc; headerCreateAll")

lazy val `sbt-mdoc-toc` = project
  .in(file("."))
  .enablePlugins(MdocPlugin, SbtPlugin)
  .settings(mdocOut := file("."))
  .settings(libraryDependencies += "org.scalameta" %% "mdoc" % "2.1.1")
