package ir.majidsadeghi.stackoverflowlike.repository;

import ir.majidsadeghi.stackoverflowlike.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply,Long> {
}
