/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linksinnovation.handbrake.utils;

import com.linksinnovation.handbrake.model.HandBrake;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jirawong Wongdokpuang <jirawong@linksinnovation.com>
 */
public class HandBrakeUtils {

    private static final String HAND_BRAKE_CLI_PATH = "/usr/bin/HandBrakeCLI";

    private HandBrakeUtils() {
    }

    private static List<String> prop(String input, String output, String quality) {
        List<String> props = new ArrayList<>();
        props.add(HAND_BRAKE_CLI_PATH);
        props.add("-i");
        props.add(input);
        props.add("-o");
        props.add(output);
        props.add("-e");
        props.add("x264");
        props.add("-a");
        props.add("1,1");
        props.add("-E");
        props.add("faac,copy:ac3");
        props.add("-B");
        props.add("160,160");
        props.add("-6");
        props.add("stereo");
        props.add("-R");
        props.add("96");
        props.add("-f");
        props.add("mp4");
        props.add("-Y");
        props.add(quality);
        props.add("--loose-anamorphic");
        props.add("-m");
        props.add("-x");
        props.add("cabac=0:ref=2:me=umh:bframes=0:weightp=0:8x8dct=0:trell");
        props.add("is=0:subme=6");
        return props;
    }

    public static String convert(String input, String output, String quality) throws IOException, InterruptedException {
        final ProcessBuilder builder = new ProcessBuilder(prop(input, output, quality));
        builder.redirectErrorStream(true);
        final Process process = builder.start();
        final int status = process.waitFor();
        if (status == 0) {
            return "done";
        }
        return "fail";
    }
}
