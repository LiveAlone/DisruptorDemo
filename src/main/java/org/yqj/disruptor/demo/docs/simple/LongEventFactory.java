package org.yqj.disruptor.demo.docs.simple;

import com.lmax.disruptor.EventFactory;

/**
 * @author yaoqijun on 2017-10-30.
 */
public class LongEventFactory implements EventFactory<LongEvent>{
    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
