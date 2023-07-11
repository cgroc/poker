import sbt._

object Dependencies {
  lazy val catsEffect      = "org.typelevel" %% "cats-effect"       % "3.5.1"
  lazy val enumeratum      = "com.beachape"  %% "enumeratum"        % "1.7.2"
  lazy val munit           = "org.scalameta" %% "munit"             % "0.7.29"
  lazy val munitCatsEffect = "org.typelevel" %% "munit-cats-effect" % "2.0.0-M3"
}
