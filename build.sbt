ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.13"

lazy val root = (project in file("."))
  .settings(
    name := "clean-arch-online-shop"
  )

libraryDependencies ++= Seq(
  "com.zaxxer" % "HikariCP" % "4.0.3",
  "org.postgresql" % "postgresql" % "42.3.6",
  "org.scalikejdbc" %% "scalikejdbc" % "4.0.0",
  "com.google.inject" % "guice" % "7.0.0",
  "com.jakehschwartz" %% "finatra-swagger" % "22.7.0" excludeAll(
    ExclusionRule(organization = "javax.activation", name = "activation"),
    ExclusionRule(organization = "javax.xml.bind", name = "jaxb-api"),
    ExclusionRule(organization = "org.slf4j", name = "jcl-over-slf4j"),
  ),
)
libraryDependencySchemes += "org.scala-lang.modules" %% "scala-parser-combinators" % VersionScheme.EarlySemVer
dependencyOverrides += "org.scala-lang.modules" %% "scala-parser-combinators" % "2.1.0"

libraryDependencies ~= {
  _ map (_ exclude("org.slf4j", "slf4j-simple"))
}