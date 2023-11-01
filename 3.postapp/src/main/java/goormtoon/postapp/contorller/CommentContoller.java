package goormtoon.postapp.contorller;

import goormtoon.postapp.dto.comment.CommentRequestDto;
import goormtoon.postapp.service.CommentServicelmpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/Boards/{BoardId}")
@RequiredArgsConstructor
public class CommentContoller {

    private final CommentServicelmpl commentServicelmpl;

    @PostMapping
    public String addComment(@PathVariable Long BoardId, @ModelAttribute CommentRequestDto commentRequestDto){
        commentServicelmpl.addComment(BoardId, commentRequestDto);
        return "redirect:/Boards/{BoardId}";
    }

    @DeleteMapping("/Comment/{CommentId}/delete")
    public String deleteComment(@PathVariable Long CommentId){
        commentServicelmpl.deleteComment(CommentId);
        return "redirect:/Boards/{BoardId}";
    }

    @PatchMapping("/Comment/{CommentId}/update")
    public String updateComment(@PathVariable Long CommentId, @ModelAttribute CommentRequestDto commentRequestDto){
        commentServicelmpl.updateComment(CommentId,commentRequestDto);
        return "redirect:/Boards/{BoardId}";
    }
}
