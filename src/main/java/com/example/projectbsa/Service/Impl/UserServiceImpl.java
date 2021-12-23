package com.example.projectbsa.Service.Impl;

import com.example.projectbsa.Entity.User;
import com.example.projectbsa.Repository.UserRepository;
import com.example.projectbsa.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;


    ///
    @Override
    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAllUser() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void save(User users) {
        userRepository.save(users);
    }

    ///
    @Override
    public Long findId(String username) {
        return userRepository.findIdByUsername(username);
    }

    @Override
    public User findUserById(long id) {
        return userRepository.findUser(id);
    }

    @Override
    public void updatePoint(int point, long id) {
        userRepository.updatePoint(point,id);
    }

    @Override
    public long checkPoint(long id) {
        return userRepository.checkPoint(id);
    }

    @Override
    public void usePoint(int point, long id) {
        userRepository.usePoint(point,id);
    }

    @Override
    public List<User> getAllUserDESC() {
        return (List<User>) userRepository.getAllUserDESC();
    }

    @Override
    public List<User> findUserByUserName(String username) {
        return (List<User>) userRepository.findUserByUserName(username);
    }

    @Override
    public int countEmail(String email) {
        return userRepository.countEmail(email);
    }


}
