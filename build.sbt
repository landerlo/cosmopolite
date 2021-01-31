val scala3Version = "3.0.0-M2"

lazy val root = project
  .in(file("."))
  .settings(
    name := "cosmopolite",
    version := "0.1.0",

    scalaVersion := scala3Version,
  )
