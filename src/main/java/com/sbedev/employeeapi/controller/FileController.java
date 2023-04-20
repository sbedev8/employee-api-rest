package com.sbedev.employeeapi.controller;

import com.sbedev.employeeapi.model.FileEntity;
import com.sbedev.employeeapi.service.FileStorageService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

@CrossOrigin("*")
@RestController
@RequestMapping("/api/files")
public class FileController {
    @Autowired
    private FileStorageService fileStorageService;

    @ApiOperation(value = "Upload a file", response = FileEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully upload"),
            @ApiResponse(code = 404, message = "Format de fichier non pris en charge ou La taille du fichier ne doit pas dépasser 2 MB ")
    })
    @PostMapping
    public ResponseEntity<FileEntity> uploadFile(@ApiParam(value = "File to upload", required = true) @RequestParam("file") MultipartFile file) throws IOException {
        FileEntity fileEntity = fileStorageService.storeFile(file);
        return ResponseEntity.status(HttpStatus.CREATED).body(fileEntity);
    }

    @ApiOperation(value = "Upload multiple files", response = List.class)
    @PostMapping("/multiple")
    public ResponseEntity<List<FileEntity>> uploadMultiFiles(@ApiParam(value = "Array of files to upload", required = true) @RequestParam("files") MultipartFile[] files) throws IOException {

        List<FileEntity> fileEntities  = Arrays.stream(files).map(file -> {
            try {
                return fileStorageService.storeFile(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.CREATED).body(fileEntities);
    }

    @ApiOperation(value = "Download a file by its ID", response = Resource.class)
    @GetMapping("/{fileId}")
    public ResponseEntity<Resource> downloadFile(@ApiParam(value = "File ID to download", required = true) @PathVariable Long fileId) {
        FileEntity fileEntity = fileStorageService.getFile(fileId);
        ByteArrayResource resource = new ByteArrayResource(fileEntity.getData());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileEntity.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getFileName() + "\"")
                .body(resource);
    }
}

