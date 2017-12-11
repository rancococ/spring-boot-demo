package com.catvgd.springbootdemo;

import java.net.InetAddress;

public class HostTest {

    public static void main(String[] args) {
        try {
            System.out.println(InetAddress.getLocalHost().getHostName());
            System.out.println(InetAddress.getLocalHost().getCanonicalHostName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
