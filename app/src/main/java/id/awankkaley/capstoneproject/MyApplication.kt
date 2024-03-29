package id.awankkaley.capstoneproject

import android.app.Application
import id.awankkaley.core.di.databaseModule
import id.awankkaley.core.di.networkModule
import id.awankkaley.core.di.repositoryModule
import id.awankkaley.capstoneproject.di.useCaseModule
import id.awankkaley.capstoneproject.di.viewModelModule
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

open class MyApplication : Application() {
    @InternalCoroutinesApi
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}