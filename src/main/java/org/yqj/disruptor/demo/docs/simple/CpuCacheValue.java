package org.yqj.disruptor.demo.docs.simple;

/**
 * @author yaoqijun on 2017-10-30.
 * cpu long 类型update 会左右填充类型
 */
public class CpuCacheValue implements Runnable{

    public final static long ITERATIONS = 500L * 1000L * 100L;

    private final int arrayIndex;

    private static VolatileLong[] longs;

    private static int numThread;

    public CpuCacheValue(final int arrayIndex)
    {
        this.arrayIndex = arrayIndex;
    }

    public static void main(final String[] args) throws Exception
    {
        for (int i=1; i<= 4; i++) {
            initThread(i);
            final long start = System.nanoTime();
            runTest();
            System.out.println("thread count i: " + i + " duration = " + (System.nanoTime() - start));
        }
    }

    private static void initThread(int n){
        numThread = n;
        longs = new VolatileLong[n];
        for (int i = 0; i < longs.length; i++)
        {
            longs[i] = new VolatileLong();
        }
    }

    private static void runTest() throws InterruptedException
    {
        Thread[] threads = new Thread[numThread];

        for (int i = 0; i < threads.length; i++)
        {
            threads[i] = new Thread(new CpuCacheValue(i));
        }

        for (Thread t : threads)
        {
            t.start();
        }

        for (Thread t : threads)
        {
            t.join();
        }
    }

    public void run()
    {
        long i = ITERATIONS + 1;
        while (0 != --i)
        {
            longs[arrayIndex].value = i;
        }
    }

    public final static class VolatileLong
    {
        public long a1, a2, a3, a4, a5, a6; // comment out

        public volatile long value = 0L;

        public long p1, p2, p3, p4, p5, p6; // comment out
    }
}
