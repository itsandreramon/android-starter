package de.thb.rulona

import android.app.Application
import com.airbnb.mvrx.Mavericks
import de.thb.core.di.coreModule
import de.thb.core.di.exampleModule
import de.thb.core.di.networkModule
import de.thb.rulona.di.appModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate() {
        super.onCreate()
        Mavericks.initialize(applicationContext)

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
