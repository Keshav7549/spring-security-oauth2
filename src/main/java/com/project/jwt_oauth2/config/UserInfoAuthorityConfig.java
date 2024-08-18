package com.project.jwt_oauth2.config;

import com.project.jwt_oauth2.entity.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

/*3. This is the third config bean created to have a user object
     implemented using UserDetails interface for authentication. */

@RequiredArgsConstructor
public class UserInfoAuthorityConfig implements UserDetails {

    private final UserInfo userInfo;   // a. This final UserInfo entity object created to get all roles specified in the USER_INFO DB/bean

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // This method returns a final list that contains all the individual roles as specified in UserInfo entity.
        // A single user can have multiple roles. Eg: Manager can be an admin or a normal user role or both.
        return Arrays.stream(userInfo.getRoles()
                                     .split(",")
                            ).map(SimpleGrantedAuthority::new)
                             .toList();
    }

    @Override
    public String getPassword() {
        return userInfo.getPassword();
    }

    @Override
    public String getUsername() {
        return userInfo.getEmailId();  // Will get email from the userInfo entity for username here as email is unique.
    }

    @Override
    public boolean isAccountNonExpired() {  // by default this is set to true unless we are not giving any condition based validation
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
