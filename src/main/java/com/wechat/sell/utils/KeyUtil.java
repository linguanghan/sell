package com.wechat.sell.utils;

import java.util.Random;

/**
 * TODO
 *
 * @author 13540
 * @version 1.0
 * @date 2021-03-10 10:38
 */
public class KeyUtil {
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
