package com.example.sourceSafeMaven.service;

import com.example.sourceSafeMaven.dto.group.AddGroupRequest;
import com.example.sourceSafeMaven.entities.Group;
import com.example.sourceSafeMaven.entities.User;
import com.example.sourceSafeMaven.repository.GroupRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public Group getGroupById(Long id) {
        return groupRepository.findById(id).orElse(null);
    }

    public List<Group> getGroups() {
        return groupRepository.findAll();
    }
    public List<Group> getGroupNotMember(User user) {
        return groupRepository.findByUsersNotContaining(user);
    }


    @Transactional
    public Group updateGroup(Group group) {
        return groupRepository.saveAndFlush(group);
    }

    public Group addGroup(AddGroupRequest name){
        Group group = new Group();
        group.setName(name.getName());
        groupRepository.save(group);
        return group;
    }

}
