package com.project.jwt_oauth2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/*8. This is the eighth controller bean created to call user APIs
     and return user handler data based on respective assigned user roles in DB */

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_MANAGER','ROLE_ADMIN')")
    @GetMapping("/welcome")
    public ResponseEntity<String> getFirstWelcomeMessage(Authentication authentication){
        return ResponseEntity.ok("Welcome to the Oauth2 API "+authentication.getName()
                                        +"with scope "+authentication.getAuthorities());
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    @GetMapping("/manager")
    public ResponseEntity<String> getManagerData(Principal principal){
        return ResponseEntity.ok("Manager :: "+principal.getName());
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/admin")
    public ResponseEntity<String> getAdminData(@RequestParam("msg") String msg, Principal principal){
        return ResponseEntity.ok("Admin ::"+principal.getName()+" has this message : "+msg);
    }
}
