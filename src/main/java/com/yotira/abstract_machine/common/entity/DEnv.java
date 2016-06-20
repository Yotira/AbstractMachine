package com.yotira.abstract_machine.common.entity;

import java.util.Map;

/**
 * @author chenyuting
 * @date 2016/6/17 11:41
 */
public class DEnv {

    // 动态变量
    private  Map<String,Integer>  denv;

    public Map<String, Integer> getDenv() {
        return denv;
    }

    public void setDenv(Map<String, Integer> denv) {
        this.denv = denv;
    }
}