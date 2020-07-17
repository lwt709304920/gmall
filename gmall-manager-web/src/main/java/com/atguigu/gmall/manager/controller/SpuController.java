package com.atguigu.gmall.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.gmall.bean.PmsProductImage;
import com.atguigu.gmall.bean.PmsProductInfo;
import com.atguigu.gmall.bean.PmsProductSaleAttr;
import com.atguigu.gmall.manager.SpuService;
import com.atguigu.gmall.manager.util.PmsUploadUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin
@RestController
public class SpuController {

    @Reference
    SpuService spuService;

    @RequestMapping("/spuList")
    public List<PmsProductInfo> spuList(String catalog3Id){
        List<PmsProductInfo> productInfoList=spuService.selectProductInfoList(catalog3Id);
        return productInfoList;
    }

    @RequestMapping("/saveSpuInfo")
    public String saveSpuInfo(@RequestBody PmsProductInfo pmsProductInfo){
        spuService.insertSpuInfo(pmsProductInfo);
        return "success";
    }

    @RequestMapping("/fileUpload")
    public String fileUpload(@RequestParam("file") MultipartFile multipartFile){
        //将图片或者音频上传到分布式文件存储系统
        String imgUrl = PmsUploadUtil.uploadImage(multipartFile);
        System.out.println(imgUrl);
        //将图片的存储路径返还给页面
        return imgUrl;
    }

    @RequestMapping("/spuSaleAttrList")
    public List<PmsProductSaleAttr> spuSaleAttrList(String spuId){
        return spuService.selectSaleAttrList(spuId);
    }

    @RequestMapping("/spuImageList")
    public List<PmsProductImage> spuImageList(String spuId){
        List<PmsProductImage> productImages=spuService.selectImageList(spuId);
        return productImages;
    }

}
