scalaVersion := "2.13.3"

lazy val `play-sample` = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    libraryDependencies ++= Seq(
      // for play2
      guice, // for Dependencies Injection
      jdbc,
      evolutions, // for DB
      filters     // for CORS
    )++ Seq(
      // for test
      "org.scalatestplus.play" %% "scalatestplus-play" % scalatestPlayVersion % "test",
      "org.mockito" % "mockito-all" % "1.10.19" % Test,
      "org.mockito" % "mockito-core" % "3.4.6" % Test
    )
  )

lazy val scalatestPlayVersion = "5.1.0"
