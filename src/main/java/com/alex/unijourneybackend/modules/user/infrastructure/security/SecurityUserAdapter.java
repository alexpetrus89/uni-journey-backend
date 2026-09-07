package com.alex.unijourneybackend.modules.user.infrastructure.security;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.alex.unijourneybackend.modules.user.domain.model.User;


public class SecurityUserAdapter implements UserDetails {

    @Serial
    private static final long serialVersionUID = 1L;

    private final transient User user;

    public SecurityUserAdapter(User user) {
        this.user = Objects.requireNonNull(user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));
        user.getPermissions()
            .stream()
            .map(p -> new SimpleGrantedAuthority(p.name()))
            .forEach(authorities::add);
        return Collections.unmodifiableList(authorities);
    }

    @Override
    public String getPassword() {
        return user.getEncodedPasswordForAuth(); // usa il metodo esplicito
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    public User getUser() {
        return user;
    }

    @Override public boolean isAccountNonLocked() { return !user.isLocked(); }
    @Override public boolean isEnabled() { return user.isActive(); }
    @Override public boolean isCredentialsNonExpired() { return user.hasValidCredentials(); }
    @Override public boolean isAccountNonExpired() { return true; }


}
