package org.yqj.disruptor.demo.docs.simple;

/**
 * @author yaoqijun on 2017-10-30.
 */
public class LongEvent {

    private Long value;

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public void clear(){
        System.out.println("clear long event condition");
        value = null;
    }

    @Override
    public String toString() {
        return "LongEvent{" +
                "value=" + value.toString() +
                '}';
    }
}
