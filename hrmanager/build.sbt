name := """hrmanager"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJpa,
  "mysql" % "mysql-connector-java" % "5.1.18",
  "org.hibernate" % "hibernate-core" % "4.3.6.Final",
  "org.hibernate" % "hibernate-entitymanager" % "4.3.6.Final",
  "com.typesafe.play" %% "play-mailer" % "4.0.0"
)

// Compile the project before generating Eclipse files, so that generated .scala or .class files for views and routes are present
EclipseKeys.preTasks := Seq(compile in Compile)
PlayKeys.externalizeResources := false
routesGenerator := InjectedRoutesGenerator