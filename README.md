# Android Compilation

## Gradle
Android Dependency, Utility, Etc.. Compilation Library

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