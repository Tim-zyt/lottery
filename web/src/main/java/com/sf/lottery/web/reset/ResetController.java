package com.sf.lottery.web.reset;

import com.sf.lottery.common.dto.JsonResult;
import com.sf.lottery.common.utils.ExceptionUtils;
import com.sf.lottery.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wujiang
 * @version 1.0.0.
 * @date 2016/12/12
 */
@Controller
public class ResetController {
    private final static Logger log = LoggerFactory.getLogger(ResetController.class);

    @Autowired UserService userService;

    @ResponseBody
    @RequestMapping(value = "/reset/resetData", method = RequestMethod.POST)
    public JsonResult<Boolean> resetData() {
        JsonResult<Boolean> result = new JsonResult<>();
        try {
            result.setData(userService.resetUsers());
        } catch (Exception e) {
            log.warn(ExceptionUtils.getStackTrace(e));
        }
        return result;
    }

}
