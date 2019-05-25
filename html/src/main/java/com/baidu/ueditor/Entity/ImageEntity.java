package com.baidu.ueditor.Entity;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class ImageEntity {
    private String id;
    private String name;
    private byte[]bytes;
    private String beLongId;
    private String beLongType;
    private Timestamp time;
}
