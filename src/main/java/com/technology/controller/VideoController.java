package com.technology.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Slf4j
@Controller
public class VideoController {

    @RequestMapping(value = "/video", method = RequestMethod.GET)
    public String home() {
        return "video";
    }

    @RequestMapping(value = "/videoStream", method = RequestMethod.GET)
    public void videoStream(HttpServletResponse response) throws IOException {
        String filePath = "E://Deadpool.mp4";

        int fileSize = (int) new File(filePath).length();

        response.setContentLength(fileSize);
        response.setContentType("video");
        response.setBufferSize(fileSize);

        FileInputStream inputStream = new FileInputStream(filePath);
        ServletOutputStream outputStream = response.getOutputStream();

        IOUtils.copy(inputStream, outputStream);

        IOUtils.closeQuietly(inputStream);
        IOUtils.closeQuietly(outputStream);

        response.setStatus(HttpServletResponse.SC_OK);
    }
}