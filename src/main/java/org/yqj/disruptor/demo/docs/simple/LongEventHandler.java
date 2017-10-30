package org.yqj.disruptor.demo.docs.simple;


import com.lmax.disruptor.EventHandler;

/**
 * @author yaoqijun on 2017-10-30.
 */
public class LongEventHandler implements EventHandler<LongEvent> {

    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println(" Event content is " + event);
    }
}
