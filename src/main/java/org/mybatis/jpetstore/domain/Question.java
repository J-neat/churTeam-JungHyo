package org.mybatis.jpetstore.domain;

import java.io.Serializable;

public class Question implements Serializable {
    private static final long serialVersionUID = -2159121673445454630L;


    private String type;
    private int queNo;
    private String contents;
    private int exNo;
    private int point;
    private String exContents;

    public int getExNo() {
        return exNo;
    }

    public void setExNo(int exNo) {
        this.exNo = exNo;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getExContents() {
        return exContents;
    }

    public void setExContents(String exContents) {
        this.exContents = exContents;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQueNo() {
        return queNo;
    }

    public void setQueNo(int queNo) {
        this.queNo = queNo;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }



}