val scala3Version = "3.7.3"

lazy val root = project
  .in(file("."))
  .settings(
    name := "chess",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    // 1. ScalaFX für die GUI
    libraryDependencies += "org.scalafx" %% "scalafx" % "20.0.0-R31",

    // 2. Google Guice für Dependency Injection
    libraryDependencies += "net.codingwell" %% "scala-guice" % "7.0.0",

    // 3. Tests
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.18" % Test,
    //Test / javaOptions += "-Dtest.env=true",

    // Compiler-Optionen
    scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked", "-Wconf:msg=Implicit parameters should be provided with a `using` clause:s"),

    // Fork für JavaFX erforderlich
    run / fork := false,

    // 4. sbt Scoverage Settings
    coverageEnabled := true,
    coverageMinimumStmtTotal := 50,
    coverageFailOnMinimum := false,
  )