package com.nbrt.kybao.service.impl;

import com.nbrt.kybao.entity.Hotel;
import com.nbrt.kybao.mapper.SojournMapper;
import com.nbrt.kybao.service.SojournService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author dayang
 */
//@SpringBootTest
//class SojournServiceImplTest {
//    @Autowired
//    private SojournService sojournService;
//
//    @Autowired
//    private SojournMapper sojournMapper;
//
////    @Test
////    void getHotelPage() {
//        PageInfo<Hotel> hotelPage = sojournService.getHotelPage("浙江", "宁波", 2, 3, "如");
//    }
//
//    @Test
//    void getHotelPageM() {
//        List<Hotel> hotelPage = sojournMapper.getHotelPage("浙江", "宁波", "如");
//        hotelPage.forEach(System.out::println);
//    }
//}