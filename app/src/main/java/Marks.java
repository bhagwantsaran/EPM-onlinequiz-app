package com.example.najss.javatp;

class Marks {
    private String s,q;
    private  String t,c,w;

    public Marks(String c, String q, String s, String t, String w) {
        this.s = s;
        this.q = q;
        this.t = t;
        this.c = c;
        this.w = w;
    }

    public String getS() {
        return s;
    }

    public String getQ() {
        return q;
    }

    public String getT() {
        return t;
    }

    public String getC() {
        return c;
    }

    public String getW() {
        return w;
    }

    public void setS(String s) {
        this.s = s;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public void setT(String t) {
        this.t = t;
    }

    public void setC(String c) {
        this.c = c;
    }

    public void setW(String w) {
        this.w = w;
    }
}
