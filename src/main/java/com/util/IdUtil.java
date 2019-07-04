package com.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class IdUtil {
    private static final AtomicLong atomicLong = new AtomicLong();
    private static AtomicInteger in = new AtomicInteger();

    public static Long getId(){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyMMddHmmssSSS00");
        LocalDateTime now  = LocalDateTime.now();

        return   Long.parseLong(now.format(formatter)) +in.incrementAndGet();
    }
}
