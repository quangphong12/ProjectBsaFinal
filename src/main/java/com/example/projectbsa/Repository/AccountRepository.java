package com.example.projectbsa.Repository;

import com.example.projectbsa.Entity.Account;
import com.example.projectbsa.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value="update Account set password=?2,verification_code=?3,enabled='0' where username=?1",nativeQuery = true)
    void updateAccount(String username, String password, String code);

    @Query(value = "SELECT * FROM Account a WHERE a.verification_code = ?1", nativeQuery = true)
    Account findByVerificationCode(String code);
    
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value="DELETE FROM Account WHERE username=?1",nativeQuery = true)
    void deleteAccount(String username);
}
