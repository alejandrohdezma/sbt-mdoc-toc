ThisBuild / scalaVersion                  := "3.8.1"
ThisBuild / crossScalaVersions            := List("3.8.1", "2.12.21")
ThisBuild / organization                  := "com.alejandrohdezma"
ThisBuild / pluginCrossBuild / sbtVersion := scalaBinaryVersion.value.on(2)("1.2.8").getOrElse("2.0.0-RC9")
ThisBuild / versionPolicyIntention        := Compatibility.None

addCommandAlias("ci-test", "fix --check; versionPolicyCheck; mdoc; +test; +publishLocal; +sbt-mdoc-toc/scripted")
addCommandAlias("ci-docs", "github; mdoc; headerCreateAll")
addCommandAlias("ci-publish", "versionCheck; github; ci-release")

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
  .settings(scriptedSbt := (pluginCrossBuild / sbtVersion).value)

lazy val `mdoc-toc-generator` = module
  .enablePlugins(BuildInfoPlugin)
  .settings(crossScalaVersions := Seq("2.12.21", "2.13.18", "3.3.7"))
  .settings(buildInfoPackage := "com.alejandrohdezma.mdoc.toc.generator")
  .settings(libraryDependencies += mdoc)
  .settings(libraryDependencies += "org.scalameta" %% "munit" % "1.2.2" % Test)
