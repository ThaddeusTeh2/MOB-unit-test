# Project Component Overview

This document describes the purpose, responsibilities, and relationships of each significant file/component in the codebase.

## Application and Entry Points

- `app/src/main/AndroidManifest.xml`
  - Purpose: Declares app components, permissions, and metadata.
  - Responsibilities: Registers `MyApp` as the Application class, `MainActivity` as the launcher activity, and `LocationService` as a foreground location service; requests runtime permissions for location and notifications.
  - Relationships: Binds class names to the Android runtime. The manifest references `MyApp`, `MainActivity`, resources under `res/`, and `service/LocationService`.

- `app/src/main/java/com/team/moblocation/MyApp.kt`
  - Purpose: Application class for Hilt DI setup.
  - Responsibilities: Annotated with `@HiltAndroidApp` to trigger Hilt code generation and DI container initialization.
  - Relationships: Referenced by the Manifest `android:name`. Generates Hilt components used by `@AndroidEntryPoint` classes like `MainActivity`.

- `app/src/main/java/com/team/moblocation/MainActivity.kt`
  - Purpose: The main UI entry point of the app.
  - Responsibilities: Hosts the Compose UI, requests location/notification permissions, and starts/stops `LocationService` as a foreground service.
  - Relationships: Uses `MOBLocationTheme`, `AppNav` for navigation, and starts `LocationService`. Annotated with `@AndroidEntryPoint` to participate in Hilt DI.

## Services

- `app/src/main/java/com/team/moblocation/service/LocationService.kt`
  - Purpose: Foreground service that tracks device location.
  - Responsibilities: Creates a notification channel, starts in foreground, configures `FusedLocationProviderClient` for periodic updates, handles location callbacks, and cleans up updates on destroy.
  - Relationships: Permissions declared in Manifest; notification resources from `res/drawable`; started by `MainActivity`. Uses Google Play Services Location APIs.

## UI - Compose Navigation and Screens

- `app/src/main/java/com/team/moblocation/ui/navigation/AppNav.kt`
  - Purpose: Defines the app navigation graph.
  - Responsibilities: Creates a `NavHost` with start destination and composable routes.
  - Relationships: Uses `Screen` sealed class for destinations and navigates to `HomeScreen`.

- `app/src/main/java/com/team/moblocation/ui/navigation/Screens.kt`
  - Purpose: Type-safe route definitions for navigation.
  - Responsibilities: Declares a sealed `Screen` hierarchy with serializable objects per destination.
  - Relationships: Referenced by `AppNav` for composing routes.

- `app/src/main/java/com/team/moblocation/ui/screens/home/HomeScreen.kt`
  - Purpose: Simple placeholder home screen.
  - Responsibilities: Renders a centered text using Compose.
  - Relationships: Composed from `AppNav` as the start destination.

- `app/src/main/java/com/team/moblocation/ui/screens/auth/LoginScreen.kt`
  - Purpose: Placeholder for an authentication screen (currently empty).
  - Responsibilities: To be implemented to render login UI.
  - Relationships: Can be added to `Screens` and `AppNav` for navigation when implemented.

## UI - Theme

- `app/src/main/java/com/team/moblocation/ui/theme/Theme.kt`
  - Purpose: App-wide Material3 theme setup for Compose.
  - Responsibilities: Defines `MOBLocationTheme` using dynamic colors (Android 12+) and light/dark schemes.
  - Relationships: Used by `MainActivity` to wrap the UI; depends on `Color.kt` and `Type.kt`.

- `app/src/main/java/com/team/moblocation/ui/theme/Color.kt`
  - Purpose: Color palette definitions for the theme.
  - Responsibilities: Exposes color constants used by `Theme.kt`.
  - Relationships: Imported in `Theme.kt`.

- `app/src/main/java/com/team/moblocation/ui/theme/Type.kt`
  - Purpose: Typography definitions for the theme.
  - Responsibilities: Configures the `Typography` object for Material3.
  - Relationships: Imported in `Theme.kt`.

## Core - Constants, DI, Utils

- `app/src/main/java/com/team/moblocation/core/constants/Constants.kt`
  - Purpose: Central place for app-wide constants.
  - Responsibilities: Currently empty; intended to host keys, request codes, intervals, etc.
  - Relationships: Can be imported across modules that need shared constants.

- `app/src/main/java/com/team/moblocation/core/di/AppModule.kt`
  - Purpose: Hilt module for providing dependencies.
  - Responsibilities: Declares a module installed in the `SingletonComponent` scope; currently empty, to be filled with `@Provides`/`@Binds`.
  - Relationships: Used by Hilt to inject dependencies into `@AndroidEntryPoint` classes and other injections.

- `app/src/main/java/com/team/moblocation/core/utils/Utils.kt`
  - Purpose: Utility functions container.
  - Responsibilities: Currently empty; intended to host reusable helpers (e.g., permission checks, formatting).
  - Relationships: Can be imported by `MainActivity`, services, and UI components.

## Tests

- `app/src/test/java/com/team/moblocation/ExampleUnitTest.kt`
  - Purpose: Placeholder unit test.
  - Responsibilities: Demonstrates basic testing setup.
  - Relationships: Runs with local JVM test configuration.

- `app/src/androidTest/java/com/team/moblocation/ExampleInstrumentedTest.kt`
  - Purpose: Placeholder instrumented test.
  - Responsibilities: Runs on an Android device/emulator to test app components.
  - Relationships: Uses Android testing framework.

## Build and Configuration

- `build.gradle.kts` (project-level)
  - Purpose: Configures Gradle plugins and repositories for the whole project.
  - Responsibilities: Applies global settings/plugins used by subprojects.
  - Relationships: Works with `settings.gradle.kts` and the app module `build.gradle.kts`.

- `settings.gradle.kts`
  - Purpose: Defines included modules and plugin management.
  - Responsibilities: Includes `:app` module and configures plugin resolution.
  - Relationships: Ties the project structure together.

- `app/build.gradle.kts`
  - Purpose: Android app module configuration.
  - Responsibilities: Declares Android SDK versions, dependencies (Compose, Hilt, Play Services Location), and build features.
  - Relationships: Consumed by Gradle; dependencies impact code in the module (e.g., Hilt annotations, Compose APIs).

- `gradle/libs.versions.toml`
  - Purpose: Centralized dependency version catalog.
  - Responsibilities: Manages versions for plugins and libraries referenced in Gradle files.
  - Relationships: Referenced by Gradle build scripts.

- `gradle.properties`, `local.properties`, `gradle/wrapper/gradle-wrapper.properties`
  - Purpose: Gradle and environment configuration.
  - Responsibilities: Performance tweaks, SDK paths, wrapper version.
  - Relationships: Affect build behavior and environment resolution.

## Resources

- `app/src/main/res/values/strings.xml`
  - Purpose: String resources including `app_name` and others.
  - Responsibilities: Localizable text used throughout the app and manifest.
  - Relationships: Referenced by `AndroidManifest.xml` and UI components.

- `app/src/main/res/values/colors.xml`
  - Purpose: XML color resources.
  - Responsibilities: Additional colors for legacy XML UI or other components.
  - Relationships: May be referenced by styles/themes.

- `app/src/main/res/values/themes.xml`
  - Purpose: App theme for XML-based UI.
  - Responsibilities: Defines `Theme.MOBStarterApp` used by Manifest and Activities.
  - Relationships: Applied to `MainActivity` and the Application in Manifest.

- `app/src/main/res/drawable/*` and `app/src/main/res/mipmap-*/*`
  - Purpose: Icons and vector drawables.
  - Responsibilities: Provide app launcher icons and notification icon (`outline_my_location_24`).
  - Relationships: Used by Manifest, `LocationService` notification, and system UI.

- `app/src/main/res/xml/backup_rules.xml`, `data_extraction_rules.xml`
  - Purpose: Backup and data extraction configuration.
  - Responsibilities: Control what app data is backed up and how system extracts data for diagnostics.
  - Relationships: Referenced by Manifest application attributes.

