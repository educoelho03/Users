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

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User getById(Long id){
        return userRepository.getById(id);
    }

    public void deleteById(Long id){
        userRepository.deleteById(id);
    }



}
