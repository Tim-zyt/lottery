package com.sf.lottery.web.user.controller;

import com.sf.lottery.common.model.Opera;
import com.sf.lottery.service.OperaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
    private int flowerCount;
    private volatile int carCount;
    private volatile int rocketCount;

    public void updateFlower() {
        flowerCount = flowerCount + 1;
    }

    public void updateCar() {
        carCount = carCount + 1;
    }

    public void updateRocket() {
        rocketCount = rocketCount + 1;
    }

    public void resetCount(int operaId) {
        Opera opera = new Opera();
        opera.setId(operaId);
        opera.setOpFlower(flowerCount);
        opera.setOpCar(carCount);
        opera.setOpRocket(rocketCount);
        try {
            operaService.updateByPrimaryKey(opera);
        } catch (Exception e) {
            e.printStackTrace();
        }
        flowerCount = 0;
        carCount = 0;
        rocketCount = 0;
    }
}
