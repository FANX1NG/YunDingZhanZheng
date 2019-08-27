package com.fanxing.yundingzhanzheng;

import java.io.Serializable;

/**
 * @author 繁星
 */
public class CanData implements Serializable {

    public int CanH;
    public int a;
    public int b;
    public int c = 0;

    public CanData(int canH, int a, int b) {
        CanH = canH;
        this.a = a;
        this.b = b;
    }
}
