package br.com.project.crud.repository;

import br.com.project.crud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByIdBetween(Integer valorIni, Integer valorFim);

}
