package br.com.project.crud.controller;

import br.com.project.crud.model.User;
import br.com.project.crud.repository.UserRepository;
import br.com.project.crud.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users") //lista todos os usuarios
    public ResponseEntity<List<User>> listaUsuarios(){
        return ResponseEntity.status(200).body(userService.listaUsuarios());
    }


    @GetMapping("/user/{id}") //lista os usuarios através do ID.
    public User getUser(@PathVariable Long id){
        return userService.listaUsuariosPorId(id);
    }


    @GetMapping("/valores?valorIni=X&valorFin=Y") // lista todos os usuarios entre um range de id.
    @ResponseBody
    public ResponseEntity<List<User>> listaProdutoPorValor(@RequestParam("valorIni") @Valid Integer valorIni, @RequestParam("valorFim") @Valid Integer valorFim){
        return ResponseEntity.status(200).body(userService.listaUsuariosPorRange(valorIni, valorFim));
    }


    @PostMapping("/salvar")
    @Transactional
    public ResponseEntity<User> cadastraUsuario(@Valid @RequestBody User user){
        return ResponseEntity.status(201).body(userService.criaUsuario(user));
    }


    @PutMapping("/atualizar/{id}")
    @Transactional
    public ResponseEntity<User> editarUsuario(@Valid @RequestBody User user){
        return ResponseEntity.status(200).body(userService.editaUsuario(user));
    }

    @DeleteMapping("/del/{id}")
    @Transactional
    public ResponseEntity<?> deletaUsuario(@PathVariable Long id){
        userService.excluirUsuario(id);
        return ResponseEntity.status(204).build();
    }

    @PostMapping("/login")
    public ResponseEntity<User> validarSenha(@Valid @RequestBody User user){
        Boolean valid = userService.validarSenha(user);
        if(!valid){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.status(200).build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException e){
        Map<String, String> errors = new HashMap<>();

        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);

        });


        return errors;

    }


}
