package org.erik;

/**
 * Write class comments here.
 * User: caiwd
 * Date: 14-5-26 下午4:20
 * version $Id: Result.java, v0.1 Exp $.
 */
public class Result {

    /** 处理结果标志 */
    protected boolean success = false;

    /** 结果码*/
    protected String  resultCode;

    /** 结果信息*/
    protected String  resultMsg;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }
}
