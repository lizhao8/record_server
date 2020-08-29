/**
  * Copyright 2020 bejson.com 
  */
package com.bilibili.entity.inner.RoomInfo;
import java.util.Date;

/**
 * Auto-generated: 2020-08-10 0:58:21
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class News_info {

    private long uid;
    private Date ctime;
    private String content;
    public void setUid(long uid) {
         this.uid = uid;
     }
     public long getUid() {
         return uid;
     }

    public void setCtime(Date ctime) {
         this.ctime = ctime;
     }
     public Date getCtime() {
         return ctime;
     }

    public void setContent(String content) {
         this.content = content;
     }
     public String getContent() {
         return content;
     }

}