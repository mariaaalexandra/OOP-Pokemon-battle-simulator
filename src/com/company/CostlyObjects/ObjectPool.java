package com.company.CostlyObjects;

import java.util.Enumeration;
import java.util.Hashtable;

/* Object pool design pattern */
abstract class ObjectPool<T> {
    /* Fields */
    long deadTime = 0;
    Hashtable<T, Long> lock = null;
    Hashtable<T, Long> unlock = null;

    /* Constructor */
    ObjectPool()
    {
        deadTime = 5000; // 5 seconds
        lock = new Hashtable<T, Long>();
        unlock = new Hashtable<T, Long>();
    }

    /* Methods */
    abstract T create();

    abstract boolean validate(T o);

    abstract void dead(T o);

    /* Remove object if 5 seconds pass */
    synchronized T takeOut()
    {
        long now = System.currentTimeMillis();
        T t;
        if (unlock.size() > 0) {
            Enumeration<T> e = unlock.keys();
            while (e.hasMoreElements()) {
                t = e.nextElement();
                if ((now - unlock.get(t)) > deadTime) {
                    // object is set dead
                    unlock.remove(t);
                    dead(t);
                    t = null;
                }
                else {
                    if (validate(t)) {
                        unlock.remove(t);
                        lock.put(t, now);
                        return (t);
                    }
                    else {
                        // object failed validation
                        unlock.remove(t);
                        dead(t);
                        t = null;
                    }
                }
            }
        }
        // no objects available, create a new one
        t = create();
        lock.put(t, now);
        return (t);
    }


    synchronized void takeIn(T t)
    {
        lock.remove(t);
        unlock.put(t, System.currentTimeMillis());
    }
}
