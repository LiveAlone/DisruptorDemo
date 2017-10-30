package org.yqj.disruptor.demo.docs.simple;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * @author yaoqijun on 2017-10-30.
 */
public class LongEventProducer {

    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer bb){
        long sequence = ringBuffer.next();
        try {
            LongEvent longEvent = ringBuffer.get(sequence);

            longEvent.setValue(bb.getLong(0));
        }finally {
            ringBuffer.publish(sequence);
        }
    }
}
