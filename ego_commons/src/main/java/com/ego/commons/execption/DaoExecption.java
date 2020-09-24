package com.ego.commons.execption;

/**
 * 数据库操作异常
 */
public class DaoExecption extends Exception {

    public  DaoExecption(String message){
        super(message);
    }

}
