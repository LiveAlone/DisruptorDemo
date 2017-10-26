package org.yqj.disruptor.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yaoqijun.
 * Date:2016-04-27
 * Email:yaoqj@terminus.io
 * Descirbe:
 */
@SpringBootApplication
@Controller
public class BootDemoApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .banner(new DemoBanner())
                .sources(BootDemoApplication.class)
                .run(args);
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String indexPage(){
        return "this is test index paging info";
    }
}
