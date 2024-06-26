package dev.switchsoftwarehouse.bioindicaapi.models.response;

import lombok.Data;

@Data
public class UserResponse {
    private String fullName;
    private String cpf;
    private String email;
    private String phone;
}
