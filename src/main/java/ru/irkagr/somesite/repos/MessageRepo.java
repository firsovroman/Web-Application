package ru.irkagr.somesite.repos;

import org.springframework.data.repository.CrudRepository;
import ru.irkagr.somesite.domen.Message;

import java.util.List;
import java.util.Optional;

public interface MessageRepo extends CrudRepository<Message, Long> {

    List<Message> findByTag(String string);

    Optional<Message> findById(Long aLong);

}
