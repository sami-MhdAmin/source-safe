package com.example.sourceSafeMaven.dto.group;

import com.example.sourceSafeMaven.dto.file.FileResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class GroupDetailsResponse {

    private List<FileResponse> files;

    public GroupDetailsResponse(List<FileResponse> files) {
        this.files = files;
    }

}
