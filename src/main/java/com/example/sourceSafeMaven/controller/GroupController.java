package com.example.sourceSafeMaven.controller;

import com.example.sourceSafeMaven.dto.file.FileResponse;
import com.example.sourceSafeMaven.dto.group.GroupResponse;
import com.example.sourceSafeMaven.service.GroupService;
import com.example.sourceSafeMaven.service.TextFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private TextFileService textFileService;

    @GetMapping()
    public List<GroupResponse> getGroups(@RequestHeader HttpHeaders httpHeaders) {
        var groups = groupService.getGroups();
        var groupsResponse = groups.stream().map(group -> new GroupResponse(group)).toList();
        return groupsResponse;
    }

    @GetMapping("/{groupId}")
    public List<FileResponse> getFiles(@RequestHeader HttpHeaders httpHeaders, @PathVariable Long groupId) {
        var files = textFileService.getFilesByGroupId(groupId);
        var fileResponseList = files.stream().map(file -> new FileResponse(file)).toList();
        return fileResponseList;
    }

    @GetMapping("/v2/{groupId}")
    public List<FileResponse> getFilesV2(@RequestHeader HttpHeaders httpHeaders, @PathVariable Long groupId) {
        var group = groupService.getGroup(groupId);
        var fileResponseList = group.getFiles().stream().map(file -> new FileResponse(file)).toList();
        return fileResponseList;
    }
}
