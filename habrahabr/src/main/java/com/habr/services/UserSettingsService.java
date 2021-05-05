package com.habr.services;

import com.habr.model.User;
import com.habr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * so far bad initial implementation
 */
@Service
public class UserSettingsService {

    private final UserRepository userRepository;

    @Autowired
    public UserSettingsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @CacheEvict(value = "users", condition = "#result != null")
    @Transactional
    public User updateNameSurname(User user) {
        User persistentUser = userRepository.findDistinctById(user.getId());
        persistentUser.setFirstName(user.getFirstName());
        persistentUser.setLastName(user.getLastName());
        user = userRepository.save(persistentUser);
        return user;
    }

    @Transactional
    public User updatePassword(User user) {
        User persistentUser = userRepository.findDistinctById(user.getId());
        persistentUser.setPassword(user.getPassword());
        user = userRepository.save(persistentUser);
        return user;
    }

    @Transactional
    public User updateEmail(User user) {
        User persistentUser = userRepository.findDistinctById(user.getId());
        persistentUser.setPassword(user.getEmail());
        user = userRepository.save(persistentUser);
        return user;
    }

    @Transactional
    public User updateNickname(User user) {
        return null;
    }
}
