organization := "net.gutefrage.data"
name := "ai-dataset"

import sbtassembly.AssemblyPlugin.autoImport.{MergeStrategy, PathList}

addCommandAlias("jenkinsTask", "; clean; assembly")

resolvers += Resolver.sonatypeRepo("releases")
resolvers += ("Gutefrage Release Repo" at "http://artifacts.endor.gutefrage.net/content/groups/public")
.withAllowInsecureProtocol(true)
resolvers += ("twitter-repo" at "https://maven.twttr.com")
  .withAllowInsecureProtocol(true)


libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core"   % Dependencies.sparkDepVer % "provided",
  "org.apache.spark" %% "spark-sql"    % Dependencies.sparkDepVer % "provided",
  "org.apache.spark" %% "spark-hive"   % Dependencies.sparkDepVer % "provided",
  "com.typesafe"     % "config"        % "1.2.1",
  "net.gutefrage"    %% "weird-string" % "1.11",
  "net.gutefrage.etl" %% "spark-commons" % "4.4",
  "net.gutefrage"      %% "clean-embeddings" % "1.14"
)

// Scala versions we build for
scalaVersion := "2.11.8"


assemblyMergeStrategy in assembly := {
  case PathList("org","aopalliance", xs @ _*) => MergeStrategy.last
  case PathList("javax", "inject", xs @ _*) => MergeStrategy.last
  case PathList("javax", "servlet", xs @ _*) => MergeStrategy.last
  case PathList("javax", "activation", xs @ _*) => MergeStrategy.last
  case PathList("org", "apache", xs @ _*) => MergeStrategy.last
  case PathList("com", "google", xs @ _*) => MergeStrategy.last
  case PathList("com", "esotericsoftware", xs @ _*) => MergeStrategy.last
  case PathList("com", "codahale", xs @ _*) => MergeStrategy.last
  case PathList("com", "yammer", xs @ _*) => MergeStrategy.last
  case "about.html" => MergeStrategy.rename
  case "META-INF/ECLIPSEF.RSA" => MergeStrategy.last
  case "META-INF/mailcap" => MergeStrategy.last
  case "META-INF/mimetypes.default" => MergeStrategy.last
  case "plugin.properties" => MergeStrategy.last
  case "log4j.properties" => MergeStrategy.last
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}