package ir.majidsadeghi.stackoverflowlike.controller;

import ir.majidsadeghi.stackoverflowlike.dto.AnswerDto;
import ir.majidsadeghi.stackoverflowlike.response.BaseResponse;
import ir.majidsadeghi.stackoverflowlike.service.AnswerService;
import ir.majidsadeghi.stackoverflowlike.util.AppConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AppConstants.API_PATH + "answer")
public class AnswerController {

    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<AnswerDto>> approvedAnswer(@PathVariable Long id) {

        return ResponseEntity.ok(new BaseResponse<>(true, answerService.approvedAnswer(id), null));
    }
}
