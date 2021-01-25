package me.weekbelt.mreview.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
public class UploadController {

    @PostMapping("/uploadAjax")
    public void uploadFile(MultipartFile[] uploadFiles) {

        for (MultipartFile uploadFile : uploadFiles) {
            String originalName = uploadFile.getOriginalFilename();
            String fileName = originalName != null ? originalName.substring(originalName.lastIndexOf("\\") + 1) : null;

            log.info("fileName: {}", fileName);
        }
    }
}
