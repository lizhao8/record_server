/**
  * Copyright 2020 bejson.com 
  */
package com.bilibili.entity.inner.RoomInfo;

import com.bilibili.entity.outer.RoomInfo;

/**
 * Auto-generated: 2020-08-10 0:58:21
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class JsonRootBean {

    private int code;
    private String message;
    private int ttl;
    private RoomInfo data;
    public void setCode(int code) {
         this.code = code;
     }
     public int getCode() {
         return code;
     }

    public void setMessage(String message) {
         this.message = message;
     }
     public String getMessage() {
         return message;
     }

    public void setTtl(int ttl) {
         this.ttl = ttl;
     }
     public int getTtl() {
         return ttl;
     }

    public void setData(RoomInfo data) {
         this.data = data;
     }
     public RoomInfo getData() {
         return data;
     }

}