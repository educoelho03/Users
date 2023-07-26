package br.com.project.crud.controller;

import br.com.project.crud.model.User;
import br.com.project.crud.repository.UserRepository;
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

    @GetMapping("/users") //lista todos os usuarios
    public ResponseEntity<List<User>> listaUsuarios(){
        List<User> users = userRepository.findAll();
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @GetMapping("/user/{id}") //lista os usuarios através do ID.
    public User getUser(@PathVariable Long id){
        return userRepository.getById(id);
    }

    @GetMapping("/valores?valorIni=X&valorFin=Y") // lista todos os usuarios entre um range de id.
    @ResponseBody
    public ResponseEntity<List<User>> listaProdutoPorValor(@RequestParam("valorIni") @Valid Integer valorIni, @RequestParam("valorFim") @Valid Integer valorFim){
        List<User> users = userRepository.findByIdBetween(valorIni, valorFim);
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }


    @PostMapping("/salvar")
    @Transactional
    public ResponseEntity<HttpStatus> cadastraUsuario(@RequestBody User user){
        userRepository.save(user);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }


    @PutMapping("/atualizar/{id}")
    @Transactional
    public User editarUsuario(@RequestBody User user){
        return userRepository.save(user);
    }


    @DeleteMapping("/del/{id}")
    @Transactional
    public ResponseEntity<User> deletaUsuario(@PathVariable Long id){
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
