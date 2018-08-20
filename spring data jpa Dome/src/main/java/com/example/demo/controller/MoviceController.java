package com.example.demo.controller;

import com.example.demo.pojo.IMovice80sService;
import com.example.demo.pojo.IMoviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value =  "reptile")
public class MoviceController {
    @Autowired
    private IMoviceService moviceService;
    @Autowired
    private IMovice80sService movice80sService;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{url}")
    public  void saveReptileInfo(@PathVariable String url){
            moviceService.saveIMoviceService(url);
    }
    @GetMapping("/sqw/{url}")
    public  void getReptileSQWInfo(@PathVariable String url){
            moviceService.reptileSQW(url,null);
    }@GetMapping("/downLoad/{url}")
    public  void downLoadMoviceFromUrl(@PathVariable String url){
            moviceService.downLoadMoviceFromUrl("","","");
    }
    @GetMapping("/getReptileInfoList")
    public Object getReptileInfoList(String moviceName){
       return moviceService.findMoviceInfoByMoviceName(moviceName);
    }
    @GetMapping("/80s/{url}")
    public void get80sMovicelist(String url){
        url="https://www.80s.tw/movie/list/q----";
       movice80sService.get80sMovicelist(url);
    }
    @GetMapping("/sq/{url}")
    public void reptileSQW(String url){

       movice80sService.reptileSQW(url,null);
    }
    @GetMapping("/downLoadSQW/")
    public void downLoadSQW(String url,String fileName,String savePath){

        movice80sService.downLoadSQW(null,null,null);
    }
    @GetMapping("reptile/downloadThread/")
    public void downloadThread(String url,String fileName,String savePath){

        movice80sService.downloadThread(url,fileName,savePath);
    }

}
