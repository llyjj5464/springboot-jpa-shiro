package com.lyj.shiroweb;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.lang.Console;
import cn.hutool.core.text.StrBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShiroWebApplicationTests {

    @Test
    public void contextLoads() {
        TimeInterval timer = DateUtil.timer();
        StringBuilder b2 = new StringBuilder();
        for (int i = 0; i < 10000000; i++) {
            b2.append("test");
            b2 = new StringBuilder();
        }
        Console.log(timer.interval());
    }
    @Test
    public void StrBuilderTest(){
        TimeInterval timer2 = DateUtil.timer();
        StrBuilder builder = StrBuilder.create();
        for(int i =0; i< 10000000; i++) {
            builder.append("test");
            builder.reset();
        }
        Console.log(timer2.interval());
    }
}
