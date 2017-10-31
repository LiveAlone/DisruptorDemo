package org.yqj.disruptor.demo;

import com.google.common.collect.Lists;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by yaoqijun.
 * Date:2016-04-27
 * Email:yaoqj@terminus.io
 * Descirbe:
 */
@Component
public class RunCommanLine implements CommandLineRunner{

    public void run(String... args) throws Exception {
        System.out.println("now command line run ");
        System.out.println("args content is " + Lists.newArrayList(args).toString());
    }
}
