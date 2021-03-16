package com.habr.services;

import com.habr.model.User;
import com.habr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;

@Service
@Transactional
public class RegistrationAndAuthorisationService {

    private final UserRepository userRepository;

    @Autowired
    public RegistrationAndAuthorisationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void persist(User user) {
        user.setCreationDate(new Timestamp(new Date().getTime()));
        userRepository.save(user);
    }
}
