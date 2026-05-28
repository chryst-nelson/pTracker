package com.BobScript_ng.pTracker.user.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.BobScript_ng.pTracker.user.dto.ReqUserDto;
import com.BobScript_ng.pTracker.user.dto.ResUserDto;

public interface UserService {

    ResUserDto createUser(ReqUserDto reqUserDto);

    Page<ResUserDto> getUsers(Pageable pageable);

    String deleteUser(long id);

}
