package ru.irkagr.somesite.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.irkagr.somesite.domen.User;

public interface UserRepo extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByActivationCode(String code);
}
