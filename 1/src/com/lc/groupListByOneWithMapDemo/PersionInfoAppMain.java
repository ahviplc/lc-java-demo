package com.lc.groupListByOneWithMapDemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.lc.groupListByOneWithMapDemo.PersionInfo;

public class PersionInfoAppMain {


    /**
     * 按照年龄age 分类 list 通过使用map key value
     *
     * @param persionInfoList
     * @return
     * @throws Exception
     */
    private static Map<Integer, List<PersionInfo>> groupPersionByAge(List<PersionInfo> persionInfoList) throws Exception {
        Map<Integer, List<PersionInfo>> resultMap = new HashMap<Integer, List<PersionInfo>>();

        try {
            for (PersionInfo persionInfo : persionInfoList) {

                if (resultMap.containsKey(persionInfo.getAge())) {//map中年龄已存在，将该数据存放到同一个key（key存放的是年龄）的map中
                    resultMap.get(persionInfo.getAge()).add(persionInfo);
                } else {//map中不存在，新建key，用来存放数据
                    List<PersionInfo> tmpList = new ArrayList<PersionInfo>();
                    tmpList.add(persionInfo);
                    resultMap.put(persionInfo.getAge(), tmpList);

                }

            }

        } catch (Exception e) {
            throw new Exception("按照年龄对人进行分组时出现异常", e);
        }

        return resultMap;
    }


    public static void main(String[] args) throws Exception {

        List<PersionInfo> lists = new ArrayList<PersionInfo>();


        PersionInfo info = new PersionInfo("IMCC消化科病房", "消化科", 13);
        PersionInfo info1 = new PersionInfo("IMCC消化科病房", "消化科", 13);
        PersionInfo info2 = new PersionInfo("消化科病房", "消化内科科", 14);
        PersionInfo info3 = new PersionInfo("消化科病房(南院", "消化科", 15);
        lists.add(info);
        lists.add(info1);
        lists.add(info2);
        lists.add(info3);


        Map<Integer, List<PersionInfo>> aa = groupPersionByAge(lists);

//        System.out.println(aa);

//        System.out.println(aa.keySet());

        for(Map.Entry<Integer,  List<PersionInfo>> entry: aa.entrySet()) {

//            System.out.print(entry.getKey() + ":" + entry.getValue().size() +":" + entry.getValue() + "\t");
//
//            List<PersionInfo> lll= entry.getValue();
//
//            System.out.println("-----"+lll.size()+"-----"+lll.get(0).getAge());



            if(entry.getValue().size()==1){

                List<PersionInfo> okPersionInfoList= entry.getValue();
                System.out.println(okPersionInfoList.get(0).getAge()+okPersionInfoList.get(0).getName());



            }else if(entry.getValue().size()>=2) {

                List<PersionInfo> okPersionInfoList2= entry.getValue();
                System.out.println(okPersionInfoList2.get(0).getAge()+okPersionInfoList2.get(0).getName());

            }



            List<PersionInfo> okPersionInfoList= entry.getValue();
            System.out.println(okPersionInfoList.get(0).getAge()+okPersionInfoList.get(0).getName());


        }

    }
}
