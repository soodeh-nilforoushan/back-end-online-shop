ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.16"

lazy val root = (project in file("."))
  .settings(
    name := "Online Shop"
  )

libraryDependencies ++= Seq(
  "com.zaxxer" % "HikariCP" % "4.0.3",
  "org.postgresql" % "postgresql" % "42.3.6",
  "org.scalikejdbc" %% "scalikejdbc" % "4.0.0",
  // Log
  "ch.qos.logback" % "logback-classic" % "1.2.11",
  "com.google.inject" % "guice" % "5.1.0",
  "com.jakehschwartz" %% "finatra-swagger" % "22.7.0" excludeAll(
    ExclusionRule(organization = "javax.activation", name = "activation"),
    ExclusionRule(organization = "javax.xml.bind", name = "jaxb-api"),
    ExclusionRule(organization = "org.slf4j", name = "jcl-over-slf4j"),
  )
)
libraryDependencySchemes += "org.scala-lang.modules" %% "scala-parser-combinators" % VersionScheme.EarlySemVer
dependencyOverrides += "org.scala-lang.modules" %% "scala-parser-combinators" % "2.1.0"

libraryDependencies ~= {
  _ map (_ exclude("org.slf4j", "slf4j-simple"))
}