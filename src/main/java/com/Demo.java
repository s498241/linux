package com;
import com.util.IdUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Demo {


    public static void main(String[] args) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyMMddHmmssSSS");
        LocalDateTime now  = LocalDateTime.now();
        System.out.println(now.format(formatter).length());
        System.out.println(String.valueOf(Long.MAX_VALUE).length());
        System.out.println(String.valueOf(Long.MAX_VALUE).length());
        for (int i = 0; i < 100; i++) {
         System.out.println(IdUtil.getId());

        }
    }

}
