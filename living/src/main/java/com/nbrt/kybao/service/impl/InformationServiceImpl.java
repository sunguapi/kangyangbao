package com.nbrt.kybao.service.impl;

import com.nbrt.kybao.entity.Information;
import com.nbrt.kybao.entity.ReceivingAddress;
import com.nbrt.kybao.mapper.InformationMapper;
import com.nbrt.kybao.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class InformationServiceImpl implements InformationService {

    @Autowired
    InformationMapper informationMapper;

    @Override
    public int register(Information information) {
        return informationMapper.register(information);
    }

    @Override
    public String getPhone(String username) {
        return informationMapper.getPhone(username);
    }

    @Override
    public Map<String, Object> getInformation(String username) {
        return informationMapper.getInformation(username);
    }

    @Override
    public int setPwd(String phone, String password) {
        return informationMapper.setPwd(phone,password);
    }

    @Override
    public boolean receivingAddress(ReceivingAddress address) {
        if (informationMapper.receivingAddress(address)==1){
             return true;
        }
        return false;
    }
}
