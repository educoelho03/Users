package br.com.project.crud.controller;

import br.com.project.crud.model.User;
import br.com.project.crud.repository.UserRepository;
import br.com.project.crud.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        List<User> users = userRepository.findByIdBetween(valorIni, valorFim);
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }


    @PostMapping("/salvar")
    @Transactional
    public ResponseEntity<User> cadastraUsuario(@RequestBody User user){
        return ResponseEntity.status(201).body(userService.criaUsuario(user));
    }


    @PutMapping("/atualizar/{id}")
    @Transactional
    public ResponseEntity<User> editarUsuario(@RequestBody User user){
        return ResponseEntity.status(200).body(userService.editaUsuario(user));
    }

    @DeleteMapping("/del/{id}")
    @Transactional
    public ResponseEntity<?> deletaUsuario(@PathVariable Long id){
        userService.excluirUsuario(id);
        return ResponseEntity.status(204).build();
    }


}
