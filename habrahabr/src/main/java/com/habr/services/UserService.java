package com.habr.services;

import com.habr.dto.UserDTO;
import com.habr.model.User;
import com.habr.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findDistinctById(id);
    }

    @Transactional
    public List<UserDTO> getFollowing(Long id) {
        Set<User> following = userRepository.findDistinctById(id).getFollowing();
        return userToDto(following);
    }

    @Transactional
    public List<UserDTO> getFollowers(Long id) {
        Set<User> followers = userRepository.findDistinctById(id).getFollowers();
        return userToDto(followers);
    }

    private List<UserDTO> userToDto(Set<User> users) {
        List<UserDTO> followers = new ArrayList<>();
        for(User user : users) {
            UserDTO userDTO = new UserDTO(user);
            followers.add(userDTO);
        }
        return followers;
    }
}
