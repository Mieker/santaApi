package com.mieker.santaApi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Role implements GrantedAuthority {

    public enum ERole {
        ROLE_USER,
        ROLE_ADMIN
    }

    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    private ERole role;

    public Role(ERole name) {
        this.role = name;
    }

    @Override
    public String getAuthority() {
        return role.name();
    }
}