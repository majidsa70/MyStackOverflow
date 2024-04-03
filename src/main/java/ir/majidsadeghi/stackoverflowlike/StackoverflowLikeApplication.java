package ir.majidsadeghi.stackoverflowlike;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

@PropertySource("file:${user.dir}/.env")
@SpringBootApplication
public class StackoverflowLikeApplication {


    @Bean
    public ModelMapper injectMapper(){
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(StackoverflowLikeApplication.class, args);
    }

}
