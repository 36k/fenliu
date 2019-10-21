package com.hlau.fenliu.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Component
@Entity
@Data
public class User implements Serializable,Comparable<User>, UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer objectId;
    @Column(unique = true)
    private String userId;
    private String name;
    private String password;
    private String email;
    private Boolean subscribe;
    private double score;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_role")
    private Role role;
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "user_department")
    private Department department;
    @OneToMany(fetch=FetchType.EAGER,cascade = {CascadeType.ALL})
    @JoinColumn(name = "aspiration_user")
    private Set<Aspiration> aspirations;
    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
            List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(("ROLE_" + this.getRole().getRoleId()));
        return grantedAuthorities;
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return this.getUserId();
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

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_major")
    private Major major;

    @Override
    public int compareTo(User o) {
        return (int)Math.ceil(score-o.getScore());
    }
}