package com.project.jwt_oauth2.repository;

import com.project.jwt_oauth2.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/* 2. This is the second bean created to have a user repository
      to save/retrieve user details in DB for authentication.   */
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,Long> {

    // 4.1 Note: Below repository method call to be defined before overriding UserInfoConfig loadByUsername() method.
    Optional<UserInfo> findByEmailId(String emailId);
}
