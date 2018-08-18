package com.example.demo.pojo;

import com.example.demo.entity.Movie80sInfo;

import java.util.List;

/**
 * 通过正则表达式完成爬虫
 * */

public interface IMovice80sService {
    void get80sMovicelist(String url);
    void  saveSQWMoviceInfo(List<Movie80sInfo> movie80sInfos);
    void reptileSQW(String url, List<Movie80sInfo> movieInfoList);
    void downLoadSQW(String url,String fileName,String savePath);
    void downloadThread(String url,String fileName,String savePath);
}
