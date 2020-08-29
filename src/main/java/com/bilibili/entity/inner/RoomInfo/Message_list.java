package com.bilibili.entity.inner.RoomInfo;

public class Message_list {

   private long id;
   private long uid;
   private int price;
   private int rate;
   private String background_image;
   private String background_color;
   private String background_icon;
   private String background_price_color;
   private String background_bottom_color;
   private String font_color;
   private int time;
   private long start_time;
   private long end_time;
   private String message;
   private int trans_mark;
   private String message_trans;
   private String token;
   private long ts;
   private User_info user_info;
   public void setId(long id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }

   public void setUid(long uid) {
        this.uid = uid;
    }
    public long getUid() {
        return uid;
    }

   public void setPrice(int price) {
        this.price = price;
    }
    public int getPrice() {
        return price;
    }

   public void setRate(int rate) {
        this.rate = rate;
    }
    public int getRate() {
        return rate;
    }

   public void setBackground_image(String background_image) {
        this.background_image = background_image;
    }
    public String getBackground_image() {
        return background_image;
    }

   public void setBackground_color(String background_color) {
        this.background_color = background_color;
    }
    public String getBackground_color() {
        return background_color;
    }

   public void setBackground_icon(String background_icon) {
        this.background_icon = background_icon;
    }
    public String getBackground_icon() {
        return background_icon;
    }

   public void setBackground_price_color(String background_price_color) {
        this.background_price_color = background_price_color;
    }
    public String getBackground_price_color() {
        return background_price_color;
    }

   public void setBackground_bottom_color(String background_bottom_color) {
        this.background_bottom_color = background_bottom_color;
    }
    public String getBackground_bottom_color() {
        return background_bottom_color;
    }

   public void setFont_color(String font_color) {
        this.font_color = font_color;
    }
    public String getFont_color() {
        return font_color;
    }

   public void setTime(int time) {
        this.time = time;
    }
    public int getTime() {
        return time;
    }

   public void setStart_time(long start_time) {
        this.start_time = start_time;
    }
    public long getStart_time() {
        return start_time;
    }

   public void setEnd_time(long end_time) {
        this.end_time = end_time;
    }
    public long getEnd_time() {
        return end_time;
    }

   public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

   public void setTrans_mark(int trans_mark) {
        this.trans_mark = trans_mark;
    }
    public int getTrans_mark() {
        return trans_mark;
    }

   public void setMessage_trans(String message_trans) {
        this.message_trans = message_trans;
    }
    public String getMessage_trans() {
        return message_trans;
    }

   public void setToken(String token) {
        this.token = token;
    }
    public String getToken() {
        return token;
    }

   public void setTs(long ts) {
        this.ts = ts;
    }
    public long getTs() {
        return ts;
    }

   public void setUser_info(User_info user_info) {
        this.user_info = user_info;
    }
    public User_info getUser_info() {
        return user_info;
    }

}