package com.monster.cas;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.LockSupport;

@Slf4j
public class AtomicStampedReferenceTest {

    public static void main(String[] args) {

        // 定义AtomicStampedReference Pair.reference值为1, Pair.stamp为1
        AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(1, 1);

        new Thread(() -> {
            int[] stampHolder = new int[1];
            int value = (int) atomicStampedReference.get(stampHolder);
            int stamp = stampHolder[0];
            log.debug("Thread1 read value: {}, stamp: {}", value, stamp);

            // 阻塞 1S
            LockSupport.parkNanos(1000000000L);

            if (atomicStampedReference.compareAndSet(value, 3, stamp, stamp + 1)) {
                log.debug("Thread1 update from {} to 3", value);
            } else {
                log.debug("Thread1 update fail!");
            }
        }, "Thread1").start();


        new Thread(() -> {
            int[] stampHolder = new int[1];
            int value = (int) atomicStampedReference.get(stampHolder);
            int stamp = stampHolder[0];
            log.debug("Thread2 read value: {}, stamp: {}", value, stamp);

            if (atomicStampedReference.compareAndSet(value, 2, stamp, stamp + 1)) {
                log.debug("Thread2 update from {} to 2", value);

                value = (int) atomicStampedReference.get(stampHolder);
                stamp = stampHolder[0];
                log.debug("Thread2 read value: " + value + ", stamp: " + stamp);
                // Thread2通过CAS修改value值为1
                if (atomicStampedReference.compareAndSet(value, 1, stamp, stamp + 1)) {
                    log.debug("Thread2 update from {} to 1", value);
                }
            } else {
                log.debug("Thread2 update fail!");
            }
        }, "Thread2").start();
    }
}
