package com.example.projectbsa.Repository;

import com.example.projectbsa.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query(value="Select u.id from [User] u where u.username =?1",nativeQuery = true)
    Long findIdByUsername(String username);
    @Query(value="select * from [User] u where u.id=?1",nativeQuery = true)
    User findUser(long id);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "update [User] set point=point+?1 where id=?2", nativeQuery = true)
    void updatePoint(int point,long id);

    @Query(value = "SELECT u.email FROM [User] u WHERE u.username=?1",nativeQuery = true)
    String checkEmail(String username);

    @Query(value = "SELECT u.point FROM [User] u WHERE u.id=?1",nativeQuery = true)
    long checkPoint(long id);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "update [User] set point=?1 where id=?2", nativeQuery = true)
    void usePoint(int point,long id);

    @Query(value="SELECT * FROM [User] ORDER BY id DESC", nativeQuery = true)
    List<User> getAllUserDESC();

    @Query(value="SELECT * FROM [User] WHERE username=?1", nativeQuery = true)
    List<User> findUserByUserName(String username);

    @Query(value="SELECT COUNT(u.email) FROM [User] u Where u.email=?1", nativeQuery = true)
    int countEmail(String email);

}
