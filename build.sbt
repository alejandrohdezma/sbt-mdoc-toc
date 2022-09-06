ThisBuild / scalaVersion                  := "2.12.16"
ThisBuild / organization                  := "com.alejandrohdezma"
ThisBuild / pluginCrossBuild / sbtVersion := "1.2.8"

addCommandAlias("ci-test", "scalafmtCheckAll; scalafmtSbtCheck; mdoc; +test; +publishLocal; scripted")
addCommandAlias("ci-docs", "github; mdoc; headerCreateAll")
addCommandAlias("ci-publish", "github; ci-release")

//These are the only external dependencies
val `sbt-mdoc` = "org.scalameta" % "sbt-mdoc" % "[2.0,)" % Provided // scala-steward:off

val mdoc = "org.scalameta" %% "mdoc" % "[2.0,)" % Provided // scala-steward:off

lazy val `documentation` = project
  .enablePlugins(MdocPlugin)
  .dependsOn(`mdoc-toc-generator`)
  .settings(mdocOut := file("."))

lazy val `sbt-mdoc-toc` = module
  .enablePlugins(SbtPlugin)
  .dependsOn(`mdoc-toc-generator`)
  .settings(publishLocal := publishLocal.dependsOn(`mdoc-toc-generator` / publishLocal).value)
  .settings(scriptedLaunchOpts += s"-Dplugin.version=${version.value}")
  .settings(addSbtPlugin(`sbt-mdoc`))

lazy val `mdoc-toc-generator` = module
  .enablePlugins(BuildInfoPlugin)
  .settings(crossScalaVersions := Seq("2.12.16", "2.13.8", "3.2.0"))
  .settings(buildInfoPackage := "com.alejandrohdezma.mdoc.toc.generator")
  .settings(libraryDependencies += mdoc)
  .settings(libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test)
