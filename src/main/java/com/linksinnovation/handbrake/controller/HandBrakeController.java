/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linksinnovation.handbrake.controller;

import com.linksinnovation.handbrake.model.HandBrake;
import com.linksinnovation.handbrake.service.ConvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jirawong Wongdokpuang <jirawong@linksinnovation.com>
 */
@RestController
public class HandBrakeController {
    
    @Autowired
    private ConvertService convertService;
    
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public String convert(@RequestBody HandBrake handBrake) throws Exception{
        return convertService.convert(handBrake);
    }
    
}
