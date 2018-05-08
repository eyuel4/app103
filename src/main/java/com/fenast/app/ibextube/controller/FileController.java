package com.fenast.app.ibextube.controller;

import com.fenast.app.ibextube.service.IService.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by taddesee on 5/8/2018.
 */

@RestController
@RequestMapping("/ibex/api/user")
public class FileController {
    @Autowired
    private IFileService filesService;

    @RequestMapping(value = "/profile/upload", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            filesService.storeFile(file);
            return "Filed Upload Completed";
        } catch (Exception e) {
            return "Fail to upload profile picture";
        }
    }
}
