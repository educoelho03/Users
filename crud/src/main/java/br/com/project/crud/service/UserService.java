package br.com.project.crud.service;


import br.com.project.crud.model.User;
import br.com.project.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> listaUsuarios(){
        return userRepository.findAll();
    }

    public User listaUsuariosPorId(Long id){
        return userRepository.getById(id);
    }

    public User criaUsuario(User user){
        return userRepository.save(user);
    }

    public void deleteById(Long id){
        userRepository.deleteById(id);
    }

    public User editaUsuario(User user){
        return userRepository.save(user);
    }

    public Boolean excluirUsuario(Long id){
        userRepository.deleteById(id);
        return true;
    }



}
