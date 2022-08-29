githubOwner := "teemak"
githubRepository := "github-packages"
githubTokenSource := TokenSource.GitConfig("github.token")
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
                "GitHub TeeMak Apache Maven Packages" at "https://maven.pkg.github.com",
            ),
            publishTo := Some("GitHub TeeMak Apache Maven Packages" at "https://maven.pkg.github.com")
        )
