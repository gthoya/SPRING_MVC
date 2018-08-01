package com.gthoya.application.board.controller;

import com.gthoya.annotation.LoginCheck;
import com.gthoya.application.board.model.Contents;
import com.gthoya.application.board.service.BoardService;
import com.gthoya.constant.CommonConstant;
import com.gthoya.application.sign.model.User;
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
    public String getBoardPage(User user) {
        return "board/board";
    }

    @PostMapping("contents/create")
    @ResponseBody
    public String createContents(@LoginCheck User user, Contents contents) {
        try {
            contents.setCreateUser(user.getId());
            return boardService.createContents(contents);
        } catch (Exception e) {
            log.error("create contents error", e);
            return CommonConstant.FAIL;
        }
    }

    @PostMapping("contents/modify")
    @ResponseBody
    public String modifyContents(@LoginCheck User user, Contents contents) {
        try {
            contents.setUpdateUser(user.getId());
            return boardService.modifyContents(contents);
        } catch (Exception e) {
            log.error("modify contents error", e);
            return CommonConstant.FAIL;
        }
    }

    @PostMapping("contents/unused/{id}")
    @ResponseBody
    public String unusedContents(@LoginCheck User user, @PathVariable long id) {
        try {
            Contents contents = new Contents(id);
            contents.setUpdateUser(user.getId());
            return boardService.unusedContents(contents);
        } catch (Exception e) {
            log.error("unused contents error", e);
            return CommonConstant.FAIL;
        }
    }

    @GetMapping("contentsList")
    public ModelAndView getContentsList(Contents contents) {
        ModelAndView mav = new ModelAndView("board/contentsList");

        mav.addObject("contentsList", boardService.getContentsList(contents));

        return mav;
    }

    @GetMapping("contents/{id}")
    public ModelAndView getContents(User user, @PathVariable long id) {
        ModelAndView mav = new ModelAndView("board/contents");
        Contents result = boardService.getContents(new Contents(id));

        mav.addObject("contents", (result == null ? new Contents() : result));

        return mav;
    }
}
