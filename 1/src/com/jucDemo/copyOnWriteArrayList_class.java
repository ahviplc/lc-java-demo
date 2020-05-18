package com.jucDemo;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class copyOnWriteArrayList_class {

    public static class ReadThread implements Runnable {
        private List<Integer> list;

        public ReadThread(List<Integer> list) {
            this.list = list;
        }

        @Override
        public void run() {
            for (Integer ele : list) {
                System.out.println("ReadThread:" + ele + " " + Thread.currentThread().getName() + " " + Thread.currentThread().getId());
            }
        }
    }


    public static class WriteThread implements Runnable {
        private List<Integer> list;

        public WriteThread(List<Integer> list) {
            this.list = list;
        }

        @Override
        public void run() {
            this.list.add(9);
            System.out.println("now this list size: " + this.list.size());
        }
    }
}
