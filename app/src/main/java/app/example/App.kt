package app.example

import android.app.Application
import app.example.core.di.coreModule
import app.example.core.di.exampleModule
import app.example.core.di.networkModule
import app.example.di.appModule
import com.airbnb.mvrx.Mavericks
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber
import timber.log.Timber.DebugTree

class App : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate() {
        super.onCreate()
        Mavericks.initialize(applicationContext)

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }

        startKoin {
            androidContext(this@App)

            modules(
                appModule,
                coreModule,
                networkModule,
                exampleModule,
            )
        }
    }
}
