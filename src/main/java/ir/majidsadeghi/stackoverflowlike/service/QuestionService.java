package ir.majidsadeghi.stackoverflowlike.service;

import ir.majidsadeghi.stackoverflowlike.auth.TokenProvider;
import ir.majidsadeghi.stackoverflowlike.dto.CreateQuestionDto;
import ir.majidsadeghi.stackoverflowlike.entity.Question;
import ir.majidsadeghi.stackoverflowlike.entity.User;
import ir.majidsadeghi.stackoverflowlike.repository.QuestionRepository;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    private final ModelMapper modelMapper;

    private final TokenProvider tokenProvider;

    public QuestionService(QuestionRepository questionRepository, ModelMapper modelMapper, TokenProvider tokenProvider) {
        this.questionRepository = questionRepository;
        this.modelMapper = modelMapper;
        this.tokenProvider = tokenProvider;
    }

    private User getUser(){
        return tokenProvider.getUser();
    }
    public Long createQuestion(CreateQuestionDto dto){
        val question = new Question();
        question.setUser(getUser());
        question.setQuestionText(dto.content());
        Question savedQuestion = questionRepository.save(question);
        return savedQuestion.getId();
    }
}
