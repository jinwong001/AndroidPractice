package com.jin.androiduipractice.other.singleton;

/**
 * Created by wanny-n1 on 2017/6/6.
 */

public class DoubleCheckedLockingSingleton {
    private static volatile DoubleCheckedLockingSingleton singleton;

    private DoubleCheckedLockingSingleton() {
    }

    public DoubleCheckedLockingSingleton getInstance() {
        if (singleton == null) {
            synchronized (DoubleCheckedLockingSingleton.class) {
                if (singleton == null) {
                    singleton = new DoubleCheckedLockingSingleton();
                }
            }
        }
        return singleton;
    }

}
