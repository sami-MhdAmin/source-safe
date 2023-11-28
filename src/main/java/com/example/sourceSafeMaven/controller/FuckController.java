package com.example.sourceSafeMaven.controller;

import com.example.sourceSafeMaven.entities.Group;
import com.example.sourceSafeMaven.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping
public class FuckController {

    @Autowired
    private GroupRepository groupRepository;


    @PostMapping("/groups")
    public Group getCustomers() {
        Group group = new Group();
        groupRepository.save(group);
        return group;
    }

    @GetMapping("/getGroups")
    public List<Group> getCustomerse() {
        return groupRepository.findAll();
    }

}
