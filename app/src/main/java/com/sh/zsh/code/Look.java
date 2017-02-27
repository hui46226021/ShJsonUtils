package com.sh.zsh.code;

import java.util.List;

/**
 * Created by zhush on 2017/1/11.
 * E-mail zhush@jerei.com
 * PS
 */
public class Look {
    String head;
    String tail;
    int leg;
    List<En> enList;

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getTail() {
        return tail;
    }

    public void setTail(String tail) {
        this.tail = tail;
    }

    public int getLeg() {
        return leg;
    }

    public void setLeg(int leg) {
        this.leg = leg;
    }

    public List<En> getEnList() {
        return enList;
    }

    public void setEnList(List<En> enList) {
        this.enList = enList;
    }

    @Override
    public String toString() {
        return "Look{" +
                "head='" + head + '\'' +
                ", tail='" + tail + '\'' +
                ", leg=" + leg +
                ", enList=" + enList +
                '}';
    }
}
