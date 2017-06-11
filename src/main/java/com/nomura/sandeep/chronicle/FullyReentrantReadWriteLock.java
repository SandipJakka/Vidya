package com.nomura.sandeep.chronicle;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sandeep on 3/27/2016.
 * <p>
 * Multiple readers ..
 * Readers re-entrant if no writers or write request.
 * Reader to Writer re-entrant when there are no readers
 * Writer to Reader re-entrant always ...
 */
public class FullyReentrantReadWriteLock {
    private int readers = 0;
    private int writers = 0;
    private int writeRequests = 0;
    /**
     * Reader thread to count for each reader
     */
    private Map<Thread, Integer> readerThreadMap = new HashMap<>();

    private Thread writerThread;

    public synchronized void readLock() throws InterruptedException {
        Thread callingThread = Thread.currentThread();
        while (!canGrantReadAccessTo(callingThread)) {
            wait();
        }
        readerThreadMap.put(callingThread, getAccessCount(callingThread) + 1);
        readers++;
    }

    private boolean canGrantReadAccessTo(Thread callingThread) {
        if (writerThread == callingThread) return true; /** Writer requesting a re-entrant read */
        if (writers > 0 || writeRequests > 0) return false; /** Writers getting preference */
        if (readerThreadMap.get(callingThread) != null) return true; /**re-entrant reader. */
        return true;
    }

    private int getAccessCount(Thread callingThread) {
        return readerThreadMap.get(callingThread) == null ? 0 : readerThreadMap.get(callingThread);
    }

    public synchronized void unlockReadLock() {
        Thread callingThread = Thread.currentThread();
        if (readerThreadMap.get(callingThread) == null) {
            throw new IllegalMonitorStateException("Current thread does not have a read lock.");
        }
        int access = readerThreadMap.get(callingThread);
        if (access > 1) {
            readerThreadMap.put(callingThread, getAccessCount(callingThread) - 1);
        } else {
            readerThreadMap.remove(callingThread);
        }
        readers--;
        notifyAll();
    }

    public synchronized void writeLock() throws InterruptedException {
        writeRequests++;
        Thread callingThread = Thread.currentThread();
        if (!canGrantWriteAccess(callingThread)) {
            wait();
        }
        writeRequests--;
        writers++;
        writerThread = callingThread;
    }

    private boolean canGrantWriteAccess(Thread callingThread) {
        if (readers == 1 && readerThreadMap.get(callingThread) == 1) return true; /** Single reader requesting  */
        if (writerThread == callingThread) return true; /** re-entrant write lock. */
        if (writers > 1) return false; /** Only 1 writer at a time. */
        if (readers > 0) return false; /** Any readers already reading */

        return true;
    }

    public synchronized void unlockWriteLock() {

    }

}
