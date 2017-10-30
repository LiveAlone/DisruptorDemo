package org.yqj.disruptor.demo.docs.simple;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author yaoqijun on 2017-10-30.
 */
public class LogEventMain {
    public static void main(String[] args) throws Exception{

        Executor executor = Executors.newCachedThreadPool();

        int bufferSize = 1024;

        LongEventFactory longEventFactory = new LongEventFactory();

        Disruptor<LongEvent> disruptor = new Disruptor<>(longEventFactory, bufferSize, executor);

        disruptor.handleEventsWith(new LongEventHandler());

        disruptor.start();

        // create ring buffer
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        LongEventProducer longEventProducer = new LongEventProducer(ringBuffer);

        ByteBuffer bb = ByteBuffer.allocate(8);

        for (long l = 0; true; l++){
            bb.putLong(0, l);
            longEventProducer.onData(bb);
            Thread.sleep(1000L);
        }

    }
}
