package com.back.standard.util;

import java.util.function.Supplier;

public class Ut {
    public static class thread {
        public static void waitUntil(Supplier<Boolean> condition) {
            waitUntil(condition, 10000, 100);
        }

        public static void waitUntil(Supplier<Boolean> condition, long timeoutMs, long intervalMs) {
            long startTime = System.currentTimeMillis();

            while (!condition.get()) {
                if (System.currentTimeMillis() - startTime > timeoutMs) {
                    throw new RuntimeException("Condition not met within timeout: " + timeoutMs + "ms");
                }

                try {
                    Thread.sleep(intervalMs);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Thread interrupted while waiting", e);
                }
            }
        }
    }
}
