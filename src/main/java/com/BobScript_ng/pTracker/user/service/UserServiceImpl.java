package com.BobScript_ng.pTracker.user.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.BobScript_ng.pTracker.common.exceptionHandler.DuplicationException;
import com.BobScript_ng.pTracker.common.exceptionHandler.ResourceNotFound;
import com.BobScript_ng.pTracker.user.dto.ReqUserDto;
import com.BobScript_ng.pTracker.user.dto.ResUserDto;
import com.BobScript_ng.pTracker.user.entity.User;
import com.BobScript_ng.pTracker.user.mapper.UserMapper;
import com.BobScript_ng.pTracker.user.repository.UserRepo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepo repo;
    private final UserMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
    }

    @Override
    public ResUserDto createUser(ReqUserDto reqUserDto) {

        if (repo.existsByEmail(reqUserDto.getEmail())) {
            throw new DuplicationException("Email already exist choose another one");
        }
        User user = mapper.toEntity(reqUserDto);
        User savedUser = repo.save(user);
        return mapper.toResponse(savedUser);

    }

    @Override
    public Page<ResUserDto> getUsers(Pageable pageable) {
        Page<User> user = repo.findAll(pageable);
        return user.map(mapper::toResponse);

    }

    @Override
    public String deleteUser(long id) {
        Optional<User> findUser = repo.findById(id);
        if (!findUser.isPresent()) {
            throw new ResourceNotFound("User not found with this id" + id);
        }

        repo.deleteById(id);
        return "User deleted";

    }

}
