package com.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Entity.Role;
import com.Entity.User;
import com.Exception.ResourceNotFoundException;
import com.dto.UserDto;
import com.repository.userRepository;
import com.serviceIMPL.UserServiceImpl;

@Service
public class userService implements UserServiceImpl {

	private final userRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Constructor Injection
    public userService(userRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        // 1. Check if email is already taken
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new RuntimeException("Email is already registered!");
        }

        // 2. Map DTO to Entity
        User user = new User();
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setEmail(userDto.getEmail());
        user.setPhonenumber(userDto.getPhonenumber());
        
        // 3. ENCRYPT THE PASSWORD
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        // Set default role to USER if none is provided
        if (userDto.getRole() == null) {
            user.setRole(Role.USER);
        } else {
            user.setRole(userDto.getRole());
        }

        // 4. Save to Database
        User savedUser = userRepository.save(user);

        // 5. Return mapped DTO (but hide the password!)
        return mapToDto(savedUser);
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return mapToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // Helper method to keep code clean
    private UserDto mapToDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setFirstname(user.getFirstname());
        dto.setLastname(user.getLastname());
        dto.setEmail(user.getEmail());
        dto.setPhonenumber(user.getPhonenumber());
        dto.setRole(user.getRole());
        // Notice we do NOT send the password back in the response for security
        return dto;
    }
}
