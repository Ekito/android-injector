package fr.ekito.injector.kotlinapp

import android.app.Application
import fr.ekito.injector.Injector
import fr.ekito.injector.kotlinapp.di.MyModule

/**
 * Created by arnaud on 25/04/2016.
 */
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        //load module
        Injector.load(MyModule::class.java, true)
    }
}