package com.javarush.task.task27.task2707;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/*
Определяем порядок захвата монитора
*/
public class Solution {
    public static boolean isLockOrderNormal(final Solution solution, final Object o1, final Object o2) throws Exception {
        Thread t1 = new Thread(() -> {
            synchronized (o1) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2) {

                }
            }
        });
        t1.setDaemon(true);
        
        Thread t2 = new Thread(() -> {
            
            solution.someMethodWithSynchronizedBlocks(o1, o2);
            
        });
        t2.setDaemon(true);
        t1.start();
        t2.start();
        PrintStream old = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream outStream = new PrintStream(out);
        System.setOut(outStream);
        Thread.sleep(1000);
        if (out.toString().isEmpty()) {
            System.setOut(old);
            return false;
        }
        System.setOut(old);
        return true;
    }

    public static void main(String[] args) throws Exception {
        final Solution solution = new Solution();
        final Object o1 = new Object();
        final Object o2 = new Object();
        System.out.println(isLockOrderNormal(solution, o1, o2));
    }

    public void someMethodWithSynchronizedBlocks(Object obj1, Object obj2) {
        synchronized (obj1) {

            synchronized (obj2) {
                
                System.out.println(obj1 + " " + obj2);
            }
        }
    }
}
