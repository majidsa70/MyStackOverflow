package ir.majidsadeghi.stackoverflowlike.controller;

import ir.majidsadeghi.stackoverflowlike.dto.CreateAnswerDto;
import ir.majidsadeghi.stackoverflowlike.dto.CreateQuestionDto;
import ir.majidsadeghi.stackoverflowlike.dto.QuestionDto;
import ir.majidsadeghi.stackoverflowlike.entity.Question;
import ir.majidsadeghi.stackoverflowlike.response.BaseResponse;
import ir.majidsadeghi.stackoverflowlike.service.QuestionService;
import ir.majidsadeghi.stackoverflowlike.util.AppConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<BaseResponse<List<QuestionDto>>> getAllQuestion(
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize
    ){
        return ResponseEntity.ok(new BaseResponse<>(true,questionService.getAllQuestions(pageNumber, pageSize),null));
    }

    @PostMapping("/{id}")
    public ResponseEntity<BaseResponse<?>> answerQuestion(@PathVariable @NotNull Long id, @RequestBody @Validated CreateAnswerDto dto){
        questionService.saveAnswerQuestion(id,dto);
        return new ResponseEntity<>(new BaseResponse<>(true,true,null,"Your answer creation successfully"),HttpStatus.CREATED);
    }

}
