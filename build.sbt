name := """circleline-gw"""

version := "1.0"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  // Spring
  "org.springframework" % "spring-context" % "4.2.4.RELEASE",
  "org.springframework" % "spring-test" % "4.2.4.RELEASE",

  // Akka
  "com.typesafe.akka" %% "akka-actor" % "2.3.11",
  "com.typesafe.akka" %% "akka-testkit" % "2.3.11" % "test",
  // Camel
  "org.apache.camel" % "camel-core" % "2.16.1",
  "org.apache.camel" % "camel-jetty" % "2.16.1",
  "org.apache.camel" % "camel-http" % "2.16.1",
  "org.apache.camel" % "camel-test" % "2.16.1",
  // ActiveMQ
  "org.apache.activemq" % "activemq-broker" % "5.13.0",
  "org.apache.activemq" % "activemq-client" % "5.13.0",
  "org.apache.activemq" % "activemq-camel" % "5.13.0",
  // Configuration
  "com.typesafe" % "config" % "1.3.0",
  // Logging
  "org.slf4j" % "slf4j-api" % "1.7.13",
  "ch.qos.logback" % "logback-classic" % "1.1.3",
  // Testing
  "junit" % "junit" % "4.12" % "test",
  "org.easytesting" % "fest-assert" % "1.4",
  "com.google.guava" % "guava" % "19.0",
  "org.projectlombok" % "lombok" % "1.16.6",
  "javax.servlet" % "javax.servlet-api" % "3.1.0",
  "com.novocode" % "junit-interface" % "0.11" % "test")
  
