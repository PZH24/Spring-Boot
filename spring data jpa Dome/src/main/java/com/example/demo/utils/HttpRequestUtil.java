package com.example.demo.utils;

import com.example.demo.thread.DownLoadThread;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequestUtil {
    /***
     * 简单的url请求
     * */
    public static Object getHttpUrlInputStream(String path,String type) {
        BufferedReader  bufferedReader = null;
        try {
            URL url = new URL(path); // 把字符串转换为URL请求地址
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();// 打开连接
            connection.setReadTimeout(150000);
            connection.setConnectTimeout(10000);
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 5.1;SV1)");
            connection.setRequestProperty("Accept-Charset", "utf-8");
            connection.setDoInput(true);
            connection.connect();// 连接会话
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), type));
            StringBuffer sb = new StringBuffer();
            char[] cBuf = new char[1024];
            int iLen;
            while ((iLen = bufferedReader.read(cBuf)) >= 0){
                sb.append(cBuf, 0, iLen);
            }
            bufferedReader.close();
            return  sb.toString();
        }
        catch (Exception e){
            if(e.getMessage().lastIndexOf("no protocol")!=-1){
                return null;
            }
            else{
                e.printStackTrace();
            }
        }
        return  null;
    }
    public static BufferedReader connectionHttp(String baseurl) throws Exception{
        URL url = new URL(baseurl); // 把字符串转换为URL请求地址
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();// 打开连接
        connection.setReadTimeout(150000);
        connection.setConnectTimeout(10000);
        connection.setRequestProperty("connection", "Keep-Alive");
        connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 5.1;SV1)");
        connection.setRequestProperty("Accept-Charset", "utf-8");
        connection.setDoInput(true);
        connection.connect();
        if(connection.getResponseCode()!=200)
            return null;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
        return bufferedReader;
    }
    public  static void downLoadMoviceFromUrl(String urlStr,String fileName,String savePath) {
//        https://video.eyny.com//watch?v=EuJbfHUB7wP
        //        urlStr ="https://video.eyny.com/watch?v=sDVH7TRqR0h";
        try {
            HttpURLConnection conn =connection(urlStr);
           int size = conn.getContentLength();
            //防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            System.out.println("下载开始时间"+System.currentTimeMillis());
            //得到输入流
            InputStream inputStream = conn.getInputStream();
            //获取自己数组
            byte[] getData = readInputStream(inputStream);
            System.out.println("下载结束时间"+System.currentTimeMillis());
            //文件保存位置
            File saveDir = new File(savePath);
            if(!saveDir.exists()){
                saveDir.mkdir();
            }
            File file = new File(saveDir+File.separator+fileName);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(getData);
            if(fos!=null){
                fos.close();
            }
            if(inputStream!=null){
                inputStream.close();
            }
            System.out.println("info:"+urlStr+" download success");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 从输入流中获取字节数组
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static  byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }
    /**
     * 多线程下载文件
     *
     * */
    public static  void downLoadThreadByUrl(String urlStr,String savePath,int threadCount,String fileName){
        HttpURLConnection conn = connection(urlStr);
        if(conn==null)return;
        System.out.println("下载开始时间"+System.currentTimeMillis());
        try {
            int length=conn.getContentLength();//返回文件大小
            //占据文件空间
            File saveDir = new File(savePath);
            if(!saveDir.exists()){
                saveDir.mkdir();
            }
            File file = new File(saveDir+File.separator+fileName);
            RandomAccessFile mAccessFile=new RandomAccessFile(file, "rwd");//"rwd"可读，可写
            mAccessFile.setLength(length);//占据文件的空间
            int size=length/threadCount;
            for (int id = 0; id < threadCount; id++) {
                //1、确定每个线程的下载区间
                //2、开启对应子线程下载
                int startIndex=id*size;
                int endIndex=(id+1)*size-1;
                if (id==threadCount-1) {
                    endIndex=length-1;
                }
                System.out.println("第"+id+"个线程的下载区间为"+startIndex+"--"+endIndex);
                new DownLoadThread(startIndex, endIndex, urlStr, id,savePath).start();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("下载开始时间"+System.currentTimeMillis());
    }
    private static  HttpURLConnection connection(String urlStr) {
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            //设置超时间为3秒
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);
            conn.connect();
            if(conn.getResponseCode()==200||conn.getResponseCode()==206)
            return conn;
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
