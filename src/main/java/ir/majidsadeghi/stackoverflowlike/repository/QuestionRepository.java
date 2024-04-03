package ir.majidsadeghi.stackoverflowlike.repository;

import ir.majidsadeghi.stackoverflowlike.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository  extends JpaRepository<Question,Long> {
}
