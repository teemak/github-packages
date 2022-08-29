//githubOwner := "teemak"
//githubRepository := "github-packages"
//val githubTokenSource := TokenSource.GitConfig("github.token")
//ThisBuild / GITHUB_TOKEN := TokenSource.GitConfig("github.token")
//ThisBuild / organization := "teemak"
//ThisBuild / name := "github-packages"
ThisBuild / versionScheme := Some("early-semver")

lazy val githubPackage = 
    project
        .in(file("."))
        .settings(
            organization := "teemak",
            name := "github-packages",
            resolvers ++= Seq(
                Resolver.mavenCentral,
            ),
            publishTo := Some("github-packages" at "https://maven.pkg.github.com/teemak/github-packages")
        )
