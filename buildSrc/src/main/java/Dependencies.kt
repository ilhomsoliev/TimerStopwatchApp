object Dependencies {
    val android = AndroidDependencies
    val coroutines = CoroutinesDependencies
    val compose = ComposeDependencies
    val test = TestDependencies
    val accompanist = AccompanistDependencies
}

object AndroidDependencies {
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:2.4.1"
    const val lifecycleViewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val ktx = "androidx.core:core-ktx:${Versions.ktx}"
    const val playCore = "com.google.android.play:core:${Versions.playCore}"
    const val dataStore = "androidx.datastore:datastore-preferences:${Versions.dataStore}"
    const val glance = "androidx.glance:glance-appwidget:${Versions.glance}"
    const val navigationRuntime = "androidx.navigation:navigation-runtime-ktx:2.4.1"
    val room = RoomDependencies
    val hilt = HiltDependencies
}

object HiltDependencies {
    const val navigation = "androidx.hilt:hilt-navigation-compose:1.0.0"
    const val android = "com.google.dagger:hilt-android:2.41"
    const val androidCompiler = "com.google.dagger:hilt-android-compiler:2.41"
    const val compiler = "androidx.hilt:hilt-compiler:1.0.0"
}

object CoroutinesDependencies {
    const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
}

object RoomDependencies {
    const val runtime = "androidx.room:room-runtime:${Versions.room}"
    const val compiler = "androidx.room:room-compiler:${Versions.room}"
    const val ktx = "androidx.room:room-ktx:${Versions.room}"
}

object ComposeDependencies {
    const val ui = "androidx.compose.ui:ui:${Versions.compose}"
    const val material = "androidx.compose.material:material:${Versions.compose}"
    const val googleMaterial = "com.google.android.material:material:1.4.0"
    const val tooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val icons = "androidx.compose.material:material-icons-extended:${Versions.compose}"
    const val navigation = "androidx.navigation:navigation-compose:${Versions.composeNav}"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.composeVm}"
    const val activity = "androidx.activity:activity-compose:${Versions.composeActivity}"
    const val uiTest = "androidx.compose.ui:ui-test:${Versions.compose}"
    const val junit4 = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
    const val manifest = "androidx.compose.ui:ui-test-manifest:${Versions.compose}"
    const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout-compose:${Versions.constraintLayout}"
}

object TestDependencies {
    const val junit = "junit:junit:${Versions.testJunit}"
    const val runner = "androidx.test:runner:${Versions.testRunner}"
    const val core = "androidx.test:core:${Versions.testCore}"
    const val coreKtx = "androidx.test:core-ktx:${Versions.testCore}"
    const val uiAutomator = "androidx.test.uiautomator:uiautomator:${Versions.testUiAutomator}"
    const val junitExt = "androidx.test.ext:junit:${Versions.testJunitExt}"
    const val mockk = "io.mockk:mockk:${Versions.testMockk}"
    const val room = "androidx.room:room-testing:${Versions.testRoom}"
    const val barista = "com.adevinta.android:barista:${Versions.barista}"
    const val espressoCore = "androidx.test.espresso:espresso-core:3.4.0"
}

object AccompanistDependencies {
    const val animation =
        "com.google.accompanist:accompanist-navigation-animation:${Versions.accompanist}"
}