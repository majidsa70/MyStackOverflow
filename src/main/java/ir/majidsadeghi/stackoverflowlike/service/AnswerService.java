package ir.majidsadeghi.stackoverflowlike.service;

import ir.majidsadeghi.stackoverflowlike.security.TokenProvider;
import ir.majidsadeghi.stackoverflowlike.dto.AnswerDto;
import ir.majidsadeghi.stackoverflowlike.entity.Answer;
import ir.majidsadeghi.stackoverflowlike.entity.AnswerApproveStatus;
import ir.majidsadeghi.stackoverflowlike.exceptions.NotFoundException;
import ir.majidsadeghi.stackoverflowlike.repository.AnswerRepository;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final TokenProvider tokenProvider;
    private final ModelMapper modelMapper;


    public AnswerService(AnswerRepository answerRepository, TokenProvider tokenProvider,
                         ModelMapper modelMapper) {
        this.answerRepository = answerRepository;
        this.tokenProvider = tokenProvider;
        this.modelMapper = modelMapper;
    }

    public Answer findById(Long id){
        val user = tokenProvider.getUser();
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
}
