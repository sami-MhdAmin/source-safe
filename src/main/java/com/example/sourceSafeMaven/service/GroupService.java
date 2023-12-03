package com.example.sourceSafeMaven.service;

import com.example.sourceSafeMaven.entities.Group;
import com.example.sourceSafeMaven.repository.GroupRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public Group getGroup(Long id) {
        return groupRepository.findById(id).orElse(null);
    }

    public List<Group> getGroups() {
        return groupRepository.findAll();
    }

    @Transactional
    public Group updateGroup(Group group) {
        return groupRepository.saveAndFlush(group);
    }
}
