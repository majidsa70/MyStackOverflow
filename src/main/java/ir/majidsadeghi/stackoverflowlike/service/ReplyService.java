package ir.majidsadeghi.stackoverflowlike.service;

import ir.majidsadeghi.stackoverflowlike.dto.CreateReplyDto;
import ir.majidsadeghi.stackoverflowlike.entity.Reply;
import ir.majidsadeghi.stackoverflowlike.repository.ReplyRepository;
import ir.majidsadeghi.stackoverflowlike.security.TokenProvider;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
public class ReplyService {
    private final ReplyRepository replyRepository;

    private final QuestionService questionService;

    private final AnswerService answerService;
    private final TokenProvider tokenProvider;

    public ReplyService(ReplyRepository replyRepository, QuestionService questionService, AnswerService answerService, TokenProvider tokenProvider) {
        this.replyRepository = replyRepository;
        this.questionService = questionService;
        this.answerService = answerService;
        this.tokenProvider = tokenProvider;
    }

    public Long createReplyForQuestion(Long questionId,CreateReplyDto dto){
        val user = tokenProvider.getUser();
        val question = questionService.findQuestionById(questionId);
        val reply = new Reply();
        reply.setUser(user);
        reply.setText(dto.getContent());
        reply.setQuestion(question);
        val saved = replyRepository.save(reply);
        return saved.getId();
    }

    public Long createReplyForAnswer(Long answerId,CreateReplyDto dto){
        val user = tokenProvider.getUser();
        val answer = answerService.findById(answerId);
        val reply = new Reply();
        reply.setUser(user);
        reply.setText(dto.getContent());
        reply.setAnswer(answer);
        val saved = replyRepository.save(reply);
        return saved.getId();
    }
}
