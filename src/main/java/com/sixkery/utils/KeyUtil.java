package com.sixkery.utils;

import java.util.Random;

/**
 * @author sixkery
 * @date 2019/11/12
 */

public class KeyUtil {
    // 生成随机的主键
    // 格式时间+随机数
    public static String genUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }

    public static void main(String[] args) {
        String s = genUniqueKey();
        System.out.println("s = " + s);
    }
}
