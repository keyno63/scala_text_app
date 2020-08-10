scalaVersion := "2.13.3"

lazy val `exchange-image` = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := "exchange-image",
    libraryDependencies ++= Seq(
      // for play2
      guice, // for Dependencies Injection
      jdbc,
      evolutions, // for DB
      filters     // for CORS
    )++ Seq(
      // for test
      "org.scalatestplus.play" %% "scalatestplus-play" % scalatestPlayVersion % Test,
      "org.mockito" % "mockito-core" % mockitoVersion % Test,
      "org.mockito" %% "mockito-scala" % mockitoScalaVersion % Test,
      "org.mockito" %% "mockito-scala-scalatest" % mockitoScalaVersion % Test
    )
  )

lazy val scalatestPlayVersion = "5.1.0"
lazy val mockitoVersion = "3.4.6"
lazy val mockitoScalaVersion = "1.5.17"
