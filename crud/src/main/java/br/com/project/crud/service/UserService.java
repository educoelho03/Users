package br.com.project.crud.service;


import br.com.project.crud.model.User;
import br.com.project.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public List<User> listaUsuarios(){
        return userRepository.findAll();
    }

    public User listaUsuariosPorId(Long id){
        return userRepository.getById(id);
    }

    public List<User> listaUsuariosPorRange(Integer valorIni, Integer valorFin){
        return userRepository.findByIdBetween(valorIni, valorFin);
    }

    public User criaUsuario(User user){
        String encoder = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(encoder);
        return userRepository.save(user);
    }

    public User editaUsuario(User user){
        String encoder = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(encoder);
        return userRepository.save(user);
    }

    public Boolean excluirUsuario(Long id){
        userRepository.deleteById(id);
        return true;
    }


    public Boolean validarSenha(User user) {
        String senha = userRepository.getById(user.getId()).getPassword();
        Boolean valid = passwordEncoder.matches(senha, senha);
        return valid;
    }
}
