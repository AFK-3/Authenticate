package id.ac.ui.cs.advprog.afk3.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "userEntity")
public class UserEntity {
    @Id
    private String username;
    private String password;
    private String name;
    private String address;
    private String phonenumber;
    private String type;
}