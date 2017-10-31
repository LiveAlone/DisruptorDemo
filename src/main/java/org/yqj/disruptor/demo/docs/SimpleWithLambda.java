package org.yqj.disruptor.demo.docs;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import org.yqj.disruptor.demo.docs.simple.LongEvent;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author yaoqijun on 2017-10-30.
 */
public class SimpleWithLambda {
    public static void main(String[] args) throws Exception{

        Executor executor = Executors.newCachedThreadPool();

        int bufferSize = 1024;

        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(LongEvent::new, bufferSize, executor);

        disruptor.handleEventsWith(builderHandler("handlerFirst"), builderHandler("handlerSecond"))
                .then(builderClean());

        disruptor.start();

        // ring buffer data add
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        for (long l = 0; true; l++)
        {
            final Long value = l;
            ringBuffer.publishEvent((event, sequence, buffer) -> event.setValue(value));
            Thread.sleep(1000);
        }
    }

    private static EventHandler<LongEvent> builderClean(){
        return (event, sequence, endOfBatch) -> {
//            sleepWorkThreadInfo();
            System.out.println(" clean event " + event.getValue());
        };
    }

    private static EventHandler<LongEvent> builderHandler(String handlerName){
        return (event, sequence, endOfBatch) -> {
            sleepWorkThreadInfo();
            System.out.println(handlerName + " finish event " + event.getValue());
        };
    }

    private static void sleepWorkThreadInfo(){
        Long randomSleepTime = ThreadLocalRandom.current().nextLong(5000);

//        System.out.println("current thread " + Thread.currentThread().getName() + " sleep time: " + randomSleepTime);

        try {
            Thread.sleep(randomSleepTime);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
