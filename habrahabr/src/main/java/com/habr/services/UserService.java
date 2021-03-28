package com.habr.services;

import com.habr.dto.UserDTO;
import com.habr.model.User;
import com.habr.repository.UserRepository;
import com.mysql.cj.xdevapi.SessionFactory;
import org.hibernate.Session;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
        User user = userRepository.findDistinctById(id);
        return user;
    }

    @Transactional
    public Set<User> getFollowing(Long id) {
//        try {
//            UserDTO userDTO =  modelMapper.map(userRepository.getDistinctById(id), UserDTO.class);
//        } catch (Throwable throwable) {
//            System.out.println("something went wrong");
//        }
//        System.out.println("=======================================================================");
//        Set<User> users = userRepository.findDistinctById(id).getFollowing();
        Set<User> users = userRepository.findDistinctById(id).getFollowing();

//        HashSet<User> following = new HashSet<>();
//        for (User user : users) {
//           following.add(user);
//        }
        return users;
    }

}
