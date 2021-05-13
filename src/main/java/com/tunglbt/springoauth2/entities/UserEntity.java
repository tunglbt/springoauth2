package com.tunglbt.springoauth2.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserEntity {
    private Long id;
    private String username;
    private String password;
    private String authorities;
}
