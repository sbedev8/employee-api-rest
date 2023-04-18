package com.sbedev.employeeapi.service;

import com.sbedev.employeeapi.exception.FileException;
import com.sbedev.employeeapi.model.FileEntity;
import com.sbedev.employeeapi.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Service
public class FileStorageService {
    @Autowired
    private FileRepository fileRepository;

    public FileEntity storeFile(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        String fileType = file.getContentType();

        if(fileType == null || !(fileType.equals("application/pdf") || fileType.startsWith("image/jpeg"))) {
            throw new FileException("Format de fichier non pris en charge");
        }

        FileEntity fileEntity = new FileEntity(fileName, fileType, file.getBytes());
        return fileRepository.save(fileEntity);
    }

    public FileEntity getFile(Long fileId) {
        return fileRepository.findById(fileId).orElseThrow(() -> new FileException("Fichier non trouvé"));
    }
}

