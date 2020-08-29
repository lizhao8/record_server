/**
  * Copyright 2020 bejson.com 
  */
package com.bilibili.entity.inner.RoomInfo;
import java.util.List;

public class Super_chat_info {

   private int status;
   private String jump_url;
   private String icon;
   private int ranked_mark;
   private List<Message_list> message_list;
   public void setStatus(int status) {
        this.status = status;
    }
    public int getStatus() {
        return status;
    }

   public void setJump_url(String jump_url) {
        this.jump_url = jump_url;
    }
    public String getJump_url() {
        return jump_url;
    }

   public void setIcon(String icon) {
        this.icon = icon;
    }
    public String getIcon() {
        return icon;
    }

   public void setRanked_mark(int ranked_mark) {
        this.ranked_mark = ranked_mark;
    }
    public int getRanked_mark() {
        return ranked_mark;
    }

   public void setMessage_list(List<Message_list> message_list) {
        this.message_list = message_list;
    }
    public List<Message_list> getMessage_list() {
        return message_list;
    }

}