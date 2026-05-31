package com.BobScript_ng.pTracker.user.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.BobScript_ng.pTracker.user.dto.ResUserDto;

public interface UserService {

    Page<ResUserDto> getUsers(Pageable pageable);

    String deleteUser(long id);

}
