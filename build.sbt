ThisBuild / scalaVersion := "2.12.10"
ThisBuild / organization := "com.alejandrohdezma"

Global / onChangedBuildSource := ReloadOnSourceChanges

addCommandAlias("ci-test", "fix --check; mdoc")
addCommandAlias("ci-docs", "mdoc; headerCreateAll")

lazy val `root` = project
  .in(file("."))
  .settings(name := "sbt-mdoc-toc")
  .aggregate(`sbt-mdoc-toc`, `mdoc-toc-generator`)
  .dependsOn(`mdoc-toc-generator`)
  .enablePlugins(MdocPlugin)
  .settings(mdocOut := file("."))

lazy val `sbt-mdoc-toc` = project
  .enablePlugins(SbtPlugin)
  .dependsOn(`mdoc-toc-generator`)
  .settings(addSbtPlugin("org.scalameta" % "sbt-mdoc" % "[2.0,)" % Provided))

lazy val `mdoc-toc-generator` = project
  .enablePlugins(BuildInfoPlugin)
  .settings(buildInfoPackage := "com.alejandrohdezma.mdoc.toc.generator")
  .settings(libraryDependencies += "org.scalameta" %% "mdoc" % "[2.0,)" % Provided)
