package com.gthoya.application.board.controller;

import com.gthoya.annotation.LoginCheck;
import com.gthoya.application.board.model.Contents;
import com.gthoya.application.board.service.BoardService;
import com.gthoya.application.sign.model.User;
import com.gthoya.constant.CommonConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping(value = {"", "board"})
    public ModelAndView getBoardPage(@LoginCheck(required = false) User user) {
        ModelAndView mav = new ModelAndView("board/board");

        mav.addObject("userId", (user != null ? user.getId() : StringUtils.EMPTY));

        return mav;
    }

    @GetMapping("contentsList")
    public ModelAndView getContentsList(Contents contents) {
        ModelAndView mav = new ModelAndView("board/contentsList");

        mav.addObject("contentsList", boardService.getContentsList(contents));

        return mav;
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

    @GetMapping("contents/{id}")
    public ModelAndView getContents(@LoginCheck(required = false) User user, @PathVariable long id) {
        ModelAndView mav = new ModelAndView("board/contents");
        Contents result = boardService.getContents(new Contents(id));

        mav.addObject("userId", (user != null ? user.getId() : StringUtils.EMPTY));
        mav.addObject("contents", (result != null ? result : new Contents()));

        return mav;
    }

    @PostMapping("/contents/upload/file")
    @ResponseBody
    public String uploadFile(MultipartFile attachFile) {
        try {
            return CommonConstant.SUCCESS;
        } catch (Exception e) {
            log.error("contents file upload error");
            return CommonConstant.FAIL;
        }
    }
}
