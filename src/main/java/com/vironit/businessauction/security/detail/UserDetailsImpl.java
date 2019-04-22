package com.vironit.businessauction.security.detail;

import com.vironit.businessauction.entity.UserStatus;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UserDetailsImpl extends org.springframework.security.core.userdetails.User {

    private Long id;
    private UserStatus status;


    public UserDetailsImpl(String username, String password, Collection<? extends GrantedAuthority> authorities, Long id, UserStatus status) {
        super(username, password, authorities);
        this.id = id;
        this.status = status;
    }

    public UserDetailsImpl(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, Long id, UserStatus status) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = id;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !status.equals(UserStatus.BAN);
    }

    @Override
    public boolean isEnabled() {
        return status.equals(UserStatus.ACTIVE);
    }
//    public UserDetailsImpl(User user) {
//        this.user = user;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        String userRole = user.getRole().name();
//        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(userRole);
//        return Collections.singletonList(simpleGrantedAuthority);

//    }
    //    @Override
    //    public String getPassword() {
    //        return user.getPassword();
    //    }
    //
    //    @Override
    //    public String getUsername() {
    //        return user.getLogin();
    //    }
    //
    //    @Override
    //    public boolean isAccountNonExpired() {
    //        return true;
    //    }
    //
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//
//    public User getUser() {
//        return user;
//    }
}
