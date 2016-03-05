package com.technology.controller;

import com.google.gson.Gson;
import com.technology.model.File;
import com.technology.repository.FileRepository;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: imishev
 * Date: 15-3-26
 * Time: 17:00
 */
@Controller
@RequestMapping(value = "/file")
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileRepository fileRepository;

    @RequestMapping(value = "/list")
    public String fileList(ModelMap model) {
        model.put("files", fileRepository.findAll(new PageRequest(0, 4)));
        return "file/list";
    }

    @RequestMapping(value = "/list/{page}")
    public
    @ResponseBody
    String getNewFiles(@PathVariable(value = "page") String page) {
        Pageable pageable = new PageRequest(Integer.valueOf(page), 4);
        Gson gson = new Gson();
        return gson.toJson(fileRepository.findAll(pageable));
    }

    @RequestMapping(value = "/{id}/download", method = RequestMethod.GET)
    public void download(@PathVariable("id") String id, HttpServletResponse response) {
        File file = fileRepository.findOne(Long.valueOf(id));
        try {
            IOUtils.copy(file.getFile().getBinaryStream(), response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            logger.error("SQLException", e);
        } catch (SQLException e) {
            logger.error("IOException", e);
        }
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody
    public String handleFileUpload(@RequestParam(value = "name") String name,
                                   @RequestParam(value = "file") MultipartFile file) {
        if (!file.isEmpty() || !StringUtils.isEmpty(name)) {
            return "FAILED";
        }

        String extension = file.getContentType();

        if (extension.equals("image/jpeg") || extension.equals("image/png") || extension.equals("video/mp4")) {
            com.technology.model.File tempFile = new com.technology.model.File();
            tempFile.setContentType(File.ContentType.IMAGE);
            tempFile.setName(name);

            try {
                tempFile.setFile(new SerialBlob(file.getBytes()));
            } catch (SQLException e) {
                logger.error("SQLException", e);
            } catch (IOException e) {
                logger.error("IOException", e);
            }

            fileRepository.save(tempFile);

            logger.info("Successfully file uploaded");

            return "SUCCESS";
        } else {
            return "FORMAT";
        }
    }
}