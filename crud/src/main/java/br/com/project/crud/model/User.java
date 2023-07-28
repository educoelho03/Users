package br.com.project.crud.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "O nome é obrigatório.")
    @Column(name = "name", length = 200)
    private String name;

    @Email(message = "Insira um email válido")
    @NotBlank(message = "O email é obrigatório.")
    private String email;

    @NotBlank(message = "A senha é obrigatória.")
    @Size(min = 5, max = 500, message = "Minimo 5 caracteres, máximo 500 caracteres")
    private String password;

    @NotBlank(message = "O telefone é obrigatório.")
    private String phone;
}
