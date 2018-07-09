package com.gthoya.application.board.controller;

import com.gthoya.application.board.model.Contents;
import com.gthoya.application.board.service.BoardService;
import com.gthoya.application.constant.CommonConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
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

    @PostMapping("contents/create")
    @ResponseBody
    public String createContents(Contents contents) {
        try {
            return boardService.createContents(contents);
        } catch (Exception e) {
            log.error("create contents error", e);
            return CommonConstant.FAIL;
        }
    }

    @PostMapping("contents/modify")
    @ResponseBody
    public String modifyContents(Contents contents) {
        try {
            return boardService.modifyContents(contents);
        } catch (Exception e) {
            log.error("modify contents error", e);
            return CommonConstant.FAIL;
        }
    }

    @PostMapping("contents/unused/{id}")
    @ResponseBody
    public String unusedContents(@PathVariable long id) {
        try {
            return boardService.unusedContents(new Contents(id));
        } catch (Exception e) {
            log.error("unused contents error", e);
            return CommonConstant.FAIL;
        }
    }

    @GetMapping("contents/{id}")
    public ModelAndView getContents(@PathVariable long id) {
        ModelAndView mav = new ModelAndView("board/contents");
        Contents result = boardService.getContents(new Contents(id));

        mav.addObject("contents", (result == null ? new Contents() : result));

        return mav;
    }
}
