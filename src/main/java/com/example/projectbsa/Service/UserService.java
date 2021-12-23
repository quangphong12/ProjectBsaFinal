package com.example.projectbsa.Service;

import com.example.projectbsa.Entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    ///
    Optional<User> findById(long id);

    List<User> getAllUser();

    void deleteUser(long id);

    void save(User users);

    ///

    Long findId(String username);

    User findUserById(long id);

    void updatePoint(int point,long id);

    long checkPoint(long id);

    void usePoint(int point,long id);

    List<User> getAllUserDESC();

    List<User> findUserByUserName(String username);

    int countEmail(String email);

}
