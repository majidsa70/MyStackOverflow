package ir.majidsadeghi.stackoverflowlike.repository;


import ir.majidsadeghi.stackoverflowlike.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByMobile(String login);
}
