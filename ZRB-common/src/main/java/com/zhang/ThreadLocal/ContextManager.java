//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.zhang.ThreadLocal;

public class ContextManager {
    private static final java.lang.ThreadLocal<ThreadLocalData> localData = new java.lang.ThreadLocal<ThreadLocalData>() {
        protected ThreadLocalData initialValue() {
            return new ThreadLocalData();
        }
    };

    public ContextManager() {
    }

    public static ThreadLocalData getLocalData() {
        return (ThreadLocalData)localData.get();
    }

    public static <T> T getContextData(Class<T> clazz) {
        return getContextData(clazz.getName());
    }

    public static <T> T getContextData(String key) {
        return (T)localData.get().getContextData(key);
    }

    public static <T> void setContextData(Class<T> clazz, Object obj) {
        setContextData(clazz.getName(), obj);
    }

    public static <T> void setContextData(String key, Object obj) {
        localData.get().setContextData(key, obj);
    }

    public static void clearContextData() {
        ((ThreadLocalData)localData.get()).clear();
        localData.remove();
    }
}
