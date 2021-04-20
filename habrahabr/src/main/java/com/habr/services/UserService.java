package com.habr.services;

import com.habr.dto.ArticleDTO;
import com.habr.dto.UserDTO;
import com.habr.model.Article;
import com.habr.model.User;
import com.habr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Cacheable("addresses")
    public List<UserDTO> getAllUsers(int page, int size) {
        Set<User> user = userRepository.findAll(PageRequest.of(page, size)).toSet();
        return userToDto(user);
    }

    public User getUserById(Long id) {
        User user = userRepository.findDistinctById(id);
        user.setFollowingNumber(userRepository.getFollowingNumber(id));
        return user;
    }

    @Transactional
    public List<UserDTO> getFollowing(Long id) {
        Set<User> following = userRepository.findByFollowersId(id);
        return userToDto(new HashSet<>(following));
    }

    @Transactional
    public List<UserDTO> getFollowers(Long id) {
        Set<User> followers = userRepository.findByUserId(id, 0, 10);
        return userToDto(new HashSet<>(followers));
    }

    private List<UserDTO> userToDto(Set<User> users) {
        List<UserDTO> followers = new ArrayList<>();
        for(User user : users) {
            followers.add(new UserDTO(user));
        }
        return followers;
    }

    @Transactional
    public List<ArticleDTO> getUserArticles(Long id) {
        User user = userRepository.findDistinctById(id);
        List<ArticleDTO> userArticles = new ArrayList<>();
        for (Article article : user.getArticles()) {
            userArticles.add(new ArticleDTO(article));
        }
        return userArticles;
    }
}
