package com.example.sourceSafeMaven.dto.file;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
@Setter
@Getter
public class CheckOutFileDto {
    private MultipartFile file;
    private Long fileId;

}
