[![Build](https://github.com/itsandreramon/android-starter/actions/workflows/build.yml/badge.svg)](https://github.com/itsandreramon/android-starter/actions/workflows/build.yml)

## Stack

| What                 | How                                                                              |
|----------------------|----------------------------------------------------------------------------------|
| User Interface       | [Compose](https://developer.android.com/jetpack/compose)                         |
| State Management     | [Mavericks](https://github.com/airbnb/mavericks)                                 |
| GraphQL              | [Apollo 3](https://github.com/apollographql/apollo-kotlin)                       |
| Dependency Injection | [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) |
| Caching              | [Room](https://developer.android.com/training/data-storage/room/)                |
| Testing              | [JUnit 5](https://github.com/junit-team/junit5)                                  |

## Information

This project initially used [dropbox/store](https://github.com/dropbox/Store) for caching functionality, but because of
some problems it has been removed. It is planned to be added again at a later stage and has been replaced by APIs found
in [RepositoryUtils.kt](https://github.com/itsandreramon/android-starter/blob/master/core/src/main/java/app/example/core/util/RepositoryUtils.kt)
.

## Instructions

This project uses Jetpack Compose and should be opened using [Android Studio](https://developer.android.com/studio/)
Arctic Fox or [IntelliJ IDEA](https://www.jetbrains.com/idea/) 2021.3
