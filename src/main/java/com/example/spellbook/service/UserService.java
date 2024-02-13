package com.example.spellbook.service;

import com.example.spellbook.dto.UserDTO;
import com.example.spellbook.mapper.UserMapper;
import com.example.spellbook.model.User;
import com.example.spellbook.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDTO getUserById(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.map(UserMapper::userToUserDTO).orElse(null);
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return UserMapper.usersToUserDTOs(users);
    }

    public boolean registerUser(UserDTO userDto) {
        try {
            User user = UserMapper.userDTOToUser(userDto);
            System.out.println("User registration " + user);
            if (userRepository.findByUsername(user.getUsername()).isEmpty()){
                userRepository.save(user);
            } else {
                return false;
            }
            return true;
        } catch (DataIntegrityViolationException e) {
            return false;
        }
    }

    public UserDTO getUserByUsernameAndPassword(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        System.out.println("User log in " + user);
        return user != null ? UserMapper.userToUserDTO(user) : null;
    }

    public boolean logoutUser() {
        try {

            log.info("User logged out");

            return true;

        } catch (Exception e) {
            log.error("Logout failed", e);
            return false;
        }
    }
}
