//githubOwner := "teemak"
//githubRepository := "github-packages"
//githubTokenSource := TokenSource.GitConfig("github.token")
//val defaultVersion: String = "1.0.0-a0"
val defaultVersion: String = "0.0.0-a0"
val versionFromTag: String = sys.env
  .get("GITHUB_REF_TYPE")
  .filter(_ == "tag")
  .flatMap(_ => sys.env.get("GITHUB_REF_NAME"))
  .flatMap { t =>
    t.headOption.map {
      case 'a' => tagAlpha(t.tail) // Alpha build, a1.2.3.4
      case 'b' => tagBeta(t.tail) // Beta build, b1.2.3.4
      case 'm' => tagMilestone(t.tail) // Milestone build, m1.2.3.4
      case 'r' => tagRC(t.tail) // RC build, r1.2.3.4
      case 'v' => t.tail // Production build, should be v1.2.3
      case _ => defaultVersion
    }
  }
  .getOrElse(defaultVersion)
val tagWithQualifier: String => String => String =
  qualifier =>
    tagVersion => s"%s.%s.%s-${qualifier}%s".format(tagVersion.split("\\."): _*)
val tagAlpha: String => String = tagWithQualifier("a")
val tagBeta: String => String = tagWithQualifier("b")
val tagMilestone: String => String = tagWithQualifier("m")
val tagRC: String => String = tagWithQualifier("rc")

//ThisBuild / publish / skip := true
ThisBuild / publishMavenStyle := true
ThisBuild / versionScheme := Some("early-semver")
ThisBuild / publishTo := Some("GitHub Packages Registry" at "https://maven.pkg.github.com")
ThisBuild / credentials += Credentials(
    "Github Package Registry",
    "maven.pkg.github.com",
    "teemak",
    sys.env.getOrElse("GITHUB_TOKEN", "N/A")
)

lazy val githubPackage = 
    project
        .in(file("."))
        .settings(
            organization := "teemak",
            name := "github-packages",
            resolvers ++= Seq(
                Resolver.mavenCentral,
                //"GitHub Package Registry" at "https://maven.pkg.github.com",
            ),
        )
