package com.example.sourceSafeMaven.dto.group;

import com.example.sourceSafeMaven.entities.Group;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class MiniGroupResponse {
    private Long id;
    private String name;

    public MiniGroupResponse(Group group) {
        this.id = group.getId();
        this.name = group.getName();
    }
}
