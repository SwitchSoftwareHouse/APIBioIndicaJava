package dev.switchsoftwarehouse.bioindicaapi.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;
    @Column()
    private String username;
    @Column()
    private String email;
    @Column()
    private String password;
    @Column(columnDefinition = "ENUM('USER', 'ADMIN') DEFAULT 'USER'")
    private String role;
}
