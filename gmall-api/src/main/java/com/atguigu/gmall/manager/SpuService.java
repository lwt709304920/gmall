package com.atguigu.gmall.manager;

import com.atguigu.gmall.bean.PmsProductImage;
import com.atguigu.gmall.bean.PmsProductInfo;
import com.atguigu.gmall.bean.PmsProductSaleAttr;

import java.util.List;

public interface SpuService {
    List<PmsProductInfo> selectProductInfoList(String catalog3Id);


    void insertSpuInfo(PmsProductInfo pmsProductInfo);

    List<PmsProductSaleAttr> selectSaleAttrList(String spuId);

    List<PmsProductImage> selectImageList(String spuId);
}
