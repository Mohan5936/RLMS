package com.serviceIMPL;

import java.util.List;

import com.dto.UserDto;

public interface UserServiceImpl {

	UserDto createUser(UserDto userDto);
    UserDto getUserById(Long id);
    List<UserDto> getAllUsers();
}
