package com.pae.server;

public enum Commands {
    AUTH_REQUEST ("/Auth "),
    AUTH_OK ("/Auth_OK"),
    AUTH_FAIL ("/Auth_FAIL"),
    AUTH_DUP ("/Auth_DUP"),
    AUTH_NICK ("/Auth_nick "),
    BL_LIST_ADD ("/Bl "),
    BL_LIST_REM ("/BlRem "),
    BL_LIST_REQ ("/BlReq"),
    CONN_STOP ("/end\r\n"),
    CONT_UPD ("/Clist"),
    HIST ("/Hist"),
    HIST_DROP ("/HisDrop"),
    PRIV_MESSAGE ("@"),
    REG_REQ ("/RegRq "),
    REG_OK ("/Reg_OK"),
    REG_FAIL ("/Reg_FAIL");

    String value;
    Commands(String value) {
        this.value = value;
    }

    public int getLength(){
        return this.value.length();
    }

    @Override
    public String toString() {
        return this.value;
    }
}
