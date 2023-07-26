package br.com.project.crud.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "Este campo nao pode ser nulo")
    @NotBlank(message = "Este campo nao pode ser vazio")
    private String name;

    @NotNull(message = "Este campo nao pode ser nulo")
    private String email;

    @NotBlank
    @Size(min = 5, max = 500, message = "Minimo 5 caracteres, máximo 500 caracteres")
    private String password;

    @NotBlank
    private String phone;
}
