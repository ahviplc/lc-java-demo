package com.lc.ss_ssr_utils;

import java.util.Base64;

/**
 * SSR SS的具体解析工具类
 *
 * @Author:LC
 * @DateTime:2019年12月13日11:01:51
 */
public class ss_ssr_utils {

    public static void main(String[] args) {
        System.out.println("================================================================");

        ssrDecoder("MTM5LjE2Mi41Ni4xMjU6MTc3MzE6b3JpZ2luOmFlcy0yNTYtY2ZiOnBsYWluOlpqVTFMbVoxYmkweE1EQTFPVEF3TVEvP29iZnNwYXJhbT1iMkptYzNCaGNtRnQmcHJvdG9wYXJhbT1jSEp2ZEc5amIyeHdZWEpoYlEmcmVtYXJrcz1jbVZ0WVhKcmN3Jmdyb3VwPVJuSmxaVk5UVWkxd2RXSnNhV00");
        ssDecoder("Y2hhY2hhMjA6ZG91Yi5pby9zc3poZngvKmRvdWIuYmlkL3NzemhmeC8qMjk4N0A2NC4xMzcuMjI5LjE1NDoyOTg3");

        //自己找的链接
        ssrDecoder("MTcyLjEwNC4xMDUuMTY4OjMwNzA3Om9yaWdpbjphZXMtMjU2LWNmYjpwbGFpbjpaalV5V0dsVWRYVjJNbk54Lz9vYmZzcGFyYW09JnJlbWFya3M9UUhOemNnJmdyb3VwPVFITnpjZw");//ssr://MTcyLjEwNC4xMDUuMTY4OjMwNzA3Om9yaWdpbjphZXMtMjU2LWNmYjpwbGFpbjpaalV5V0dsVWRYVjJNbk54Lz9vYmZzcGFyYW09JnJlbWFya3M9UUhOemNnJmdyb3VwPVFITnpjZw
        ssDecoder("cmM0LW1kNTpsbmNuLm9yZyB2NjZAODkuMzEuMTI1LjIzNjoyMDE1"); //ss://cmM0LW1kNTpsbmNuLm9yZyB2NjZAODkuMzEuMTI1LjIzNjoyMDE1
        //ss加了备注 #remarks
        ssDecoder("cmM0LW1kNTpsbmNuLm9yZyB2NjZAODkuMzEuMTI1LjIzNjoyMDE1#remarks");//ss://cmM0LW1kNTpsbmNuLm9yZyB2NjZAODkuMzEuMTI1LjIzNjoyMDE1#remarks
    }

    public static void ssrDecoder(String text) {

        byte[] decode = Base64.getDecoder().decode(text);
        String data = new String(decode);
        System.out.println("ssr解析data:" + data);

        String[] arr = data.split(":");
        String arr2 = arr[5].split("/\\?")[1];
        byte[] decode1 = Base64.getDecoder().decode(arr[5].split("/\\?")[0]);

        String password = new String(decode1);
        String ip = arr[0];
        String port = arr[1];
        String protocol = arr[2];
        String method = arr[3];
        String obfs = arr[4];
        String[] split = arr2.split("&");
        String obfsparam = "";
        String protoparam = "";
        String remarks = "";
        String group = "";

        for (String s : split) {

            String[] split1 = s.split("=");
            switch (split1[0]) {
                case "obfsparam":
                    if (split1.length == 1) {//等于1时obfsparam为空的情况
                        obfsparam = "";
                    } else if (split1.length == 2) {
                        obfsparam = new String(Base64.getDecoder().decode(split1[1]));
                    }
                    break;
                case "protoparam":
                    if (split1.length == 1) {//等于1时protoparam为空的情况
                        protoparam = "";
                    } else if (split1.length == 2) {
                        protoparam = new String(Base64.getDecoder().decode(split1[1]));
                    }
                    break;
                case "remarks":
                    if (split1.length == 1) {//等于1时remarks为空的情况
                        remarks = "";
                    } else if (split1.length == 2) {
                        remarks = new String(Base64.getDecoder().decode(split1[1]));
                    }
                    break;
                case "group":
                    if (split1.length == 1) {//等于1时group为空的情况
                        group = "";
                    } else if (split1.length == 2) {
                        group = new String(Base64.getDecoder().decode(split1[1]));
                    }
                    break;
                default:
                    break;
            }
        }

        System.out.println("========================SSR解析结果============================");
        System.out.println("ip:" + ip);//服务器ip(地址)
        System.out.println("port:" + port);//服务器端口
        System.out.println("password:" + password);//密码
        System.out.println("protocol:" + protocol);//协议
        System.out.println("method:" + method);//加密
        System.out.println("obfs:" + obfs);//混淆
        System.out.println("obfsparam:" + obfsparam);//混淆参数
        System.out.println("protoparam:" + protoparam);//协议参数
        System.out.println("remarks:" + remarks);//备注
        System.out.println("group:" + group);//group 分组
    }

    public static void ssDecoder(String text) {

        String remarks = "";//定义remarks

        //如果含有备注remarks的情况 输出备注信息 并将实际ss数据赋值给text
        if (text.contains("#")) {
            String[] arrWithReMark = text.split("#");
            //#前面的是text(含有具体ss的数据)
            text = arrWithReMark[0];
            //#后面的是remarks
            remarks = arrWithReMark[1];
            System.out.println("ss解析备注remarks:" + remarks);//加密
        }

        //不含有备注remarks的情况 直接处理
        byte[] decode = java.util.Base64.getDecoder().decode(text);
        String data = new String(decode);
        System.out.println("ss解析data:" + data);

        String[] arr = data.split(":");
        String method = arr[0];
        String ip = arr[1].split("@")[1];
        String password = arr[1].split("@")[0];
        String port = arr[2];

        System.out.println("========================SS解析结果============================");
        System.out.println("ip:" + ip);//服务器地址(ip)
        System.out.println("port:" + port);//服务器端口
        System.out.println("password:" + password);//密码
        System.out.println("method:" + method);//加密
        System.out.println("===============================================================");

        System.out.println("输出demo===============================================================");
        /**
         * ================================================================
         * ssr解析data:139.162.56.125:17731:origin:aes-256-cfb:plain:ZjU1LmZ1bi0xMDA1OTAwMQ/?obfsparam=b2Jmc3BhcmFt&protoparam=cHJvdG9jb2xwYXJhbQ&remarks=cmVtYXJrcw&group=RnJlZVNTUi1wdWJsaWM
         * ========================SSR解析结果============================
         * ip:139.162.56.125
         * port:17731
         * password:f55.fun-10059001
         * protocol:origin
         * method:aes-256-cfb
         * obfs:plain
         * obfsparam:obfsparam
         * protoparam:protocolparam
         * remarks:remarks
         * group:FreeSSR-public
         * ss解析data:chacha20:doub.io/sszhfx/*doub.bid/sszhfx/*2987@64.137.229.154:2987
         * ========================SS解析结果============================
         * ip:64.137.229.154
         * port:2987
         * password:doub.io/sszhfx/*doub.bid/sszhfx/*2987
         * method:chacha20
         * ===============================================================
         * ssr解析data:172.104.105.168:30707:origin:aes-256-cfb:plain:ZjUyWGlUdXV2MnNx/?obfsparam=&remarks=QHNzcg&group=QHNzcg
         * ========================SSR解析结果============================
         * ip:172.104.105.168
         * port:30707
         * password:f52XiTuuv2sq
         * protocol:origin
         * method:aes-256-cfb
         * obfs:plain
         * obfsparam:
         * protoparam:
         * remarks:@ssr
         * group:@ssr
         * ss解析data:rc4-md5:lncn.org v66@89.31.125.236:2015
         * ========================SS解析结果============================
         * ip:89.31.125.236
         * port:2015
         * password:lncn.org v66
         * method:rc4-md5
         * ===============================================================
         * ss解析备注remarks:remarks
         * ss解析data:rc4-md5:lncn.org v66@89.31.125.236:2015
         * ========================SS解析结果============================
         * ip:89.31.125.236
         * port:2015
         * password:lncn.org v66
         * method:rc4-md5
         * ===============================================================
         */
    }
}
