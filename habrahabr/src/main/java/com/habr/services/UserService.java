package com.habr.services;

import com.habr.dto.UserDTO;
import com.habr.model.Followers;
import com.habr.model.User;
import com.habr.repository.FollowersRepository;
import com.habr.repository.UserRepository;
import com.mysql.cj.xdevapi.Collection;
import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.*;
import java.util.stream.Collectors;

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

    @Transactional
    public List<User> getFollowing(Long id) {
        Set<User> users = userRepository.getDistinctById(id).getFollowing();
//        List<User> followers = new ArrayList<>();
//        for(User user : users) {
//            followers.add(userRepository.findDistinctById(user.getId()));
//        }
        return new ArrayList<>(users);
    }
}
