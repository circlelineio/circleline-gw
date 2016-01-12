name := """circleline-gw"""

version := "1.0"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.3.11",
  "com.typesafe.akka" %% "akka-testkit" % "2.3.11" % "test",
  // Camel
  "org.apache.camel" % "camel-core" % "2.16.1",
  // ActiveMQ
  "org.apache.activemq" % "activemq-broker" % "5.13.0",
  "org.apache.activemq" % "activemq-client" % "5.13.0",
  "org.apache.activemq" % "activemq-camel" % "5.13.0",
  "junit" % "junit" % "4.12" % "test",
  "com.novocode" % "junit-interface" % "0.11" % "test")
  
