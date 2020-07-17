package com.atguigu.gmall.manager.util;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public class PmsUploadUtil {

    public static String uploadImage(MultipartFile multipartFile){
        String imgUrl = "";
        try {
            String tracker=PmsUploadUtil.class.getResource("/tracker.conf").getPath();
            ClientGlobal.init(tracker);
            TrackerClient trackerClient = new TrackerClient();
            //获得一个trackerServer的实例
            TrackerServer trackerServer = trackerClient.getTrackerServer();
            //通过tracker获得一个storager连接客户端
            StorageClient storageClient = new StorageClient(trackerServer,null);
            //获得上传的二进制对象
            byte[] bytes = multipartFile.getBytes();
            String originalFilename = multipartFile.getOriginalFilename();//a.jgp
            int i = originalFilename.lastIndexOf(".");

            String[] jpgs = storageClient.upload_appender_file(bytes, "jpg", null);
             imgUrl="http://192.168.1.129";
            for (String jpg:jpgs) {
                imgUrl += "/" +jpg;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imgUrl;
    }
}
