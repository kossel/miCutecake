/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iknition.micutecake.exception;

/**
 *
 * @author coslit
 */
public class BusinessRuleException extends Exception{
    private ErrObj errObj;
    
    public BusinessRuleException(int errorNum, String msj){
        this.errObj=new ErrObj();
        errObj.setErrorNum(errorNum);
        errObj.setMsj(msj);
    }
    
    public BusinessRuleException(ErrObj obj){
        this.errObj= obj;
    }

    public ErrObj getErrObj() {
        return errObj;
    }

    public void setErrObj(ErrObj errObj) {
        this.errObj = errObj;
    }
}
