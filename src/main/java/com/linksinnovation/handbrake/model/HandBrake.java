/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linksinnovation.handbrake.model;

/**
 *
 * @author Jirawong Wongdokpuang <jirawong@linksinnovation.com>
 */
public class HandBrake {
    private String uuid;
    private Long lecture;
    private String quality;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Long getLecture() {
        return lecture;
    }

    public void setLecture(Long lecture) {
        this.lecture = lecture;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    
}
