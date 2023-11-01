package goormtoon.postapp.contorller;

import goormtoon.postapp.dto.board.BoardListCommentDto;
import goormtoon.postapp.dto.board.BoardListResponseDto;
import goormtoon.postapp.dto.board.BoardRequestDto;
import goormtoon.postapp.dto.board.BoardDetailResponseDto;
import goormtoon.postapp.service.BoardServicelmpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@Controller
@RequestMapping("/Boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardServicelmpl boardServicelmpl;

    //게시판 조회 Paging 구현하기
    @GetMapping
    @ResponseBody
    public List<BoardListResponseDto> Boards(@PageableDefault(size = 10) Pageable pageable){
        return boardServicelmpl.getAllBoards(pageable);
    }


    @PostMapping("/add")
//    @ResponseBody
    public String addBoard(@ModelAttribute("boardDto") BoardRequestDto boardRequestDto){
        log.info("addBoard 실행");

        BoardDetailResponseDto boardDetailResponseDto = boardServicelmpl.saveBoard(boardRequestDto);
        Long id = boardDetailResponseDto.getId();
        return "redirect:/Boards/" +id;
    }

    @DeleteMapping("/{BoardId}/delete")
    public String deleteBoard(@PathVariable Long BoardId){
        Boolean b = boardServicelmpl.deleteBoard(BoardId);
        if(!b){
            return "ok";
        }
        return "redirect:/Boards";
    }

    @PatchMapping("/{BoardId}/update")
    @ResponseBody
    public BoardDetailResponseDto updateBoard(@PathVariable Long BoardId, @ModelAttribute BoardRequestDto boardRequestDto){
        return boardServicelmpl.updateBoard(BoardId, boardRequestDto);
    }


    @ResponseBody
    @GetMapping("/{BoardId}")
    public BoardListCommentDto getBoard(@PathVariable Long BoardId){
        log.info("getBoard 실행");
        return boardServicelmpl.getBoardById(BoardId);
    }
}
