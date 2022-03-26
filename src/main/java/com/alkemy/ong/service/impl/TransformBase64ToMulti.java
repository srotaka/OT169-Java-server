package com.alkemy.ong.service.impl;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Path;

public class TransformBase64ToMulti implements MultipartFile {



    private final byte[] imgContent;

    public TransformBase64ToMulti(byte[] imgContent) {
        this.imgContent = imgContent;
    }

    @Override
    public String getName() {
        // TODO - implementation depends on your requirements
        return null;
    }

    @Override
    public String getOriginalFilename() {
        // TODO - implementation depends on your requirements
        return null;
    }

    @Override
    public String getContentType() {
        // TODO - implementation depends on your requirements
        return null;
    }

    @Override
    public boolean isEmpty() {
        return imgContent == null || imgContent.length == 0;
    }

    @Override
    public long getSize() {
        return imgContent.length;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return imgContent;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(imgContent);
    }

    @Override
    public void transferTo(File file) throws IOException, IllegalStateException {
        new FileOutputStream(file).write(imgContent);
    }


    @Override
    public void transferTo(Path dest) throws IOException, IllegalStateException {
        MultipartFile.super.transferTo(dest);
    }
}
