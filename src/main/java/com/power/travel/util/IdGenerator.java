package com.power.travel.util;

import java.util.UUID;

public class IdGenerator {

    public static String id() {
        // 生成一个随机的UUID
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
