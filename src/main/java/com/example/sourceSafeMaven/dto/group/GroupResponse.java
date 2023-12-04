package com.example.sourceSafeMaven.dto.group;

import com.example.sourceSafeMaven.dto.file.FileResponse;
import com.example.sourceSafeMaven.entities.Group;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class GroupResponse {
    private Long id;
    private String name;
    private List<Long> users; // TODO: this should be user object
    private List<FileResponse> files;

    public GroupResponse(Group group) {
        this.id = group.getId();
        this.name = group.getName();
        this.users = group.getUsers().stream().map(user -> user.getId()).toList();
        this.files = group.getTextFiles().stream().map(file -> new FileResponse(file)).toList();
    }
}
