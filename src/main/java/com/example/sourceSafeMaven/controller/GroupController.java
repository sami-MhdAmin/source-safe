package com.example.sourceSafeMaven.controller;

import com.example.sourceSafeMaven.dto.file.FileResponse;
import com.example.sourceSafeMaven.dto.group.GroupResponse;
import com.example.sourceSafeMaven.dto.group.GroupUsersRequest;
import com.example.sourceSafeMaven.dto.group.MiniGroupResponse;
import com.example.sourceSafeMaven.entities.Group;
import com.example.sourceSafeMaven.entities.Role;
import com.example.sourceSafeMaven.entities.User;
import com.example.sourceSafeMaven.security.JwtService;
import com.example.sourceSafeMaven.service.GroupService;
import com.example.sourceSafeMaven.service.TextFileService;
import com.example.sourceSafeMaven.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private TextFileService textFileService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @GetMapping()
    public List<MiniGroupResponse> getGroups(@RequestHeader HttpHeaders httpHeaders) {
        var groups = groupService.getGroups();
        var groupsResponse = groups.stream().map(group -> new MiniGroupResponse(group)).toList();
        return groupsResponse;
    }

    @GetMapping("/{groupId}")
    public GroupResponse getGroup(@RequestHeader HttpHeaders httpHeaders, @PathVariable Long groupId) {
        var group = groupService.getGroup(groupId);
        var groupResponse = new GroupResponse(group);
        return groupResponse;
    }

    @GetMapping("/{groupId}/files")
    public List<FileResponse> getFiles(@RequestHeader HttpHeaders httpHeaders, @PathVariable Long groupId) {
        var files = textFileService.getFilesByGroupId(groupId);
        var fileResponseList = files.stream().map(file -> new FileResponse(file)).toList();
        return fileResponseList;
    }

    @GetMapping("/v2/{groupId}/files")
    public List<FileResponse> getFilesV2(@RequestHeader HttpHeaders httpHeaders, @PathVariable Long groupId) {
        var group = groupService.getGroup(groupId);
        var fileResponseList = group.getFiles().stream().map(file -> new FileResponse(file)).toList();
        return fileResponseList;
    }

    @PostMapping("/add_users")
    public ResponseEntity<GroupResponse> addUser(@RequestHeader HttpHeaders httpHeaders, @RequestBody GroupUsersRequest groupUsersRequest) {
        Long currentUserId = jwtService.getUserIdByToken(httpHeaders);
        var currentUser = userService.findUserById(currentUserId);
        if (currentUser.getRole() != Role.ADMIN)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        else {
            List<User> users = userService.findUsersByIds(groupUsersRequest.getUsersId());
            var group = groupService.getGroup(groupUsersRequest.getGroupId());
            group.getUsers().addAll(users);
            Group updatedGroup = groupService.updateGroup(group);
            var groupResponse = new GroupResponse(updatedGroup);
            return ResponseEntity.status(HttpStatus.OK).body(groupResponse);
        }
    }

    @PostMapping("/change_name/{groupId}")
    public ResponseEntity<GroupResponse> addUser(@RequestHeader HttpHeaders httpHeaders, @PathVariable Long groupId, @RequestBody String name) {
        var group = groupService.getGroup(groupId);
        group.setName(name);
        Group updatedGroup = groupService.updateGroup(group);
        var groupResponse = new GroupResponse(updatedGroup);
        return ResponseEntity.status(HttpStatus.OK).body(groupResponse);

    }
}
