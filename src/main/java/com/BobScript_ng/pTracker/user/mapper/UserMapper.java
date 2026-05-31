package com.BobScript_ng.pTracker.user.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.BobScript_ng.pTracker.user.dto.ReqUserDto;
import com.BobScript_ng.pTracker.user.dto.ResUserDto;
import com.BobScript_ng.pTracker.user.entity.User;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface UserMapper {

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", ignore = true)
    User toEntity(ReqUserDto reqUserDto);

    ResUserDto toResponse(User user);

    List<ResUserDto> toResponseList(List<User> user);

}
