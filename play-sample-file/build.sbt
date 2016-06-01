name := """play-sample-file"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  // If you enable PlayEbean plugin you must remove these
  // JPA dependencies to avoid conflicts.
  "mysql" % "mysql-connector-java" % "5.1.18",
  javaJpa,
  "org.hibernate" % "hibernate-entitymanager" % "4.3.7.Final"
)

// Compile the project before generating Eclipse files, so that generated .scala or .class files for views and routes are present
EclipseKeys.preTasks := Seq(compile in Compile)
PlayKeys.externalizeResources := false
routesGenerator := InjectedRoutesGenerator
