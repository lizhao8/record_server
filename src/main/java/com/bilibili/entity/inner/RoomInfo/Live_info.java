/**
  * Copyright 2020 bejson.com 
  */
package com.bilibili.entity.inner.RoomInfo;
import java.util.List;

/**
 * Auto-generated: 2020-08-10 0:58:21
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Live_info {

    private int level;
    private long level_color;
    private long score;
    private int upgrade_score;
    private List<Long> current;
    private List<String> next;
    private String rank;
    public void setLevel(int level) {
         this.level = level;
     }
     public int getLevel() {
         return level;
     }

    public void setLevel_color(long level_color) {
         this.level_color = level_color;
     }
     public long getLevel_color() {
         return level_color;
     }

    public void setScore(long score) {
         this.score = score;
     }
     public long getScore() {
         return score;
     }

    public void setUpgrade_score(int upgrade_score) {
         this.upgrade_score = upgrade_score;
     }
     public int getUpgrade_score() {
         return upgrade_score;
     }

    public void setCurrent(List<Long> current) {
         this.current = current;
     }
     public List<Long> getCurrent() {
         return current;
     }

    public void setNext(List<String> next) {
         this.next = next;
     }
     public List<String> getNext() {
         return next;
     }

    public void setRank(String rank) {
         this.rank = rank;
     }
     public String getRank() {
         return rank;
     }

}