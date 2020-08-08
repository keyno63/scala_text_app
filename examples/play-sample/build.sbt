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
    ) ++ Seq(
      // for test
      "org.scalatestplus.play" %% "scalatestplus-play" % scalatestPlayVersion % "test"
    ) ++
      scalikeJdbcModules
  )

lazy val scalatestPlayVersion          = "5.1.0"
lazy val h2DriverVersion               = "1.4.200"
lazy val scalikejdbcVersion            = "3.5.0"
lazy val scalikejdbcInitializerVersion = "2.8.0-scalikejdbc-3.5"
lazy val scalikeJdbcModules = Seq(
  "com.h2database"  % "h2"                            % h2DriverVersion,
  "org.scalikejdbc" %% "scalikejdbc"                  % scalikejdbcVersion,
  "org.scalikejdbc" %% "scalikejdbc-config"           % scalikejdbcVersion,
  "org.scalikejdbc" %% "scalikejdbc-play-initializer" % scalikejdbcInitializerVersion
)
