package com.sf.lottery.serviceImpl;

import com.sf.lottery.manager.ConfigManeger;
import com.sf.lottery.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 01139954 on 2016/12/8.
 */
@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    private ConfigManeger configManeger;

    @Override
    public int closeShark() {
        return configManeger.closeShark();
    }

    @Override
    public int openStartShark() {
        return configManeger.openStartShark();
    }

    @Override
    public Boolean isStartShark() {
        return configManeger.isStartShark();
    }
}
