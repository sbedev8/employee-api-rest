package com.sbedev.employeeapi.controller;

import com.sbedev.employeeapi.model.FileEntity;
import com.sbedev.employeeapi.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/files")
public class FileController {
    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping
    public ResponseEntity<FileEntity> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        FileEntity fileEntity = fileStorageService.storeFile(file);
        return ResponseEntity.status(HttpStatus.CREATED).body(fileEntity);
    }

    @PostMapping("/multiple")
    public ResponseEntity<List<FileEntity>> uploadMultiFiles(@RequestParam("files") MultipartFile[] files) throws IOException {

        List<FileEntity> fileEntities  = Arrays.stream(files).map(file -> {
            try {
                return fileStorageService.storeFile(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.CREATED).body(fileEntities);
    }

    @GetMapping("/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long fileId) {
        FileEntity fileEntity = fileStorageService.getFile(fileId);
        ByteArrayResource resource = new ByteArrayResource(fileEntity.getData());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileEntity.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getFileName() + "\"")
                .body(resource);
    }
}

