package app.example

import android.app.Application
import com.airbnb.mvrx.Mavericks
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import logcat.AndroidLogcatLogger
import logcat.LogPriority.VERBOSE

@HiltAndroidApp
class App : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate() {
        super.onCreate()
        Mavericks.initialize(applicationContext)

        AndroidLogcatLogger.installOnDebuggableApp(
            application = this,
            minPriority = VERBOSE,
        )
    }
}
