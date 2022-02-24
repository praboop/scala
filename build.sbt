name := "scala"

version := "0.1"

scalaVersion := "2.13.3"
//scalaVersion := "2.12.11"

libraryDependencies +=  "com.softwaremill.sttp.client3" %% "core" % "3.0.0-RC10"
libraryDependencies +=  "com.softwaremill.sttp.client3" %% "httpclient-backend" % "3.0.0-RC10"
libraryDependencies += "com.softwaremill.sttp.client" %% "async-http-client-backend-future" % "3.0.0-RC3"
libraryDependencies +=  "com.google.guava" % "guava" % "25.1-jre"

//H2
libraryDependencies += "com.typesafe.slick" %% "slick" % "3.3.3"
libraryDependencies += "com.h2database" % "h2" % "1.4.200"

libraryDependencies ++= Seq("org.slf4j" % "slf4j-api" % "1.7.5",
  "org.slf4j" % "slf4j-simple" % "1.7.5")