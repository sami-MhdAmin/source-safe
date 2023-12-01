package com.example.sourceSafeMaven.controller;

import com.example.sourceSafeMaven.dto.AddFileDto;
import com.example.sourceSafeMaven.entities.TextFile;
import com.example.sourceSafeMaven.repository.TextFileRepository;
import com.example.sourceSafeMaven.security.JwtService;
import com.example.sourceSafeMaven.service.TextFileService;
import com.example.sourceSafeMaven.service.VersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/version")
public class VersionController {

    @Autowired
    private VersionService versionService;
    @Autowired
    private TextFileService textFileService;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private TextFileRepository textFileRepository;

    @PostMapping("/addFile")
    public ResponseEntity<String> addFile(@ModelAttribute AddFileDto request, @RequestHeader HttpHeaders httpHeaders) {
        if (!request.getFile().isEmpty()) {
            Long userId=jwtService.getUserIdByToken(httpHeaders);
            return versionService.addFile(userId, request.getFile(), request.getGroupId(), request.getFileName());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading file");
        }
    }

    @GetMapping("/files")
    public Map<String, Object> getFiles(@RequestHeader HttpHeaders httpHeaders) {
        Long userId=jwtService.getUserIdByToken(httpHeaders);
        Map<String, Object> groups =  textFileService.getFiles(userId);
        return groups;
    }

        @GetMapping("/download/{fileId}/{versionId}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long fileId,
                                               @PathVariable Long versionId,
                                               @RequestHeader HttpHeaders httpHeaders)
                throws FileNotFoundException {
//        Long userId=jwtService.getUserIdByToken(httpHeaders);
            System.out.println("i am sami");

        byte[] versionBytes = versionService.getVersionBytes(versionId);
        Optional<TextFile> textFile = textFileRepository.findById(fileId);
        String fileName = textFile.get().getFileName();
        fileName.concat(".txt");
            System.out.println("file name is " + fileName);
        // Set up the HttpHeaders
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", fileName+".txt"); // Set the desired file name

        return new ResponseEntity<>(versionBytes, headers, HttpStatus.OK);
    }
}
