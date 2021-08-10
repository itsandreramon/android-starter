[![Build](https://github.com/itsandreramon/mux-rulona/actions/workflows/build.yml/badge.svg)](https://github.com/itsandreramon/mux-rulona/actions/workflows/build.yml)

## Stack

| What           | How                        |
|----------------|----------------------------|
| User Interface | [Compose](https://developer.android.com/jetpack/compose)|
| Dependency Injection | [Koin](https://github.com/InsertKoinIO/koin)|
| State Management | [Mavericks](https://github.com/airbnb/mavericks)|
| Caching | [Room](https://developer.android.com/jetpack/androidx/releases/room)|
| Networking | [Retrofit](https://github.com/square/retrofit)|

## Instructions

This project uses Jetpack Compose and should be opened using Android Studio
2020.3.1 [Arctic Fox](https://developer.android.com/studio/) or newer.

#### Decrypt secrets

```
$ export ENCRYPT_KEY=
$ ./scripts/decrypt_secrets.sh
```

#### Add Signing Config

```
// keystore.properties
debugKeyPassword=
debugStorePassword=
```

#### Add Google Maps API keys

```
// local.properties
MAPS_API_KEY=
MAPS_SERVICES_API_KEY=
```
