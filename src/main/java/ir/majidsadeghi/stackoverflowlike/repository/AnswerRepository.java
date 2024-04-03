package ir.majidsadeghi.stackoverflowlike.repository;

import ir.majidsadeghi.stackoverflowlike.entity.Answer;
import ir.majidsadeghi.stackoverflowlike.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer,Long> {
    Optional<Answer> findByIdAndUser(Long id, User user);
}
