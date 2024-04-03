package ir.majidsadeghi.stackoverflowlike.controller;

import ir.majidsadeghi.stackoverflowlike.dto.CreateQuestionDto;
import ir.majidsadeghi.stackoverflowlike.response.BaseResponse;
import ir.majidsadeghi.stackoverflowlike.service.QuestionService;
import ir.majidsadeghi.stackoverflowlike.util.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AppConstants.API_PATH + "question")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping
    public ResponseEntity<BaseResponse<Long>> createQuestion(@RequestBody @Validated CreateQuestionDto dto) {
        return new ResponseEntity<>(new BaseResponse<>(true, questionService.createQuestion(dto), null, "" +
                "Question Creation Success"), HttpStatus.CREATED);
    }
}
