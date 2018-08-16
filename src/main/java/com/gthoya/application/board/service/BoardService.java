package com.gthoya.application.board.service;

import com.gthoya.application.board.dao.BoardDAO;
import com.gthoya.application.board.model.Contents;
import com.gthoya.constant.CommonConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    @Autowired
    private BoardDAO boardDAO;

    public String createContents(Contents contents) {
        if (boardDAO.insertContents(contents) == 1) {
            return CommonConstant.SUCCESS;
        }

        return CommonConstant.FAIL;
    }

    public String modifyContents(Contents contents) {
        if (boardDAO.updateContents(contents) == 1) {
            return CommonConstant.SUCCESS;
        }

        return CommonConstant.FAIL;
    }

    public String unusedContents(Contents contents) {
        if (boardDAO.updateUnusedContents(contents) == 1) {
            return CommonConstant.SUCCESS;
        }

        return CommonConstant.FAIL;
    }

    public List<Contents> getContentsList(Contents contents) {
        return boardDAO.selectContentsList(contents);
    }

    public Contents getContents(Contents contents) {
        return boardDAO.selectContents(contents);
    }
}
