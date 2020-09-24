package com.ego.service.impl;

import com.ego.commons.utils.FastDFSClient;
import com.ego.service.PicService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class PicServiceImpl implements PicService {
    //    ${}
    @Value("${ego.fastdfs.nginx}")
    private String nginxHost;

    @Override
    public Map<String, Object> update(MultipartFile file) {
        Map<String,Object> map = new HashMap<>();

        try {
            /**
             * file.getInputStream() 获取图片流
             * file.getOriginalFilename() 获取上传图片名称
             * FastDFS 图片会被重新命名，第二个参数的主要含义是扩展名
             */
            String[] result = FastDFSClient.uploadFile(file.getInputStream(), file.getOriginalFilename());
            System.out.println(result[0]+"+++++++"+result[1]);
            map.put("error",0);
            //测试环境和上线环境不一致问题，要使用软编码实现
            //把整个项目的软编码都放在commons中
            map.put("url",nginxHost + result[0] + "/" + result[1]);
            return map;
        } catch (IOException e) {
            e.printStackTrace();
        }

        map.put("error",1);
        map.put("message","错误信息");

        return map;
    }
}
