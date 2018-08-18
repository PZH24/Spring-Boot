package com.example.demo.pojo;

import com.example.demo.entity.MovieInfo;

import java.util.List;

public interface IMoviceService {
    void reptileHttp(String url, List<MovieInfo> movieInfoList);
    void saveIMoviceService(String url);
    List<MovieInfo> findMoviceInfoByMoviceName(String filmName);
    void reptileSQW(String url,List<MovieInfo> movieInfoList);
    void downLoadMoviceFromUrl(String url,String fileName,String savePath);
}
