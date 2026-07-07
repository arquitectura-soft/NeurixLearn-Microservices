package org.learne.platform.profileservice.shared.dto;

public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private Integer type_user;
    private Integer type_plan;

    public UserDto() {}

    public UserDto(Long id, String firstName, String lastName, String username, String email, Integer type_user, Integer type_plan) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.type_user = type_user;
        this.type_plan = type_plan;
    }

    // Getters y setters
    // ...
}
