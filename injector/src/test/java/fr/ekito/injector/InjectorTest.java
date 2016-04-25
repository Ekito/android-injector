package fr.ekito.injector;

import org.junit.BeforeClass;

/**
 * Created by arnaud on 26/04/2016.
 */
public class InjectorTest {

    @BeforeClass
    public static void before() {
        Log.OS = Log.Type.JAVA;
        Injector.clear();
    }

}
