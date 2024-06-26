package ir.majidsadeghi.stackoverflowlike.service;

import ir.majidsadeghi.stackoverflowlike.repository.UserRepository;
import ir.majidsadeghi.stackoverflowlike.security.TokenProvider;
import ir.majidsadeghi.stackoverflowlike.dto.CreateAnswerDto;
import ir.majidsadeghi.stackoverflowlike.dto.CreateQuestionDto;
import ir.majidsadeghi.stackoverflowlike.dto.QuestionDto;
import ir.majidsadeghi.stackoverflowlike.entity.Answer;
import ir.majidsadeghi.stackoverflowlike.entity.Question;
import ir.majidsadeghi.stackoverflowlike.entity.User;
import ir.majidsadeghi.stackoverflowlike.exceptions.NotFoundException;
import ir.majidsadeghi.stackoverflowlike.repository.AnswerRepository;
import ir.majidsadeghi.stackoverflowlike.repository.QuestionRepository;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    private final AnswerRepository answerRepository;

    private final ModelMapper modelMapper;

    private final UserService userService;

    public QuestionService(QuestionRepository questionRepository, AnswerRepository answerRepository, ModelMapper modelMapper, UserService userService) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.modelMapper = modelMapper;

        this.userService = userService;
    }

    private User getUser() {
        return userService.findUser();
    }

    public Long createQuestion(CreateQuestionDto dto) {
        val question = new Question();
        question.setUser(getUser());
        question.setQuestionText(dto.content());
        Question savedQuestion = questionRepository.save(question);
        return savedQuestion.getId();
    }

    public List<QuestionDto> getAllQuestions(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Question> questions = questionRepository.findAll(pageable);

        return questions.getContent().stream().map((element) -> modelMapper.map(element, QuestionDto.class)).collect(Collectors.toList());
    }
    public List<QuestionDto> getAllUserQuestion(int pageNumber, int pageSize) {
        User user = getUser();
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Question> questions = questionRepository.findAllByUser(pageable, user);

        return questions.getContent().stream().map((element) -> modelMapper.map(element, QuestionDto.class)).collect(Collectors.toList());
    }

    public void saveAnswerQuestion(Long id, CreateAnswerDto dto) {
        Question question = findQuestionById(id);
        val answer = new Answer();
        answer.setAnswerText(dto.content());
        answer.setUser(getUser());
        answer.setQuestion(question);
        answerRepository.save(answer);
    }


    public Question findQuestionById(Long id) {
        return questionRepository.findById(id).orElseThrow(() -> new NotFoundException("Question", "id", id.toString()));
    }

    public Long deleteQuestion(Long id) {
        val question = findQuestionById(id);
         questionRepository.delete(question);
         return question.getId();
    }
}
