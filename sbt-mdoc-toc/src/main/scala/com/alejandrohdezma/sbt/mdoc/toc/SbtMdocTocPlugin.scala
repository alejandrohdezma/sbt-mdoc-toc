/*
 * Copyright 2020-2021 Alejandro Hernández <https://github.com/alejandrohdezma>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alejandrohdezma.sbt.mdoc.toc

import sbt.Keys._
import sbt._

import com.alejandrohdezma.mdoc.toc.generator.BuildInfo
import mdoc.MdocPlugin

/** Automatically adds a dependency with current version of `mdoc-toc-generator` to
  * any project that has enabled the `MdocPlugin`.
  */
object SbtMdocTocPlugin extends AutoPlugin {

  override def trigger: PluginTrigger = allRequirements

  override def requires: Plugins = MdocPlugin

  override def projectSettings: Seq[Def.Setting[_]] =
    Seq(
      libraryDependencies += "com.alejandrohdezma" %% "mdoc-toc-generator" % BuildInfo.version
    )

}
