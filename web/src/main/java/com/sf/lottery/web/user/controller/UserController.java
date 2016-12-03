package com.sf.lottery.web.user.controller;

import com.sf.lottery.common.dto.JsonResult;
import com.sf.lottery.common.model.User;
import com.sf.lottery.common.utils.ExceptionUtils;
import com.sf.lottery.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by 01139954 on 2016/12/2.
 */


@Controller
public class UserController {
    private final static Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/user/getSignedUser", method = RequestMethod.POST)
    public JsonResult<List<User>> getSignedUser() {
        JsonResult<List<User>> result = new JsonResult<>();
        try {
            List<User> Users =  userService.getSignedUser();
            result.setData(Users);
        } catch (Exception e) {
            log.warn(ExceptionUtils.getStackTrace(e));
        }
        return result;
    }
}
