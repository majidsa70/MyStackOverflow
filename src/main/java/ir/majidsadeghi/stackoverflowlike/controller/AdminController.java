package ir.majidsadeghi.stackoverflowlike.controller;

import ir.majidsadeghi.stackoverflowlike.response.BaseResponse;
import ir.majidsadeghi.stackoverflowlike.service.AnswerService;
import ir.majidsadeghi.stackoverflowlike.service.QuestionService;
import ir.majidsadeghi.stackoverflowlike.util.AppConstants;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AppConstants.ADMIN_API_PATH)
//@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final QuestionService questionService;

    public AdminController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @DeleteMapping("/question/{id}")
    public ResponseEntity<BaseResponse<Long>> deleteQuestion(@PathVariable @NotNull Long id){
        Long deletedId = questionService.deleteQuestion(id);
        return ResponseEntity.ok(new BaseResponse<>(true,deletedId,null,"success"));
    }
}
