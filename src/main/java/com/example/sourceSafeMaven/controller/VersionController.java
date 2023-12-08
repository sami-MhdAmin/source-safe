package com.example.sourceSafeMaven.controller;

import com.example.sourceSafeMaven.dto.file.AddFileDto;
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
import java.util.List;
import java.util.Map;

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
//sa

    @PostMapping(path = "/addFile",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<String> addFile(@ModelAttribute AddFileDto request, @RequestHeader HttpHeaders httpHeaders) {
        if (!request.getFile().isEmpty()) {
            Long userId = jwtService.getUserIdByToken(httpHeaders);
            return versionService.addFile(userId, request.getFile(), request.getGroupId(), request.getFileName());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading file");
        }
    }

    @GetMapping("/files")
    public Map<String, Object> getFiles(@RequestHeader HttpHeaders httpHeaders) {
        Long userId = jwtService.getUserIdByToken(httpHeaders);
        Map<String, Object> groups = textFileService.getFiles(userId);
        return groups;
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<?> downloadFile(@PathVariable Long fileId,
                                               @RequestHeader HttpHeaders httpHeaders)
            throws FileNotFoundException {

        Long userId = jwtService.getUserIdByToken(httpHeaders);


        byte[] versionBytes = versionService.download(userId,fileId);
        if (versionBytes!=null) {
            // Set up the HttpHeaders
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "file.txt");

            return new ResponseEntity<>(versionBytes, headers, HttpStatus.OK);
        }
        else {
            return  ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not authorized to download this file");
        }
    }

    @GetMapping("/fileVersions/{fileId}")
    public ResponseEntity<?> fileVersions(@RequestHeader HttpHeaders httpHeaders, @PathVariable Long fileId) throws FileNotFoundException {
        Long userId = jwtService.getUserIdByToken(httpHeaders);
        List<Map<String, Object>> versions = versionService.fileVersions(userId,fileId);
        if(versions!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(versions);
        }
        else
        {
            return  ResponseEntity.status(HttpStatus.FORBIDDEN).body("You don't have the authorization to access the file's versions");

        }
    }

}
