package com.jin.androiduipractice.other.singleton;

/**
 * Created by wanny-n1 on 2017/6/6.
 */

public class StaticClassSingleton {

    private static class InnerSingleton {
        private static final StaticClassSingleton instance = new StaticClassSingleton();

    }

    public static StaticClassSingleton getInstance() {
        return InnerSingleton.instance;
    }

    private StaticClassSingleton() {
    }
}
