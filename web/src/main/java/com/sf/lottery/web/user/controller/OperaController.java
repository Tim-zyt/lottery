package com.sf.lottery.web.user.controller;

import com.sf.lottery.common.model.Opera;
import com.sf.lottery.service.OperaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
        Opera opera = new Opera();
        opera.setId(operaId);
        opera.setOpFlower(flowerCount.get());
        opera.setOpCar(carCount.get());
        opera.setOpRocket(rocketCount.get());
        try {
            operaService.updateByPrimaryKey(opera);
        } catch (Exception e) {
            e.printStackTrace();
        }
        flowerCount.set(0);
        carCount.set(0);
        rocketCount.set(0);
    }


}
