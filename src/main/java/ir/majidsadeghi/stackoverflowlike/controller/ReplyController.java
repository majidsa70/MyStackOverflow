package ir.majidsadeghi.stackoverflowlike.controller;

import ir.majidsadeghi.stackoverflowlike.dto.CreateReplyDto;
import ir.majidsadeghi.stackoverflowlike.response.BaseResponse;
import ir.majidsadeghi.stackoverflowlike.service.ReplyService;
import ir.majidsadeghi.stackoverflowlike.util.AppConstants;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AppConstants.API_PATH + "reply")
public class ReplyController {

    private final ReplyService replyService;

    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @PostMapping("/question/{id}")
    public ResponseEntity<BaseResponse<Long>> createReplyForQuestion(@PathVariable Long id , @RequestBody @Valid CreateReplyDto dto){
        return new ResponseEntity<>(new BaseResponse<>(true,replyService.createReplyForQuestion(id, dto),null,"created successfully"), HttpStatus.CREATED);
    }
    @PostMapping("/answer/{id}")
    public ResponseEntity<BaseResponse<Long>> createReplyForAnswer(@PathVariable Long id , @RequestBody @Valid CreateReplyDto dto){
        return new ResponseEntity<>(new BaseResponse<>(true,replyService.createReplyForAnswer(id, dto),null,"created successfully"), HttpStatus.CREATED);
    }
}
