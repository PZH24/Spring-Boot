package com.example.demo.service;

import com.example.demo.utils.HttpRequestUtil;
import com.example.demo.dao.IMovieDao;
import com.example.demo.entity.MovieInfo;
import com.example.demo.pojo.IMoviceService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class MoviceService implements IMoviceService {
    @Autowired
    private IMovieDao movieDao;
    private Integer finalPage ;
    private  Integer statrPage = 1;
    @Override
    public void reptileHttp(String url, List<MovieInfo> movieInfoList) {
//              String  url = "https://www.jianshu.com/search?q="+type+"&page="+PAGE_INIT+"&type=note";
        try {
            Document doc = Jsoup.connect(url).get();
            Elements catalogDivs = doc.getElementsByClass("index-area clearfix");
            if(null==catalogDivs&&catalogDivs.size()==0){

            }
            Elements pageDiv = doc.getElementsByClass("pages");
            String pageStr ="";
            if(pageDiv.size()>0){
                pageStr = pageDiv.get(0).getElementsByTag("span").get(1).childNode(0).toString();
            }
            int lastint = pageStr.lastIndexOf("页");
            if(lastint>0&&null==finalPage)
                finalPage = Integer.parseInt(pageStr.substring(0,lastint));
            statrPage+=1;
            for(int i=0;i<catalogDivs.size();i++){
                String catalogUrl = catalogDivs.get(i).getElementsByTag("a").get(0).attributes().get("href");
                Elements sMackDivs =catalogDivs.get(i).getElementsByClass("sMark");
                String catalogName =sMackDivs.size()>0?sMackDivs.get(0).childNodes().get(0).toString():doc.getElementsByClass("sMark").get(0).childNode(0).toString();
                Elements movieLI = catalogDivs.get(i).getElementsByTag("li");
                for(int j =0 ;j<movieLI.size();j++){
                    MovieInfo movieInfo = new MovieInfo();
                    String movieName =movieLI.get(j).getElementsByTag("a").get(0).attributes().get("title");
                    String movieUrl = movieLI.get(j).getElementsByTag("a").get(0).attributes().get("href");
                    String imgUrl =movieLI.get(j).getElementsByTag("img").get(0).attributes().get("src");
                    movieInfo.setCatalogName(catalogName);
                    movieInfo.setCatalogUrl(catalogUrl);
                    movieInfo.setCatalog(url);
                    movieInfo.setImgUrl(imgUrl);
                    movieInfo.setMovieName(movieName);
                    movieInfo.setMovieUrl("http://www.q2002.com"+movieUrl);
                    movieInfoList.add(movieInfo);
                }
                //获取q2002的分类
                //doc.getElementsByClass("index-area clearfix").get(0).getElementsByClass("sMark").get(0).childNodes.get(0)
                //获取更多url
                //doc.getElementsByClass("aMore").get(0).getElementsByTag("a").get(0).attributes().get("href")
                //具体某一部电影的url doc.getElementsByClass("index-area clearfix").get(0).getElementsByTag("li").get(0).getElementsByTag("a").get(0).attributes.get("href")
                //具体某一部电影的标题：doc.getElementsByClass("index-area clearfix").get(0).getElementsByTag("li").get(0).getElementsByTag("a").get(0).attributes.get("title")
            }

        }
        catch (Exception e){

        }
    }

    @Override
    public void saveIMoviceService(String url) {
//        https://video.eyny.com/video
        List<MovieInfo> movieInfos = new ArrayList<>();
        String suffix= "type/2/"+statrPage+".html";
        url = "http://www.q2002.com/"+suffix;
        try {
                reptileHttp(url,movieInfos);
            if(null!=finalPage&&statrPage<finalPage)
                reptileHttp(url,movieInfos);
        }
            catch (Exception e){
                e.printStackTrace();
        }
        movieInfos.forEach(movieInfo -> {
            if(isExist(movieInfo.getMovieName(),movieInfo.getMovieUrl()))
                movieDao.save(movieInfo);
        });

    }

    @Override
    public List<MovieInfo> findMoviceInfoByMoviceName(String moviceName) {
        if(null==moviceName)
            moviceName="";
        moviceName  = "%"+moviceName+"%";
       return movieDao.findByMovieNameLikeOrderByIdAsc(moviceName);
    }

    @Override
    public void reptileSQW(String url, List<MovieInfo> movieInfoList) {
//        url ="https://video.eyny.com/watch?v=sDVH7TRqR0h";
        try {
            //具体播放的地址 doc.getElementsByTag("video").get(0).childNode(0).attributes().get("src");
            //跳转播放页 doc.getElementsByClass("img_div_width").get(0).getElementsByTag("p").get(0).childNode(0).attributes.get("href")
            //标题：doc.getElementsByClass("img_div_width").get(0).getElementsByTag("p").get(0).childNode(0).childNode(0)
            Document doc = Jsoup.connect(url).get();
            Elements watchDiv = doc.getElementsByClass("img_div_width");
            for(int i =0;i<watchDiv.size();i++){

            }
//            URL realUrl = new URL(url);
//            HttpURLConnection connection = (HttpURLConnection)realUrl.openConnection();
//            connection.setRequestProperty("accept", "*/*");
//            connection.setRequestProperty("connection", "Keep-Alive");
//            connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//            // 建立实际的连接
//            connection.connect();
//            InputStream is=connection.getInputStream();
//            ByteArrayOutputStream baos=new ByteArrayOutputStream();
//            //10MB的缓存
//            byte [] buffer=new byte[10485760];
//            int len=0;
//            while((len=is.read(buffer))!=-1){
//                baos.write(buffer, 0, len);
//            }
//            String jsonString=baos.toString();
//            baos.close();
//            is.close();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void downLoadMoviceFromUrl(String urlStr,String fileName,String savePath) {
//        urlStr ="https://video.eyny.com/watch?v=sDVH7TRqR0h";
        urlStr ="https://a210.static-file.com/cache/9d1d034PFHyubtvo0QxmyPjSQUWZDnthzrmS3FT5UUftgpP9j40trSySptbYr5PPecgwOjNeqlB5/video/995b59e24f4fa37b69d8212ffaf70919/5b74e96c/mp4/v12/180815/6cf6b667c45c58862f101f243df3e48a.mp4?s=128";
        fileName = "a.mp4";
        savePath = "d:/resource";
        HttpRequestUtil.downLoadMoviceFromUrl(urlStr,fileName,savePath);

    }
    private boolean isExist(String moiveName,String url){
        List<MovieInfo> movieInfolst = movieDao.findMovieInfoByMovieNameAndMovieUrl(moiveName,url);
        if(null==movieInfolst||movieInfolst.size()==0)
            return true;
        return false;
    }

}
