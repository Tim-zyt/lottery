package com.sf.lottery.web.award.controller;

import com.sf.lottery.common.dto.JsonResult;
import com.sf.lottery.common.model.Award;
import com.sf.lottery.common.utils.ExceptionUtils;
import com.sf.lottery.service.AwardService;
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
import java.util.Set;

/**
 * @author wujiang
 * @version 1.0.0.
 * @date 2016/12/4
 */
@Controller
public class AwardController {
    private final static Logger log = LoggerFactory.getLogger(AwardController.class);

    @Autowired AwardService awardService;

    @ResponseBody
    @RequestMapping(value = "/award/getAwardByKind", method = RequestMethod.POST)
    public JsonResult<List<Award>> getAwardByKind(String awKind) {
        JsonResult<List<Award>> result = new JsonResult<>();
        try {
            List<Award> Awards = awardService.getAwardByKind(awKind);
            result.setData(Awards);
        } catch (Exception e) {
            log.warn(ExceptionUtils.getStackTrace(e));
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/award/getAllAwKinds", method = RequestMethod.POST)
    public JsonResult<Set<String>> getAllAwKinds() {
        JsonResult<Set<String>> result = new JsonResult<>();
        try {
            Set<String> awKinds = awardService.getAllAwKinds();
            result.setData(awKinds);
        } catch (Exception e) {
            log.warn(ExceptionUtils.getStackTrace(e));
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/award/addAward", method = RequestMethod.POST)
    public JsonResult<Boolean> addAward(HttpServletRequest request) {
        JsonResult<Boolean> result = new JsonResult<>();
        String awardName = request.getParameter("awardName");
        String awardDescription = request.getParameter("awardDescription");
        Integer awardUserCount = Integer.valueOf(request.getParameter("awardUserCount"));
        String awardKind = request.getParameter("awardKind");
        Award award = null;
        try {
            award = new Award();
            award.setAwName(awardName);
            award.setAwDescription(awardDescription);
            award.setAwUserCount(awardUserCount);
            award.setAwKind(awardKind);
            Boolean b = awardService.addAward(award);
            result.setData(b);
        } catch (Exception e) {
            log.warn(ExceptionUtils.getStackTrace(e));
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/award/deleteAward", method = RequestMethod.POST)
    public JsonResult<Boolean> deleteAward(@RequestParam("awardId")Integer awardId,HttpServletRequest request) {
        JsonResult<Boolean> result = new JsonResult<>();
        try {
            result.setData(awardService.deleteAwardByAwardId(awardId));
        } catch (Exception e) {
            log.warn(ExceptionUtils.getStackTrace(e));
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/award/updateAward", method = RequestMethod.POST)
    public JsonResult<Boolean> updateAward(@RequestParam("awardId")Integer awardId,HttpServletRequest request) {
        JsonResult<Boolean> result = new JsonResult<>();
        String awardName = request.getParameter("awardName");
        String awardDescription = request.getParameter("awardDescription");
        Integer awardUserCount = Integer.valueOf(request.getParameter("awardUserCount"));
        String awardKind = request.getParameter("awardKind");
        try {
            Award award = awardService.getAward(awardId);
            if(awardName!=null && awardName!=""){
                award.setAwName(awardName);
            }
            if(awardDescription!=null && awardDescription!=""){
                award.setAwDescription(awardDescription);
            }
            if(awardUserCount!=null){
                award.setAwUserCount(awardUserCount);
            }
            if(awardKind!=null && awardKind!=""){
                award.setAwKind(awardKind);
            }
            result.setData(awardService.updateAward(award));
        } catch (Exception e) {
            log.warn(ExceptionUtils.getStackTrace(e));
        }
        return result;
    }
}
