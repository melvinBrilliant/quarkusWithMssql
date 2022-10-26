package org.melvin.first.dto.typicode;

import lombok.Data;

@Data
public class UserDto {
    private Integer id;
    private String name;
    private String username;
    private String email;
    private String address;
    private String phone;
    private String website;
    private String company;
}
