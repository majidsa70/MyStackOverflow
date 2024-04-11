package ir.majidsadeghi.stackoverflowlike.service;

import ir.majidsadeghi.stackoverflowlike.dto.CreateAnswerDto;
import ir.majidsadeghi.stackoverflowlike.repository.UserRepository;
import ir.majidsadeghi.stackoverflowlike.security.TokenProvider;
import ir.majidsadeghi.stackoverflowlike.dto.AnswerDto;
import ir.majidsadeghi.stackoverflowlike.entity.Answer;
import ir.majidsadeghi.stackoverflowlike.entity.AnswerApproveStatus;
import ir.majidsadeghi.stackoverflowlike.exceptions.NotFoundException;
import ir.majidsadeghi.stackoverflowlike.repository.AnswerRepository;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionService questionService;
    private final ModelMapper modelMapper;
    private final UserService userService;


    public AnswerService(AnswerRepository answerRepository, QuestionService questionService,
                         ModelMapper modelMapper, UserService userService) {
        this.answerRepository = answerRepository;
        this.questionService = questionService;

        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    public Answer findById(Long id){

        val user = userService.findUser();
        return answerRepository.findByIdAndUser(id,user).orElseThrow(() -> new NotFoundException("answer","id",id.toString()));
    }

    public AnswerDto approvedAnswer(Long id) {
        Answer answer = findById(id);
        answer.setApproveStatus(AnswerApproveStatus.APPROVED);
        Answer updated = answerRepository.save(answer);
        return modelMapper.map(updated, AnswerDto.class);
    }

    public void deleteAnswer(Long id) {
        answerRepository.deleteById(id);
    }

    public void saveAnswerQuestion(Long questionId, CreateAnswerDto dto) {
        questionService.saveAnswerQuestion(questionId,dto);
    }
}
