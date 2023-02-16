package com.bmstu.flowrence.auth.jwt.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@Accessors(chain = true)
@RequiredArgsConstructor
public class JwtUser implements UserDetails {

    private final String userIdentifier;
    private final String userEmail;
    private final String userPassword;

    @Override
    public String getUsername() {
        return userIdentifier;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Set<GrantedAuthority> h = new HashSet<GrantedAuthority>(Arrays.<GrantedAuthority>asList("ROLE_ADMIN"));

        return null;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
