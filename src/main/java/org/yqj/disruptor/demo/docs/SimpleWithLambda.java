package org.yqj.disruptor.demo.docs;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import org.yqj.disruptor.demo.docs.simple.LongEvent;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author yaoqijun on 2017-10-30.
 */
public class SimpleWithLambda {
    public static void main(String[] args) throws Exception{

        Executor executor = Executors.newCachedThreadPool();

        int bufferSize = 1024;

        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(LongEvent::new, bufferSize, executor);

        disruptor
                .handleEventsWith((event, sequence, endOfBatch) -> System.out.println("Event is : " + event))
                .then((event, sequence, endOfBatch) -> event.clear());

        disruptor.start();

        // ring buffer data add
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        ByteBuffer bb = ByteBuffer.allocate(8);
        for (long l = 0; true; l++)
        {
            bb.putLong(0, l);
            // 不同的配置方式, config
            ringBuffer.publishEvent((event, sequence, buffer) -> event.setValue(buffer.getLong(0)), bb);
            Thread.sleep(1000);
        }
    }
}
