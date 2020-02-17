package com.alejandrohdezma.sbt.mdoc.toc

import sbt.Keys._
import sbt._

import com.alejandrohdezma.mdoc.toc.generator.BuildInfo
import mdoc.MdocPlugin

object SbtMdocTocPlugin extends AutoPlugin {

  override def trigger: PluginTrigger = allRequirements

  override def requires: Plugins = MdocPlugin

  override def projectSettings: Seq[Def.Setting[_]] = Seq(
    libraryDependencies += "com.alejandrohdezma" %% "mdoc-toc-generator" % BuildInfo.version
  )

}
