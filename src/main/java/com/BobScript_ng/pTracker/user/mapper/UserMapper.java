package com.BobScript_ng.pTracker.user.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.BobScript_ng.pTracker.user.dto.ReqUserDto;
import com.BobScript_ng.pTracker.user.dto.ResUserDto;
import com.BobScript_ng.pTracker.user.entity.User;

@Mapper(componentModel = "spring")

public interface UserMapper {

    User toEntity(ReqUserDto reqUserDto);

    ResUserDto toResponse(User user);

    List<ResUserDto> toResponseList(List<User> user);

}
