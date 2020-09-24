package com.michau.oauth.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

//import javax.persistence.Entity;
//import javax.persistence.Id;
import java.util.Collection;
import java.util.Map;

//@Entity
@Getter
@Setter
public class User implements OAuth2User {
    public User(String username) {
        this.username = username;
    }

//    @Id
    private long id;

    private String username;

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getName() {
        return this.username;
    }
}
