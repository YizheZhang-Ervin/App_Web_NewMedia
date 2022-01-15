package com.ervin.newmediaspring.po;

import lombok.Data;

@Data
public class UserPO {
    private int id;
    private String name;
    private String password;
    private String authority;
    private String job;
    private String description;
}
