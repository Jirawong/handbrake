/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linksinnovation.handbrake.utils;

import com.linksinnovation.handbrake.model.HandBrake;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jirawong Wongdokpuang <jirawong@linksinnovation.com>
 */
public class FFMPEGUtils {

    private FFMPEGUtils() {
    }

    private static final String FFMPEG_PATH = "/usr/bin/ffmpeg";

    public static List<String> createProps(String input) {
        List<String> props = new ArrayList<>();
        props.add(FFMPEG_PATH);
        props.add("-i");
        props.add(input);
        return props;
    }

    public static List<String> addScale(List<String> props, String output, String scale) {
        props.add("-vf");
        props.add("scale=-2:" + scale);
        props.add("-acodec");
        props.add("aac");
        props.add("-strict");
        props.add("-2");
        props.add("-vcodec");
        props.add("libx264");
        props.add("-map");
        props.add("0");
        props.add("-map");
        props.add("-0:d");
        props.add("-f");
        props.add("segment");
        props.add("-segment_time");
        props.add("1");
        props.add("-segment_list");
        props.add(output + ".m3u8");
        props.add("-segment_format");
        props.add("mpegts");
        props.add("-vbsf");
        props.add("h264_mp4toannexb");
        props.add("-flags");
        props.add("-global_header");
        props.add(output + "-%d.ts");
        return props;
    }

    public static void convert(List<String> props,HandBrake handBrake) throws IOException, InterruptedException {
        createFolder(handBrake);
        
        final ProcessBuilder builder = new ProcessBuilder(props);
        builder.redirectErrorStream(true);
        builder.redirectOutput(new File("/mnt/data/convert/"+handBrake.getUuid()+"/convert.log"));
        final Process process = builder.start();
        process.waitFor();
    }
    
    private static void createFolder(HandBrake handBrake) throws IOException, InterruptedException{
        final ProcessBuilder builder = new ProcessBuilder("mkdir","/mnt/data/convert/"+handBrake.getUuid());
        builder.redirectErrorStream(true);
        final Process process = builder.start();
        process.waitFor();
    }
}
