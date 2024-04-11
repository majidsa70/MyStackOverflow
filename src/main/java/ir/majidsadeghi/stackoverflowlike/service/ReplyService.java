package ir.majidsadeghi.stackoverflowlike.service;

import ir.majidsadeghi.stackoverflowlike.dto.CreateReplyDto;
import ir.majidsadeghi.stackoverflowlike.entity.Reply;
import ir.majidsadeghi.stackoverflowlike.repository.ReplyRepository;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
public class ReplyService {
    private final ReplyRepository replyRepository;

    private final QuestionService questionService;

    private final AnswerService answerService;

    private final UserService userService;

    public ReplyService(ReplyRepository replyRepository, QuestionService questionService, AnswerService answerService, UserService userService) {
        this.replyRepository = replyRepository;
        this.questionService = questionService;
        this.answerService = answerService;
        this.userService = userService;

    }

    public Long createReplyForQuestion(Long questionId, CreateReplyDto dto) {
        val user = userService.findUser();
        val question = questionService.findQuestionById(questionId);
        val reply = new Reply();
        reply.setUser(user);
        reply.setText(dto.getContent());
        reply.setQuestion(question);
        val saved = replyRepository.save(reply);
        return saved.getId();
    }

    public Long createReplyForAnswer(Long answerId, CreateReplyDto dto) {
        val user = userService.findUser();
        val answer = answerService.findById(answerId);
        val reply = new Reply();
        reply.setUser(user);
        reply.setText(dto.getContent());
        reply.setAnswer(answer);
        val saved = replyRepository.save(reply);
        return saved.getId();
    }
}
