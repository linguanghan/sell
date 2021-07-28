package com.wechat.sell;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LogTest {

//    private final Logger logger = LoggerFactory.getLogger(LogTest.class);

    @Test
    public void test() {
        String name = "imooc";
        String password = "123456";
        log.debug("debug...");
        log.info("name：{}, password：{}", name, password);
        log.info("info...");
        log.error("error...");
        log.warn("warning...");
    }
}
