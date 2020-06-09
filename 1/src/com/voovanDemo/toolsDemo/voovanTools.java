package com.voovanDemo.toolsDemo;

import org.voovan.tools.TDateTime;
import org.voovan.tools.TPerformance;
import org.voovan.tools.TString;
import org.voovan.tools.log.Logger;
import org.voovan.tools.security.TBase64;

public class voovanTools {
    public static void main(String[] args) {

//        Logger.warn("LC");
//        Logger.info("this is log message.");
//        //output: this is log message.
//        Logger.infof("this is log message. {{1}}-{{bbbbb}}", "aaa", "bbb");
//        //output: this is log message. aaa-bbb

        System.out.println(TDateTime.now());

        System.out.println(TPerformance.getProcessorCount());
        System.out.println(TPerformance.getProcessorInfo());
        System.out.println(TPerformance.getLocalIpAddrs());
        System.out.println(TPerformance.getJVMInfo());

        byte[] decodebyte = TBase64.decode("1234");
//        for (byte b : decodebyte) {
//            System.out.println(b);
//        }
        System.out.println(TBase64.encode(decodebyte));

        System.out.println(TString.leftPad("1", 8, "#".charAt(0)));
        System.out.println(TString.leftPad("1", 8, "0".charAt(0)));

        System.out.println(TString.rightPad("1", 8, "0".charAt(0)));
    }
}
