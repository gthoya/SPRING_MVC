package com.gthoya.application.board.controller;

import com.gthoya.application.board.model.Contents;
import com.gthoya.application.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping("board")
    public String getBoardPage() {
        return "board/board";
    }

    @GetMapping("contentsList")
    public ModelAndView getContentsList(Contents contents) {
        ModelAndView mav = new ModelAndView("board/contentsList");

        mav.addObject("contentsList", boardService.getContentsList(contents));

        return mav;
    }

    @GetMapping("contents/create")
    @ResponseBody
    public String createContents(Contents contents) {
        return boardService.createContents(contents);
    }

    @PostMapping("contents/modify")
    @ResponseBody
    public String modifyContents(Contents contents) {
        return boardService.modifyContents(contents);
    }

    @PostMapping("contents/unused")
    @ResponseBody
    public String unusedContents(Contents contents) {
        return boardService.unusedContents(contents);
    }

    @GetMapping("contents")
    public ModelAndView getContents(Contents contents) {
        ModelAndView mav = new ModelAndView("board/contents");

        mav.addObject("contents", boardService.getContents(contents));

        return mav;
    }
}
