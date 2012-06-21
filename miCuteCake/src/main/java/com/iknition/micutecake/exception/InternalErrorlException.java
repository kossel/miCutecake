/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iknition.micutecake.exception;

/**
 *
 * @author coslit
 */
public class InternalErrorlException extends Exception{
    
    private ErrObj errObj;
    
    public InternalErrorlException(int errorNum, String msj){
        this.errObj=new ErrObj();
        errObj.setErrorNum(errorNum);
        errObj.setMsj(msj);
    }
    
    public InternalErrorlException(ErrObj obj){
        this.errObj= obj;
    }

    public ErrObj getErrObj() {
        return errObj;
    }

    public void setErrObj(ErrObj errObj) {
        this.errObj = errObj;
    }
    
    
}
