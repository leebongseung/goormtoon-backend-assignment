package goormtoon.postapp.contorller;

import goormtoon.postapp.dto.board.BoardListCommentDto;
import goormtoon.postapp.dto.board.BoardRequestDto;
import goormtoon.postapp.dto.board.BoardResponseDto;
import goormtoon.postapp.repository.BoardRepository;
import goormtoon.postapp.service.BoardServicelmpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
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
    public List<BoardResponseDto> Boards(@PageableDefault(size = 10) Pageable pageable){
        Page<BoardResponseDto> allBoards = boardServicelmpl.getAllBoards(pageable);
        return allBoards.getContent();
    }


    @PostMapping("/add")
    @ResponseBody
    public BoardResponseDto addBoard(@ModelAttribute("boardDto") BoardRequestDto boardRequestDto){
        log.info("addBoard 실행");

        return boardServicelmpl.saveBoard(boardRequestDto);
    }

    @PutMapping("/{BoardId}/delete")
    public String deleteBoard(@PathVariable Long BoardId){
        Boolean b = boardServicelmpl.deleteBoard(BoardId);
        if(!b){
            return "ok";
        }
        return "redirect:/Boards";
    }

    @PutMapping("/{BoardId}/update")
    @ResponseBody
    public BoardResponseDto updateBoard(@PathVariable Long BoardId, @ModelAttribute BoardRequestDto boardRequestDto){
        return boardServicelmpl.updateBoard(BoardId, boardRequestDto);
    }


    @ResponseBody
    @GetMapping("/{BoardId}")
    public BoardListCommentDto getBoard(@PathVariable Long BoardId){
        log.info("getBoard 실행");
        return boardServicelmpl.getBoardById(BoardId);
    }
}
