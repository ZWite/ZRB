//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.zhang.ThreadLocal;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalData<T> {
//    private Map<String, List<Persistence>> persistenceMap = new HashMap();
//    private List<TransactionStatus> transactionStatusList = new ArrayList();
    private Map<String, T> contextDataMap = new HashMap();

    public ThreadLocalData() {
    }

//    public Map<String, List<Persistence>> getPersistenceMap() {
//        return this.persistenceMap;
//    }
//
//    public void setPersistenceMap(Map<String, List<Persistence>> persistenceMap) {
//        this.persistenceMap = persistenceMap;
//    }
//
//    public List<TransactionStatus> getTransactionStatusList() {
//        return this.transactionStatusList;
//    }
//
//    public void setTransactionStatusList(List<TransactionStatus> transactionStatusList) {
//        this.transactionStatusList = transactionStatusList;
//    }

    public T getContextData(String key) {
        return this.contextDataMap.get(key);
    }

    public void setContextData(String key, T obj) {
        this.contextDataMap.put(key, obj);
    }

    public void clear() {
        this.contextDataMap.clear();
    }
}
