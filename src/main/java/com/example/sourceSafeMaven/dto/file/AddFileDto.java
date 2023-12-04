package com.example.sourceSafeMaven.dto.file;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class AddFileDto {
    private MultipartFile file;
    private String fileName;
    private Long groupId;
}
