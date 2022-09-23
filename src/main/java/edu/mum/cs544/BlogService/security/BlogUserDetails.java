package edu.mum.cs544.BlogService.security;

import com.fasterxml.jackson.annotation.JsonIgnore;

import edu.mum.cs544.BlogService.models.User;
import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;


@Data
@ToString
public class BlogUserDetails implements UserDetails {

    private int id;
    private String email;

    @JsonIgnore
    private String password;

    public BlogUserDetails(User user) {
        this.id = user.getId();
        this.email = user.getUsername();
        this.password = user.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
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
