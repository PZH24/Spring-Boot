package com.example.demo.thread;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownLoadThread extends Thread {
    private int startIndex,endIndex,threadId;
    private String urlString;
    private String savePath;
    public DownLoadThread(int startIndex,int endIndex,String urlString,int threadId,String savePath) {
        this.endIndex=endIndex;
        this.startIndex=startIndex;
        this.urlString=urlString;
        this.threadId=threadId;
        this.savePath = savePath;
    }
    @Override
    public void run() {
        RandomAccessFile mAccessFile =null;
        InputStream is = null;
        try {
            URL url=new URL(urlString);
            HttpURLConnection conn=(HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(8000);
            conn.setReadTimeout(8000);
            conn.setRequestProperty("Range", "bytes="+startIndex+"-"+endIndex);//设置头信息属性,拿到指定大小的输入流
            if (conn.getResponseCode()==206) {//拿到指定大小字节流，由于拿到的使部分的指定大小的流，所以请求的code为206
                is=conn.getInputStream();
                File saveDir = new File(savePath);
                if(!saveDir.exists()){
                    saveDir.mkdir();
                }
                File file = new File(saveDir+File.separator+"测试.mp4");
                 mAccessFile=new RandomAccessFile(file, "rwd");//"rwd"可读，可写
                mAccessFile.seek(startIndex);//表示从不同的位置写文件
                byte[] bs=new byte[1024];
                int len=0;
                int current=0;
                while ((len=is.read(bs))!=-1) {
                    mAccessFile.write(bs,0,len);
                    current+=len;
                    System.out.println("第"+threadId+"个线程下载了"+current);

                }
                mAccessFile.close();
                System.out.println("第"+threadId+"个线程下载完毕");
                is.close();
            }
            else {
                DownLoadThread.interrupted();
            }
        } catch (Exception e) {
            if(e.getMessage().indexOf("connect timed out")!=-1||e.getMessage().indexOf("Read timed out")!=-1){
             new DownLoadThread(startIndex, endIndex, urlString, threadId, savePath).start();
            }
            e.printStackTrace();
        }
        finally {
            try {
                if(mAccessFile!=null){
                mAccessFile.close();
                }
                if(is!=null){
                is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.run();
    }

}
