package fr.ekito.injector.demoapp;

import android.app.Application;

import fr.ekito.injector.Injector;
import fr.ekito.injector.demoapp.di.MyModule;

/**
 * Created by arnaud on 25/04/2016.
 */
public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // load my module
        Injector.load(MyModule.class,true);
    }
}
