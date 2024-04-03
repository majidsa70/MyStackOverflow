package ir.majidsadeghi.stackoverflowlike.repository;

import ir.majidsadeghi.stackoverflowlike.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer,Long> {
}
