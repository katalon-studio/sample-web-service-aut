package com.katalon.wsaut.api.controller;

import com.katalon.wsaut.config.constant.Resources;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@RestController
public class FileController {

    @PostMapping(value = Resources.File.UPLOAD,
                consumes = {
                    MediaType.IMAGE_PNG_VALUE,
                    MediaType.IMAGE_JPEG_VALUE
                },
                produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, String> singleFileUpload(@RequestBody byte[] file, HttpServletRequest request) {
        String contentType = request.getHeader(HttpHeaders.CONTENT_TYPE);

        Map<String, String> result = new HashMap<>();
        result.put("fileSize", FileUtils.byteCountToDisplaySize(file.length));
        result.put("contentType", contentType);
        return result;
    }
}
