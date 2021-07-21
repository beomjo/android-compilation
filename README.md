# Android Compilation
Android Dependency, Utility, Etc.. Compilation Library

## Setting

### build.gradle(project)
Add below codes to your root build.gradle file (not your module build.gradle file).
```
buildscript {
    apply from: 'android-compilation/versions.gradle'
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath "com.android.tools.build:gradle:${versions.gradle}"
        classpath deps.classpath.kotlin
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

task clean(type: Delete) {
    delete rootProject.buildDir
}
```

### build.gradle(app module)
```
...
...

android {
    // Change Version Reference 
    compileSdkVersion versions.compile_sdk_version
    buildToolsVersion versions.build_tool_version

    defaultConfig {
        ...
        ...
        // Change Version Reference 
        minSdkVersion versions.min_sdk_version
        targetSdkVersion versions.target_sdk_version

        ....
        ...
    }

   ...
   ...

    buildFeatures {
        dataBinding true
    }
}

dependencies {
    // adding library project import
    implementation project(":android-compilation")

    ...
    ...
}

```

### settings.gradle
```
include ':app'
include ':android-compilation'
```


## LICENSE
```xml
Designed and developed by 2021 beomjo

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
