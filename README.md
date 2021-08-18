[![Build](https://github.com/itsandreramon/mux-rulona/actions/workflows/build.yml/badge.svg)](https://github.com/itsandreramon/android-starter/actions/workflows/build.yml)

## Stack

| What           | How                        |
|----------------|----------------------------|
| User Interface | [Compose](https://developer.android.com/jetpack/compose)|
| Dependency Injection | [Koin](https://github.com/InsertKoinIO/koin)|
| State Management | [Mavericks](https://github.com/airbnb/mavericks)|
| Caching | [Room](https://developer.android.com/jetpack/androidx/releases/room)|
| Networking | [Retrofit](https://github.com/square/retrofit)|

## Information

This project initially used [dropbox/store](https://github.com/dropbox/Store) for caching functionality, but because of some problems it has been removed. It is planned to be added again at a later stage and has been replaced by APIs found in [RepositoryUtils.kt](https://github.com/itsandreramon/android-starter/blob/master/app.example.core/src/main/java/app/app.example/app.example.core/app.example.core.util/RepositoryUtils.kt).

## Instructions

This project uses Jetpack Compose and should be opened using Android Studio
2020.3.1 [Arctic Fox](https://developer.android.com/studio/) or newer.
