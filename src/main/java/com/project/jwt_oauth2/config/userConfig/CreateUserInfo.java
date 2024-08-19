package com.project.jwt_oauth2.config.userConfig;

import com.project.jwt_oauth2.entity.UserInfo;
import com.project.jwt_oauth2.repository.UserInfoRepository;
//import com.project.jwt_oauth2.util.security.PasswordEncoderUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

/*6. This is the sixth userConfig bean created to initialise user
     with custom user details added (hardcoded in our case using CommandLineRunner)  */

@RequiredArgsConstructor
@Component
@Slf4j
public class CreateUserInfo implements CommandLineRunner {

    private final UserInfoRepository userInfoRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
//        PasswordEncoderUtil pwdUtil = new PasswordEncoderUtil();

        // This run method is basically created to save static users in userRepository
        // Save as user in userRepo
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("User");
        userInfo.setPassword(passwordEncoder.encode("root"));
        userInfo.setRoles("ROLE_USER");
        userInfo.setMobileNumber("1111111111");
        userInfo.setEmailId("user@user.com");

        // Save as manager in userRepo
        UserInfo managerInfo = new UserInfo();
        managerInfo.setUserName("Manager");
        managerInfo.setPassword(passwordEncoder.encode("root"));
        managerInfo.setRoles("ROLE_MANAGER");
        managerInfo.setMobileNumber("9999999999");
        managerInfo.setEmailId("manager@manager.com");

        // Save as admin in userRepo
        UserInfo admin = new UserInfo();
        admin.setUserName("Admin");
        admin.setPassword(passwordEncoder.encode("root"));
        admin.setRoles("ROLE_ADMIN");
        admin.setMobileNumber("0000000000");
        admin.setEmailId("admin@admin.com");

        // Save all types of users in userRepo DB
        userInfoRepository.saveAll(List.of(userInfo,managerInfo,admin));
    }
}
