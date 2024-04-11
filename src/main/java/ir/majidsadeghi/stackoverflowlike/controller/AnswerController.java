package ir.majidsadeghi.stackoverflowlike.controller;

import ir.majidsadeghi.stackoverflowlike.dto.AnswerDto;
import ir.majidsadeghi.stackoverflowlike.dto.CreateAnswerDto;
import ir.majidsadeghi.stackoverflowlike.response.BaseResponse;
import ir.majidsadeghi.stackoverflowlike.service.AnswerService;
import ir.majidsadeghi.stackoverflowlike.util.AppConstants;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AppConstants.API_PATH + "answer")
public class AnswerController {

    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping("/{question_id}")
    public ResponseEntity<BaseResponse<?>> answerToQuestion(@PathVariable @NotNull Long question_id, @RequestBody @Validated CreateAnswerDto dto){
        answerService.saveAnswerQuestion(question_id,dto);
        return new ResponseEntity<>(new BaseResponse<>(true,true,null,"Your answer creation successfully"), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<AnswerDto>> approvedAnswer(@PathVariable Long id) {

        return ResponseEntity.ok(new BaseResponse<>(true, answerService.approvedAnswer(id), null));
    }
}
