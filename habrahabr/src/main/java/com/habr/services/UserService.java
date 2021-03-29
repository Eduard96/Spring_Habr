package com.habr.services;

import com.habr.model.Followers;
import com.habr.model.User;
import com.habr.repository.FollowersRepository;
import com.habr.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final FollowersRepository followersRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public UserService(UserRepository userRepository, FollowersRepository followersRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.followersRepository = followersRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findDistinctById(id);
    }

    public List<Followers> getFollowing(Long id) {
//        try {
//            UserDTO userDTO =  modelMapper.map(userRepository.getDistinctById(id), UserDTO.class);
//        } catch (Throwable throwable) {
//            System.out.println("something went wrong");
//        }
//        System.out.println("=======================================================================");
//        Set<User> users = userRepository.findDistinctById(id).getFollowing();
        //Set<Followers> users = userRepository.findDistinctById(id).getFollowing();
//        List<UserDTO> fol = users.stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
//        HashSet<User> following = new HashSet<>();
//        for (User user : users) {
//           following.add(user);
//        }
        return followersRepository.findAllByFollowingId(id);
    }

}
