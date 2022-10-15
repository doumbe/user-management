package fr.doumbe.spring.usermanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.doumbe.spring.usermanagement.entity.User;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {

}
