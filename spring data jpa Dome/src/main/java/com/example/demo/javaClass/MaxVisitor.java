package com.example.demo.javaClass;
import java.util.*;

public class MaxVisitor {
    //需求：现将举行一个餐会，让访客事先填写到达时间与离开时间，为了掌握座位的数目，必须先估计不同时间的最大访客数。
    //解法：
    //这个题目看似有些复杂，其实相当简单，单就计算访客数这个目的，同时考虑同一访客的来访时间与离开时间，反而会使程式变得复杂；
    // 只要将来访时间与离开时间分开处理就可以了，假设访客 i 的来访时间为x[i]，而离开时间为y[i]。
    // 个人解读：对应有时间间隔，长短，过程变量的，建议先分：开始阶段 和结束阶段，而不必纠结于过程量的存储。
    public static void  getMaxVisitor() throws  Exception{
        System.out.println("请输入0-24小时到达时间与离开时间:");
        System.out.println("参考格式 10 12 ");
        System.out.println("按q/Q 结束");
        Scanner scanner = new Scanner(System.in);
        List<String > list = new ArrayList<>();
        while (true){
            System.out.print("-->");
            String ss  = scanner.nextLine();
            if(ss.equalsIgnoreCase("q"))break;
            list.add(ss);
        }
        int [] startTime = new int[list.size()];
        int [] endTime = new int[list.size()];
        for(int i=0 ;i<list.size();i++){
                String input = list.get(i);
                if(!input.contains(" "))continue;
                String []inputs = input.split(" ");
                if(inputs.length==1)continue;
                startTime[i]=Integer.parseInt(inputs[0]);
                endTime[i]=Integer.parseInt(inputs[1]);
        }
        Arrays.sort(startTime);
        Arrays.sort(endTime);
        //循环查询24小时查询
//        for(int time =1;time<=24;time++){
//           int num = getMaxVisitorNum(startTime,endTime,time);
//           System.out.println("时间为："+(time-1)+"-"+time+"的最大人数是："+num);
//        }
       System.out.println("输入你要查询的时间：1-24小时");
       int time  = scanner.nextInt();
       int num = getMaxVisitorNum(startTime,endTime,time);
       System.out.println("时间为："+(time-1)+"-"+time+"的最大人数是："+num);

//        BufferedReader buf = new BufferedReader(new InputStreamReader(System. in ));
//        System.out.println("输入来访时间与离开时间(0~24)：");
//        System.out.println("范例：10 15");
//        System.out.println("输入-1结束");
//        List<String> list = new ArrayList();
//        while (true) {
//            System.out.print(">>");
//            String input = buf.readLine();
//            if (input.equals("-1")) break;
//            list.add(input);
//        }
//        System.out.print(list);
    }
    private static int getMaxVisitorNum(int[] startTime, int[] endTime, int time){
        int num =0;
        int length = startTime.length;
        for (int i = 0;i<length;i++){
            if(time>startTime[i])num++;
            if(time>endTime[i])num--;
        }
        return num;
    }
    //错误的思维逻辑；会出现时间间隔的问题；属于这时间段的人被误以为不是这个时间段的了。
    private static void  getMaxVisitorByMap(){
        System.out.println("请输入0-24小时到达时间与离开时间:");
        System.out.println("参考格式 10 12 ");
        System.out.println("按q/Q 结束");
        Scanner scanner = new Scanner(System.in);
        Map<Integer,Integer> timeMap = new HashMap<>();
        creatMap(timeMap,1,24);
        while (true){
            System.out.print("-->");
            String input  = scanner.nextLine();
            if(input.equalsIgnoreCase("q"))break;
            if(!input.contains(" "))continue;
            String []inputs = input.split(" ");
            if(inputs.length==1)continue;
            //inputs[0] 为开始时间,inputs[1]为结束时间
            int startTime =Integer.parseInt(inputs[0]);
            int endTime =Integer.parseInt(inputs[1]);
            if(timeMap.containsKey(startTime)){
                timeMap.put(startTime,timeMap.get(startTime)+1);
            }
            if(timeMap.containsKey(endTime)){
                timeMap.put(endTime,timeMap.get(startTime)==0?0:timeMap.get(startTime)-1);
            }
        }
        System.out.println("输入你要查询的时间：1-24小时");
        int time  = scanner.nextInt();
        int num =timeMap.get(time);
        System.out.println("时间为："+(time-1)+"-"+time+"的最大人数是："+num);
    }
    private static  void creatMap(Map<Integer,Integer> timeMap,int startTime,int endTime){
       if(startTime>endTime)return;
       for(int i =startTime;i<=endTime;i++){
           timeMap.put(i,0);
       }
    }
    //主函数
    public static void main(String []args){
        try {
            getMaxVisitor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
