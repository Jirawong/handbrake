/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linksinnovation.handbrake.service;

import com.linksinnovation.handbrake.model.HandBrake;
import com.linksinnovation.handbrake.utils.FFMPEGUtils;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jirawong Wongdokpuang <jirawong@linksinnovation.com>
 */
@Service
public class ConvertService {

    private static final ExecutorService es = Executors.newFixedThreadPool(1);

    public String convert(HandBrake handBrake) throws Exception {
        List<String> props = FFMPEGUtils.createProps("/mnt/data/source/" + handBrake.getUuid());
        switch (handBrake.getQuality()) {
            case "1080":
                props = FFMPEGUtils.addScale(props, "/mnt/data/convert/" + handBrake.getUuid() + "/1080p", "1080");
            case "720":
                props = FFMPEGUtils.addScale(props, "/mnt/data/convert/" + handBrake.getUuid() + "/720p", "720");
            case "480":
                props = FFMPEGUtils.addScale(props, "/mnt/data/convert/" + handBrake.getUuid() + "/480p", "480");
            case "320":
                props = FFMPEGUtils.addScale(props, "/mnt/data/convert/" + handBrake.getUuid() + "/320p", "320");
        }
        execute(props, handBrake);
        return "done";
    }

    private void execute(List<String> props, HandBrake handBrake) throws IOException, InterruptedException {
        es.execute(() -> {
            try {
                FFMPEGUtils.convert(props, handBrake);
            } catch (IOException | InterruptedException ex) {
                Logger.getLogger(ConvertService.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
    }
}
