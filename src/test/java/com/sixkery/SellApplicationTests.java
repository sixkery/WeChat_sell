package com.sixkery;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;


@Slf4j
public class SellApplicationTests {

    @Test
    public void test1() {
        String name = "sixkery";
        String password = "123";
        log.debug("debug...");
        log.info("name: {} , password:{}",name,password);
        log.error("error...");
    }
}