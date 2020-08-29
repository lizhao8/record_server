/**
  * Copyright 2020 bejson.com 
  */
package com.bilibili.entity.inner.RoomInfo;

/**
 * Auto-generated: 2020-08-10 0:58:21
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Base_info {

    private String uname;
    private String face;
    private String gender;
    private Official_info official_info;
    public void setUname(String uname) {
         this.uname = uname;
     }
     public String getUname() {
         return uname;
     }

    public void setFace(String face) {
         this.face = face;
     }
     public String getFace() {
         return face;
     }

    public void setGender(String gender) {
         this.gender = gender;
     }
     public String getGender() {
         return gender;
     }

    public void setOfficial_info(Official_info official_info) {
         this.official_info = official_info;
     }
     public Official_info getOfficial_info() {
         return official_info;
     }

}