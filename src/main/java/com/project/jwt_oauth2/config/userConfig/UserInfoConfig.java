package com.project.jwt_oauth2.config.userConfig;

import com.project.jwt_oauth2.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/*4. This is the fourth config bean created to get the users by
     implementing UserDetailsService interface for authentication. */

@Service
@RequiredArgsConstructor
public class UserInfoConfig implements UserDetailsService {

    @Autowired
    public final UserInfoRepository userInfoRepository;

    /* 4.2 This method will get the user in the form of Auth object which is UserDetails object.
           The user is get using email address which is unique for every user.    */
    @Override
    public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {

        /* The below method is finding UserInfo object using findByEmailId() and then
            mapping the UserInfo object with the UserInfoAuthorityConfig
            as it is implementing the UserDetails interface which is return type for this method.  */

        return userInfoRepository.findByEmailId(emailId)
                                 .map(UserInfoAuthorityConfig::new)
                                 .orElseThrow(()-> new UsernameNotFoundException("User emailID not found"));
    }
}
