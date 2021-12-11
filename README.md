![jitpack](https://jitpack.io/v/chibatching/remote-konfig.svg)

# remote-konfig

Easy wrapper library for Firebase Remote Config.

## Install

```groovy
implementation 'com.google.firebase:firebase-config-ktx:21.0.1'
implementation 'com.github.chibatching:remote-konfig:0.2.1'
```

## How to use

### Declare KonfigModel

Declare KonfigModel with key and default values.

```kotlin
object SomeConfig : KonfigModel {

    val foo by konfig("foo", 0L)
    val bar by konfig("bar", "Test Config")
    val baz by konfig("baz", 0.833)
    val qux by konfig("qux", false)
}
```

You can declare multiple KonfigModel to group and organize remote config values.


### Initialize RemoteKonfig

To setup default value, register your KonfigModel via `registerModels`
If you want to change some parameter of [Remote Config](https://firebase.google.com/docs/reference/android/com/google/firebase/remoteconfig/FirebaseRemoteConfigSettings.Builder.html)

```kotlin
RemoteKonfig.initialize {
    minimumFetchIntervalInSeconds = if (BuildConfig.DEBUG) 0L else 3600L
    registerModels(SomeConfig)
}
```

### Fetch and activate

Fetch and activate is automatically executed in initialize.
But if you want to do manually, you can do it.

```kotlin
RemoteKonfig.fetch()
RemoteKonfig.activate()
```

## License

```
Copyright 2016-2021 Takao Chiba

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```
