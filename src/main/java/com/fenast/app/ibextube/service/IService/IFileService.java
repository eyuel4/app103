package com.fenast.app.ibextube.service.IService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by taddesee on 5/8/2018.
 */
public interface IFileService {
    public void storeFile(MultipartFile file);
}
