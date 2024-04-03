package ir.majidsadeghi.stackoverflowlike.repository;

import ir.majidsadeghi.stackoverflowlike.entity.Question;
import ir.majidsadeghi.stackoverflowlike.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository  extends JpaRepository<Question,Long> {

    Page<Question> findAllByUser(Pageable pageable, User user);
}
