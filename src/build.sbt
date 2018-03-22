name := "ad"

version := "1.0"

scalaVersion := "2.12.4"

// cps

autoCompilerPlugins := true

addCompilerPlugin("org.scala-lang.plugins" % "scala-continuations-plugin_2.12.0" % "1.0.3")

libraryDependencies += "org.scala-lang.plugins" % "scala-continuations-library_2.12" % "1.0.3"

scalacOptions += "-P:continuations:enable"

// lms

//scalaOrganization := "org.scala-lang.virtualized"

//scalacOptions += "-Yvirtualize"

resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies += "org.scala-lang.lms" %% "lms-core-macrovirt" % "0.9.0-SNAPSHOT"

libraryDependencies += "org.scala-lang" % "scala-compiler" % scalaVersion.value % "compile"

libraryDependencies += "org.scala-lang" % "scala-library" % scalaVersion.value % "compile"

libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVersion.value % "compile"

val paradiseVersion = "2.1.0"

addCompilerPlugin("org.scalamacros" % "paradise" % paradiseVersion cross CrossVersion.full)


// libraryDependencies += "org.scala-lang.lms" %% "lms-core" % "1.0.0-SNAPSHOT"

// libraryDependencies += "org.scala-lang.virtualized" % "scala-compiler" % "2.11.2"

// libraryDependencies += "org.scala-lang.virtualized" % "scala-library" % "2.11.2"

// libraryDependencies += "org.scala-lang.virtualized" % "scala-reflect" % "2.11.2"