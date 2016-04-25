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

        if (t != null)
            Log.v(TAG, "getOrNull " + clazz.getSimpleName() + " :: " + t);
        else
            Log.v(TAG,"no instance for class:"+clazz);
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
        Log.v(TAG, "Add/Replace instance:"+o+" class:"+clazz.getSimpleName());
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
     * add components from Module definition
     *
     * @param module      Module
     *
     */
    public static void load(Class<? extends Module> module) {
        load(module,false);
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
     * remove an instance, from object's class
     *
     */
    public static void remove(Object o) {
        String simpleName = o.getClass().getSimpleName();
        Log.v(TAG, "Remove class : " + simpleName);
        instances.remove(simpleName);
    }

    /**
     * clear all instances
     */
    public static void clear() {
        Log.v(TAG, "Clear All instances !");
        instances.clear();
    }

}

