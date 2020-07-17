package com.atguigu.gmall.manager.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.gmall.bean.*;
import com.atguigu.gmall.manager.SpuService;
import com.atguigu.gmall.manager.mapper.ProductImageMapper;
import com.atguigu.gmall.manager.mapper.ProductInfoMapper;
import com.atguigu.gmall.manager.mapper.ProductSaleAttrMapper;
import com.atguigu.gmall.manager.mapper.ProductSaleAttrValueMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class SpuServiceImpl implements SpuService {
    @Autowired
    ProductInfoMapper productInfoMapper;
    @Autowired
    ProductSaleAttrMapper productSaleAttrMapper;
    @Autowired
    ProductSaleAttrValueMapper productSaleAttrValueMapper;
    @Autowired
    ProductImageMapper productImageMapper;

    @Override
    public List<PmsProductInfo> selectProductInfoList(String catalog3Id) {
        PmsProductInfo pmsProductInfo = new PmsProductInfo();
        return productInfoMapper.select(pmsProductInfo);
    }

    @Override
    public void insertSpuInfo(PmsProductInfo pmsProductInfo) {
        String id=pmsProductInfo.getId();
        if(id==null||id.length()==0){
            productInfoMapper.insertSelective(pmsProductInfo);
            for (PmsProductSaleAttr pmsProductSaleAttr:pmsProductInfo.getSpuSaleAttrList()) {
                pmsProductSaleAttr.setProductId(pmsProductInfo.getId());
                productSaleAttrMapper.insertSelective(pmsProductSaleAttr);
                for (PmsProductSaleAttrValue pmsProductSaleAttrValue:pmsProductSaleAttr.getSpuSaleAttrValueList()){
                    pmsProductSaleAttrValue.setProductId(pmsProductInfo.getId());
                    pmsProductSaleAttrValue.setSaleAttrId(pmsProductSaleAttr.getSaleAttrId());
                    productSaleAttrValueMapper.insertSelective(pmsProductSaleAttrValue);
                }
            }
            for (PmsProductImage pmsProductImage:pmsProductInfo.getSpuImageList()){
                pmsProductImage.setProductId(pmsProductInfo.getId());
                productImageMapper.insertSelective(pmsProductImage);
            }
        }else{
            productInfoMapper.updateByPrimaryKeySelective(pmsProductInfo);

            PmsProductSaleAttr pmsProductSaleAttr = new PmsProductSaleAttr();
            pmsProductSaleAttr.setProductId(pmsProductInfo.getId());
            productSaleAttrMapper.delete(pmsProductSaleAttr);

            PmsProductSaleAttrValue pmsProductSaleAttrValue=new PmsProductSaleAttrValue();
            pmsProductSaleAttrValue.setProductId(pmsProductInfo.getId());
            productSaleAttrValueMapper.delete(pmsProductSaleAttrValue);

            PmsProductImage pmsProductImage=new PmsProductImage();
            pmsProductImage.setProductId(pmsProductInfo.getId());
            productImageMapper.delete(pmsProductImage);

            for (PmsProductSaleAttr pmsProductSaleAttr1:pmsProductInfo.getSpuSaleAttrList()) {
                pmsProductSaleAttr.setProductId(pmsProductInfo.getId());
                productSaleAttrMapper.insertSelective(pmsProductSaleAttr1);
                for (PmsProductSaleAttrValue pmsProductSaleAttrValue1:pmsProductSaleAttr.getSpuSaleAttrValueList()){
                    pmsProductSaleAttrValue.setProductId(pmsProductInfo.getId());
                    pmsProductSaleAttrValue.setSaleAttrId(pmsProductSaleAttr.getSaleAttrId());
                    productSaleAttrValueMapper.insertSelective(pmsProductSaleAttrValue1);
                }
            }
            for (PmsProductImage pmsProductImage1:pmsProductInfo.getSpuImageList()){
                pmsProductImage.setProductId(pmsProductInfo.getId());
                productImageMapper.insertSelective(pmsProductImage1);
            }

        }

    }

    @Override
    public List<PmsProductSaleAttr> selectSaleAttrList(String spuId) {
        PmsProductSaleAttr productSaleAttr = new PmsProductSaleAttr();
        productSaleAttr.setProductId(spuId);
        List<PmsProductSaleAttr> productSaleAttrs = productSaleAttrMapper.select(productSaleAttr);
        for (PmsProductSaleAttr pmsProductSaleAttr:productSaleAttrs){
            PmsProductSaleAttrValue pmsProductSaleAttrValue = new PmsProductSaleAttrValue();
            pmsProductSaleAttrValue.setSaleAttrId(pmsProductSaleAttr.getSaleAttrId());
            List<PmsProductSaleAttrValue> productSaleAttrValues = productSaleAttrValueMapper.select(pmsProductSaleAttrValue);
            pmsProductSaleAttr.setSpuSaleAttrValueList(productSaleAttrValues);
        }
        return productSaleAttrs;
    }

    @Override
    public List<PmsProductImage> selectImageList(String spuId) {
        PmsProductImage productImage = new PmsProductImage();
        productImage.setProductId(spuId);
        List<PmsProductImage> productImages = productImageMapper.select(productImage);
        return productImages;
    }

}
