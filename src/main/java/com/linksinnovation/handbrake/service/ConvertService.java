/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linksinnovation.handbrake.service;

import com.linksinnovation.handbrake.model.HandBrake;
import com.linksinnovation.handbrake.utils.FFMPEGUtils;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jirawong Wongdokpuang <jirawong@linksinnovation.com>
 */
@Service
public class ConvertService {

    public String convert(HandBrake handBrake) throws Exception {
        List<String> props = FFMPEGUtils.createProps(handBrake.getInput());
        switch (handBrake.getQuality()) {
            case "1080":
//                HandBrakeUtils.convert(handBrake.getInput(), handBrake.getOutput() + "_1080.mp4", "1080");
                props = FFMPEGUtils.addScale(props, handBrake.getOutput() + "_1080p", "1080");
            case "720":
//                HandBrakeUtils.convert(handBrake.getInput(), handBrake.getOutput() + "_720.mp4", "720");
                props = FFMPEGUtils.addScale(props, handBrake.getOutput() + "_720p", "720");
            case "480":
//                HandBrakeUtils.convert(handBrake.getInput(), handBrake.getOutput() + "_480.mp4", "480");
                props = FFMPEGUtils.addScale(props, handBrake.getOutput() + "_480p", "480");
            case "320":
//                HandBrakeUtils.convert(handBrake.getInput(), handBrake.getOutput() + "_320.mp4", "320");
                props = FFMPEGUtils.addScale(props, handBrake.getOutput() + "_320p", "320");
//            case "240":
////                HandBrakeUtils.convert(handBrake.getInput(), handBrake.getOutput() + "_240.mp4", "240");
//                props = FFMPEGUtils.addScale(props, handBrake.getOutput() + "_240p", "240");
//            case "140":
////                HandBrakeUtils.convert(handBrake.getInput(), handBrake.getOutput() + "_140.mp4", "140");
//                props = FFMPEGUtils.addScale(props, handBrake.getOutput() + "_140p", "140");
        }
        FFMPEGUtils.convert(props);
        return "done";
    }
}
