package com.example.sourceSafeMaven.dto.group;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class GroupUsersRequest {
    private Long groupId;
    private List<Long> usersId;
}
