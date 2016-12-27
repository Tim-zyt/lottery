package com.sf.lottery.web.user.controller;

import com.sf.lottery.common.dto.JsonResult;
import com.sf.lottery.common.model.Opera;
import com.sf.lottery.common.utils.ExceptionUtils;
import com.sf.lottery.service.ConfigService;
import com.sf.lottery.service.OperaService;
import org.apache.zookeeper.Op;
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
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zyt
 * @version 1.0.0
 * @date 2016/12/6.
 */
@Controller
public class OperaController {
    private final static Logger log = LoggerFactory.getLogger(OperaController.class);
    @Autowired
    private OperaService operaService;
    @Autowired
    private ConfigService configService;
    private AtomicInteger flowerCount = new AtomicInteger(0);
    private AtomicInteger carCount = new AtomicInteger(0);
    private AtomicInteger rocketCount = new AtomicInteger(0);

    public void updateFlower() {
        flowerCount.incrementAndGet();
    }

    public void updateCar() {
        carCount.incrementAndGet();
    }

    public void updateRocket() {
        rocketCount.incrementAndGet();
    }

    public void resetCount(int operaId) {

        try {
            Opera opera = operaService.getOperaByOperaId(operaId);
            opera.setOpFlower(opera.getOpFlower() + flowerCount.get());
            opera.setOpCar(opera.getOpCar() + carCount.get());
            opera.setOpRocket(opera.getOpRocket() + rocketCount.get());
            operaService.updateByPrimaryKey(opera);
        } catch (Exception e) {
            e.printStackTrace();

        }
        flowerCount.set(0);
        carCount.set(0);
        rocketCount.set(0);
    }

    @ResponseBody
    @RequestMapping(value = "/opera/addOpera", method = RequestMethod.POST)
    public JsonResult<Boolean> addAward(HttpServletRequest request) {
        JsonResult<Boolean> result = new JsonResult<>();
        String opName = request.getParameter("opName");
        String opActor = request.getParameter("opActor");
        String s = request.getParameter("opSort");
        Integer opSort = 0;
        if (s != null && s != "") {
            opSort = Integer.valueOf(s);
        }
        String opDepartment = request.getParameter("opDepartment");
        Opera opera = null;
        try {
            opera = new Opera();
            opera.setOpName(opName);
            opera.setOpDepartment(opDepartment);
            opera.setOpSort(opSort);
            opera.setOpActor(opActor);
            Boolean b = operaService.addOpera(opera);
            result.setData(b);
        } catch (Exception e) {
            log.warn(ExceptionUtils.getStackTrace(e));
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/opera/deleteOpera", method = RequestMethod.POST)
    public JsonResult<Boolean> deleteAward(@RequestParam("operaId") Integer operaId, HttpServletRequest request) {
        JsonResult<Boolean> result = new JsonResult<>();
        try {
            result.setData(operaService.deleteOpera(operaId));
        } catch (Exception e) {
            log.warn(ExceptionUtils.getStackTrace(e));
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/opera/updateOpera", method = RequestMethod.POST)
    public JsonResult<Boolean> updateAward(@RequestParam("operaId") Integer operaId, HttpServletRequest request) {
        JsonResult<Boolean> result = new JsonResult<>();
        String opName = request.getParameter("opName");
        String opActor = request.getParameter("opActor");
        String opSort = request.getParameter("opSort");
        String opDepartment = request.getParameter("opDepartment");
        try {
            Opera opera = operaService.getOperaByOperaId(operaId);
            if (opName != null && opName != "") {
                opera.setOpName(opName);
            }
            if (opSort != null && opSort != "") {
                opera.setOpSort(Integer.valueOf(opSort));
            }
            if (opActor != null && opActor != "") {
                opera.setOpActor(opActor);
            }
            if (opDepartment != null && opDepartment != "") {
                opera.setOpDepartment(opDepartment);
            }
            result.setData(operaService.updateOpera(opera));
        } catch (Exception e) {
            log.warn(ExceptionUtils.getStackTrace(e));
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/opera/getAllOperas", method = RequestMethod.POST)
    public JsonResult<List<Opera>> getAllAwards(HttpServletRequest request) {
        JsonResult<List<Opera>> result = new JsonResult<>();
        try {
            result.setData(operaService.getAllOperas());
            result.setMessage(Integer.toString(configService.selectCurProgramId()));
            return result;
        } catch (Exception e) {
            log.warn(ExceptionUtils.getStackTrace(e));
        }
        return result;
    }


}
