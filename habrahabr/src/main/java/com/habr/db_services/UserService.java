package com.habr.db_services;

import com.habr.dto.ArticleDTO;
import com.habr.dto.UserDTOToClient;
import com.habr.model.User;
import com.habr.repository.UserRepository;
import com.habr.utils.ModelToDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Cacheable("users")
    public List<UserDTOToClient> getAllUsers(int page, int size) {
        List<User> users = userRepository.findAllBy(PageRequest.of(page, size));
        return ModelToDTO.mapList(users, UserDTOToClient.class);
    }

    public User getUserById(Long id) {
        User user = userRepository.findDistinctById(id);
        user.setFollowingNumber(userRepository.countAllByFollowersId(id));
        return user;
    }

    public List<UserDTOToClient> getFollowing(Long id, int page, int size) {
        List<User> following = userRepository.findByFollowersId(id, PageRequest.of(page, size));
        return ModelToDTO.mapList(following, UserDTOToClient.class);
    }

    public List<UserDTOToClient> getFollowers(Long id, int page, int size) {
        List<User> followers = userRepository.findByUserId(id, page, size);
        return ModelToDTO.mapList(followers, UserDTOToClient.class);
    }

    @Transactional
    public List<ArticleDTO> getUserArticles(Long id) {
        User user = userRepository.findDistinctById(id);
        return ModelToDTO.mapList(new ArrayList<>(user.getArticles()), ArticleDTO.class);
    }
}
