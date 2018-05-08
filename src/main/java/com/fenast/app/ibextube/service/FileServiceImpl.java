package com.fenast.app.ibextube.service;

import com.fenast.app.ibextube.service.IService.IFileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by taddesee on 5/8/2018.
 */

@Service
@Transactional
public class FileServiceImpl implements IFileService {
    private final Path rootLocation = Paths.get("ProfilePictureStore");

    public void storeFile(MultipartFile file) {
        try {
            System.out.println(file.getOriginalFilename());
            System.out.println(rootLocation.toUri());
            Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
        } catch (Exception e){
            throw new RuntimeException("FAIL!");
        }

    }
}
