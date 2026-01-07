pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "siyai-front-android-fixed"

include(":app")

include(":core:common")
include(":core:network")
include(":core:db")
include(":core:analytics")

include(":feature:auth:api")
include(":feature:auth:impl")

include(":feature:feed:api")
include(":feature:feed:impl")

include(":feature:details:api")
include(":feature:details:impl")

include(":feature:favorites:api")
include(":feature:favorites:impl")
include(":feature:auth:api", ":feature:auth:impl")
include(":feature:feed:api", ":feature:feed:impl")
include(":feature:details:api", ":feature:details:impl")
include(":feature:favorites:api", ":feature:favorites:impl")

