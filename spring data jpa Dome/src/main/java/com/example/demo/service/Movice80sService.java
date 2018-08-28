package com.example.demo.service;

import com.example.demo.utils.HttpRequestUtil;
import com.example.demo.dao.IMovice80sDao;
import com.example.demo.entity.Movie80sInfo;
import com.example.demo.pojo.IMovice80sService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class Movice80sService implements IMovice80sService{
    @Autowired
    private IMovice80sDao movice80sDao;
    @Override
    public void get80sMovicelist(String url) {
        url = "https://www.80s.tw/movie/list/q----";
        Map <String ,Boolean> oldMap = new LinkedHashMap<String ,Boolean>();//存放是否遍历过的url
        Map<String, String> videoLinkMap = new LinkedHashMap<String, String>();//存放视频集合
        String oldLinkHost = ""; // host
        Pattern p = Pattern.compile("(https?://)?[^/\\s]*"); // 比如：http://www.zifangsky.cn
        Matcher m = p.matcher(url);
        if (m.find()) {
            oldLinkHost = m.group();
        }
        oldMap.put(url, false);
        videoLinkMap = crawlLinks(oldLinkHost, oldMap);
        for(String key:videoLinkMap.keySet()){
            Movie80sInfo movie80sInfo =new Movie80sInfo();
            movie80sInfo.setMovieName(key);
            movie80sInfo.setMovieUrl(videoLinkMap.get(key));
            movice80sDao.save(movie80sInfo);
        }
    }
    /**
     * 抓取一个网站所有可以抓取的网页链接，在思路上使用了广度优先算法 对未遍历过的新链接不断发起GET请求， 一直到遍历完整个集合都没能发现新的链接
     * 则表示不能发现新的链接了，任务结束
     *
     * 对一个链接发起请求时，对该网页用正则查找我们所需要的视频链接，找到后存入集合videoLinkMap
     *
     * @param oldLinkHost
     *            域名，如：http://www.zifangsky.cn
     * @param oldMap
     *            待遍历的链接集合
     *
     * @return 返回所有抓取到的视频下载链接集合
     * */
    private Map<String ,String> crawlLinks(String oldLinkHost,Map<String ,Boolean>oldMap){
        Map<String, Boolean> newMap = new LinkedHashMap<String, Boolean>(); // 每次循环获取到的新链接
        Map<String, String> videoLinkMap = new LinkedHashMap<String, String>(); // 视频下载链接
        String oldLink = "";
        for (Map.Entry<String, Boolean> mapping : oldMap.entrySet()) {
            if(!mapping.getValue()){
                oldLink = mapping.getKey();
                String line ="";
               try {
                   //暂时访问回报301
//                   URL url = new URL(oldLink);
//                   HttpURLConnection connection = (HttpURLConnection) url
//                           .openConnection();
//                   connection.setRequestMethod("GET");
////                    connection.setConnectTimeout(6000);
////                    connection.setReadTimeout(6000);
//                   BufferedReader bufferedReader = null;
//                   if (connection.getResponseCode() == 200) {
//                       InputStream inputStream = connection.getInputStream();
//                       bufferedReader  = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
//                   }
                   Document doc = Jsoup.connect(oldLink).get();
//                   Document doc = Jsoup.parse(new URL(oldLink),10000);
                   Elements watchDiv = new Elements();
                   if(doc.getElementsByClass("clearfix noborder block1").size()>0){
                       watchDiv = doc.getElementsByClass("clearfix noborder block1").get(0).getElementsByTag("a");

                   }
                   else if(doc.getElementsByClass("formatblock3-left").size()>0){
                       watchDiv = doc.getElementsByClass("formatblock3-left").get(0).getElementsByTag("a");
                   }
                   else {
                       break;
                   }
                   boolean checkTitle = false;
                   String title = "";
                   Pattern pattern = null;
                   Matcher matcher = null;
                   if(isMoviePage(oldLink)){
                    for(int i =0; i<watchDiv.size();i++){
                        line = watchDiv.get(i).toString();
                       //取出页面中的视频标题
//                       if(!checkTitle){
//                           pattern = Pattern.compile("title=");
//                           matcher = pattern.matcher(line);
//                           if(matcher.find()){
//                               title = matcher.group(1);
//                               checkTitle = true;
//                               continue;
//                           }
//                       }
                       // 取出页面中的视频下载链接
                       pattern = Pattern
                               .compile("(thunder:[^\"]+).*thunder[rR]es[tT]itle=\"[^\"]*\"");
                       matcher = pattern.matcher(line);
                       if (matcher.find()) {
                           title = watchDiv.get(i).attributes().get("title");
                           videoLinkMap.put(title,matcher.group(1));
                           System.out.println("视频名称： "
                                   + title + "  ------  视频链接："
                                   + matcher.group(1));
                           break;  //当前页面已经检测完毕
                       }
                     }
                   }
                   else if(checkUrl(oldLink)){
                       for(int i =0; i<watchDiv.size();i++){
                           line = watchDiv.get(i).toString();
                           pattern = Pattern
                                   .compile("<a href=\"([^\"\\s]*)\"");
                           matcher = pattern.matcher(line);
                           while (matcher.find()) {
                               String newLink = matcher.group(1).trim(); // 链接
                               // 判断获取到的链接是否以http开头
                               if (!newLink.startsWith("http")) {
                                   if (newLink.startsWith("/"))
                                       newLink = oldLinkHost  + newLink;
                                   else
                                       newLink = oldLinkHost + "/" + newLink;
                               }
                               // 去除链接末尾的 /
                               if (newLink.endsWith("/"))
                                   newLink = newLink.substring(0,
                                           newLink.length() - 1);
                               // 去重，并且丢弃其他网站的链接
                               if (!oldMap.containsKey(newLink)
                                       && !newMap.containsKey(newLink)
                                       && (checkUrl(newLink) || isMoviePage(newLink))) {
                                   System.out.println("temp: " + newLink);
                                   newMap.put(newLink, false);
                               }
                           }
                       }
                   }
               }
               catch (Exception e){
                    e.printStackTrace();
//                   crawlLinks(oldLinkHost,oldMap);
               }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                oldMap.replace(oldLink, false, true);
            }
        }
        // 有新链接，继续遍历
        if (!newMap.isEmpty()) {
            oldMap.putAll(newMap);
            videoLinkMap.putAll(crawlLinks(oldLink, oldMap)); // 由于Map的特性，不会导致出现重复的键值对
        }
        return videoLinkMap;
    }
    /**
     * 判断页面是否是电影详情页面
     * @param url  页面链接
     * @return 状态
     * */
    public boolean isMoviePage(String url){
        Pattern pattern =  Pattern.compile("https://www.80s.tw/movie/\\d+");
        Matcher matcher = pattern.matcher(url);
        if(matcher.find())
            return true;  //电影页面
        else
            return false;
    }
    /**
     * 判断是否是2015年的电影列表页面
     * @param url 待检查URL
     * @return 状态
     * */
    public boolean checkUrl(String url){
        Pattern pattern =  Pattern.compile("https://www.80s.tw/movie/list/\\d*");
        Matcher matcher = pattern.matcher(url);
        if(matcher.find())
            return true;  //2015年的列表
        else
            return false;
    }

    @Override
    public void saveSQWMoviceInfo(List<Movie80sInfo> movie80sInfos) {
        movie80sInfos.forEach(movie80sInfo -> {
            if(isExeis(movie80sInfo.getMovieName(),movie80sInfo.getMovieUrl()))
                movice80sDao.save(movie80sInfo);
        });
    }
    private  boolean isExeis(String moviceName,String moviceUrl){
        if(null==movice80sDao.findMovie80sInfoByMovieNameAndMovieUrl(moviceName,moviceUrl))
            return true;
        return false;
    }
    @Override
    public void reptileSQW(String url, List<Movie80sInfo> movieInfoList) {
        List<Movie80sInfo> movieInfoLst = new ArrayList<>();
        url = "https://video.eyny.com/channel/UCXVtaFCKWX";
        try {
            //具体播放的地址 doc.getElementsByTag("video").get(0).childNode(0).attributes().get("src");
            //跳转播放页 doc.getElementsByClass("img_div_width").get(0).getElementsByTag("p").get(0).childNode(0).attributes.get("href")
            //标题：doc.getElementsByClass("img_div_width").get(0).getElementsByTag("p").get(0).childNode(0).childNode(0)
            Document doc = Jsoup.connect(url).get();
            Elements watchDiv = doc.getElementsByClass("img_div_width");
            String baseUri = doc.baseUri();
            for(int i =0;i<watchDiv.size();i++){
                Movie80sInfo movie80sInfo = new Movie80sInfo();
                String moviceName = watchDiv.get(i).getElementsByTag("p").get(0).childNode(0).childNode(0).toString();
                String moviceurl = watchDiv.get(i).getElementsByTag("p").get(0).childNode(0).attributes().get("href");
                movie80sInfo.setMovieUrl(baseUri+moviceurl.substring(1,moviceurl.length()));
                movie80sInfo.setMovieName(moviceName);
                movieInfoLst.add(movie80sInfo);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        saveSQWMoviceInfo(movieInfoLst);
    }

    @Override
    public void downLoadSQW(String url, String fileName, String savePath) {
//        List<Movie80sInfo> movie80sInfos =(List<Movie80sInfo>) movice80sDao.findAll();
//        for(Movie80sInfo movie80sInfo:movie80sInfos){
//             测试地址//https://a417.static-file.com/cache/3d69cjg64jzIe8ed6zzX8I8ARKvHx7c58DmNFeY3ohI9R7ooo2BQAXxopAtoXna5iRJKMUZ44rU%2F/video/05d591cc68a013c5d19924f649a14a3e/5b76556e/mp4/v12/180816/de3f48ca868c93832edc7995ea2e64f1.mp4?s=128
//        }
        fileName ="b.mp4";
        savePath ="d:/resource";
        String src ="";
        System.out.println("单一 开始时间"+System.currentTimeMillis());
        HttpRequestUtil.downLoadMoviceFromUrl(url,fileName,savePath);
        //通过访问页面查询到具体视频的地址
//        try {
//            Document doc = Jsoup.connect(url).get();
//            Elements eVideo =  doc.getElementsByTag("video");
//            if(null!=eVideo&&eVideo.size()>0){
//                src =  eVideo.get(0).childNode(0).attributes().get("src");
//                HttpRequestSevice.downLoadMoviceFromUrl(src,fileName,savePath);
//            }
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
        System.out.println("地址"+src);

    }

    @Override
    public void downloadThread(String url, String fileName, String savePath) {
        url = "https://video.eyny.com/watch?v=SoJpeznCDYu";
        savePath ="d:/resource";
        fileName ="测试.mp4";
        int threadCount =10;
        System.out.println("多线程 开始时间"+System.currentTimeMillis());
        String src ="";
        try {
            Document doc = Jsoup.connect(url).get();
            Elements eVideo =  doc.getElementsByTag("video");
            if(null!=eVideo&&eVideo.size()>0){
                src =  eVideo.get(0).childNode(0).attributes().get("src");
                HttpRequestUtil.downLoadThreadByUrl(src,savePath,threadCount,fileName);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("多线程 结束时间"+System.currentTimeMillis());
    }
}
