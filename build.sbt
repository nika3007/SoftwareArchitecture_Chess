val scala3Version = "3.7.3"

lazy val root = project
  .in(file("."))
  .settings(
    name := "chess",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies += "org.scalafx" %% "scalafx" % "23.0.1-R34",

    libraryDependencies += "net.codingwell" %% "scala-guice" % "7.0.0",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.18" % Test,

    scalacOptions ++= Seq(
      "-deprecation",
      "-feature",
      "-unchecked",
      "-Wconf:msg=Implicit parameters should be provided with a `using` clause:s"
    ),

    run / fork := false,

    assembly / mainClass := Some("Chess"),
    assembly / assemblyMergeStrategy := {
      case PathList("module-info.class") => MergeStrategy.discard
      case PathList("META-INF", xs @ _*) => MergeStrategy.discard
      case x => MergeStrategy.first
    },

    coverageEnabled := true,
    coverageMinimumStmtTotal := 50,
    coverageFailOnMinimum := false,
  )
