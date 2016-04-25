package fr.ekito.injector;

/**
 * Log
 * Help abstract from default Android Log and run unit tests
 */
public class Log {

    public static Type OS = Type.ANDROID;

    public enum Type {
        ANDROID, JAVA
    }
    
    static String threadInfo(){
        Thread t = Thread.currentThread();
        return "(~"+t.getName()+") ";
    }

    public static void v(String TAG, String message) {
        if (OS == Type.ANDROID) {
            android.util.Log.v(TAG, threadInfo()+message);
        } else
            System.out.println(TAG + "[verbose] : " + message);
    }

    public static void i(String TAG, String message) {
        if (OS == Type.ANDROID) {
            android.util.Log.i(TAG, threadInfo()+message);
        } else
            System.out.println(TAG + "[info] : " + message);
    }

    public static void d(String TAG, String message) {
        if (OS == Type.ANDROID) {
            android.util.Log.d(TAG, threadInfo()+message);
        } else
            System.out.println(TAG + "[debug] : " + message);
    }

    public static void w(String TAG, String message) {
        if (OS == Type.ANDROID) {
            android.util.Log.w(TAG, threadInfo()+message);
        } else
            System.out.println(TAG + "[WARNING] : " + message);
    }

    public static void e(String TAG, String message) {
        e(TAG, message, null);
    }

    public static void e(String TAG, String message, Throwable e) {
        if (OS == Type.ANDROID) {
            android.util.Log.e(TAG, threadInfo()+message, e);
        } else
            System.err.println(TAG + "[UNKNOWN_ERROR] : " + message);
    }
}
