package fr.ekito.injector;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * Injector : Dependency Injection main class
 */
public class Injector {

    final static String TAG = "Injector";

    final static Map<Class, Object> instances = new HashMap<>();

    /**
     * get add component instance from Class
     * can't be null if not found or throw IllegalStateException
     *
     * @param clazz component Class
     * @param <T>   Type
     * @return
     * @throw IllegalStateException if not present
     */
    @NonNull
    public static <T> T get(Class<T> clazz) {
        Object o = instances.get(clazz);
        if (o == null)
            throw new IllegalStateException("Instance not found for class : '" + clazz.getSimpleName() + "'"
            );
        return (T) o;
    }

    /**
     * get add component instance from Class
     * can be null if not found
     *
     * @param clazz component Class
     * @param <T>   Type
     * @return Component if present
     */
    @Nullable
    public static <T> T getOrNull(Class<T> clazz) {
        T t = (T) instances.get(clazz);
        Log.v(TAG, "get " + clazz.getSimpleName() + " :: " + t);
        return t;
    }

    /**
     * Add new component
     * Is added to injector via its class
     *
     * @param o
     */
    public static void add(Object o) {
        if (o != null) {
            add(o, o.getClass());
        }
    }

    /**
     * Replace an existing instance (with given class), by given object instance
     *
     * @param o
     * @param clazz
     */
    public static void add(Object o, Class clazz) {
        Object existingInstance = getOrNull(clazz);
        if (existingInstance != null) {
            remove(clazz);
        }
        if (o != null && clazz != null) {
            Log.v(TAG, "Add instance for " + clazz.getSimpleName());
            instances.put(clazz, o);
        } else {
            throw new IllegalStateException("can't add instance for : object:" + o + " & class:" + clazz);
        }
    }

    /**
     * Initialize components from Module definition
     *
     * @param module      Module
     * @param clearBefore - clear instances before adding module
     */
    public static void load(Class<? extends Module> module, boolean clearBefore) {
        if (clearBefore) {
            clear();
        }

        Log.v(TAG, "loading definition : " + module);
        try {
            Module mod = module.newInstance();
            mod.load();
        } catch (Throwable e) {
            throw new IllegalStateException("Module prepare " + module.getSimpleName() + " error : " + e);
        }
    }

    /**
     * remove an instance
     *
     * @param clazz
     */
    public static void remove(Class clazz) {
        Log.v(TAG, "Remove class : " + clazz.getSimpleName());
        instances.remove(clazz);
    }

    /**
     * clear all instances
     */
    public static void clear() {
        Log.v(TAG, "Clear All instances !");
        instances.clear();
    }

}

