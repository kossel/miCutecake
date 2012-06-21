/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iknition.micutecake.exception;

/**
 *
 * @author coslit
 */
public class ErrObj {
    private int errorNum;
    private String Msj;

    public static int ERROR =1;
    public static int WARNING =2;
    public static int NOTICE =3;
    
    public String getMsj() {
        return Msj;
    }

    public void setMsj(String Msj) {
        this.Msj = Msj;
    }

    public int getErrorNum() {
        return errorNum;
    }

    public void setErrorNum(int errorNum) {
        this.errorNum = errorNum;
    }

    
}
