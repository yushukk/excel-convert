package org.erik;

/**
 * Write class comments here.
 * User: caiwd
 * Date: 14-5-26 ����4:20
 * version $Id: Result.java, v0.1 Exp $.
 */
public class Result {

    /** ��������־ */
    protected boolean success = false;

    /** �����*/
    protected String  resultCode;

    /** �����Ϣ*/
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
