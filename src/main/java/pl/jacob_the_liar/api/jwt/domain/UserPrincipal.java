package pl.jacob_the_liar.api.jwt.domain;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.jacob_the_liar.api.jwt.model.Role;
import pl.jacob_the_liar.api.jwt.model.User;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;


/**
 * * @author: Jakub O.  [https://github.com/JacobTheLiar]
 * * @date : 2022-05-22 18:28
 * *
 * * @className: UserPrincipal
 * *
 * *
 ******************************************************/
public class UserPrincipal extends User implements UserDetails{
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return super.getRoles()
                .stream()
                .map(Role::getName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }
    
    
    @Override
    public boolean isAccountNonExpired(){
        return isEnabled();
    }
    
    
    @Override
    public boolean isAccountNonLocked(){
        return ofNullable(super.getLastInvalidPassword()).map(LocalDateTime.now()::isAfter).orElse(true);
    }
    
    
    @Override
    public boolean isCredentialsNonExpired(){
        return isEnabled();
    }
    
    
    @Override
    public boolean isEnabled(){
        return super.getDisabled() == null;
    }
}
