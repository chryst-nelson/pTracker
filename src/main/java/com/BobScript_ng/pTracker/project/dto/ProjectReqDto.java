package com.BobScript_ng.pTracker.project.dto;

import com.BobScript_ng.pTracker.user.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectReqDto {

    private String name;

    private Long ownerId;
}
