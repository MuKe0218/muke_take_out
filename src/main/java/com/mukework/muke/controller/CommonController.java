package com.mukework.muke.controller;

import com.mukework.muke.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/common")
@Slf4j
//文件上传下载
public class CommonController {

    @Value("${muke.path}")
    private String basePath;
    //文件上传
    @PostMapping("/upload")
    public R<String> upload(MultipartFile file){
        String originalFilename = file.getOriginalFilename();
        //获取文件格式
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        //生产uuid
        String fileName = UUID.randomUUID().toString()+suffix;
        //判断目录是否存在 不存在 给它创建目录
        File dir = new File(basePath);
        if (!dir.exists()){
            dir.mkdirs();
        }
        try {
            //文件转存
            file.transferTo(new File(basePath+fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.success(fileName);
    }
    //文件下载
    @GetMapping("/download")
    public void download(HttpServletResponse httpServletResponse,String name){
        //输入流 读文件
        //输出流 写会浏览器
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(basePath+name));
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = fileInputStream.read(bytes)) != -1){
                servletOutputStream.write(bytes,0,len);
                servletOutputStream.flush();
            }
            servletOutputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
