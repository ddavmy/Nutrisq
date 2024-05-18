package com.project.nutrisq.controller.dto;

import lombok.Builder;
import lombok.Getter;
import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Builder
public class UserDto implements Serializable {
    private String email;
    private String username;
    private String created;
    private String password;
    private UserSpecificsDto userSpecifics;

    @Getter
    @Builder
    public static class GetUsers implements Serializable {
        private String email;
        private String username;
        private String roles;
        private Timestamp created;
        private UserSpecificsDto userSpecifics;

        public GetUsers(String email, String username, String roles, Timestamp created, UserSpecificsDto userSpecifics) {
            this.email = email;
            this.username = username;
            this.roles = roles;
            this.created = created;
            this.userSpecifics = userSpecifics;
        }
    }

    @Getter
    @Builder
    public static class RoleEdit implements Serializable {
        private String roles;

        public RoleEdit(String roles) {
            this.roles = roles;
        }
    }

    @Getter
    @Builder
    public static class UserEdit implements Serializable {
        private String email;
        private String username;
        private String password;

        public UserEdit(String email, String username, String password) {
            this.email = email;
            this.username = username;
            this.password = password;
        }
    }
}
