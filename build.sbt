name := "tapir-cats-enumeratum-bug"

version := "0.1"

scalaVersion := "2.13.3"

val tapirVersion = "0.16.13"

libraryDependencies ++= Seq(
  "com.softwaremill.sttp.tapir"         %% "tapir-core"                         % tapirVersion,
  "com.softwaremill.sttp.tapir"         %% "tapir-json-circe"                   % tapirVersion,
  "com.softwaremill.sttp.tapir"         %% "tapir-openapi-docs"                 % tapirVersion,
  "com.softwaremill.sttp.tapir"         %% "tapir-openapi-circe-yaml"           % tapirVersion,
  "com.softwaremill.sttp.tapir"         %% "tapir-cats"                         % tapirVersion,
  "com.softwaremill.sttp.tapir"         %% "tapir-enumeratum"                   % tapirVersion,
  "com.beachape"                        %% "enumeratum-circe"                   % "1.6.1",
)