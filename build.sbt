name := "scala_text_app"

version := "0.1"

scalaVersion := "2.13.3"

lazy val `scala_text_app` = project in file(".")

aggregateProjects(
  RootProject(file("examples/exchange-image")),
  RootProject(file("examples/play-sample"))
)
