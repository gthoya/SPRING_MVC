package com.gthoya.application.board.controller;

import com.gthoya.application.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;

    @RequestMapping("/")
    public String getBoardListForm() {

    }
}
