package com.yotira.abstract_machine.common.cache;

import com.yotira.abstract_machine.common.entity.DEnv;

import java.util.List;
import java.util.Map;

/**
 * @author chenyuting
 * @date 2016/6/17 11:54
 */
public class AbstractMachineCache {

    // 控制语句
    private String[] control;

    // 动态变量map
    private  Map<String,Integer>  denv;

    // 栈空间
    private String[] stack;

    // 当前要处理的语句个数
    private Integer ctrlsize;

    // 栈顶控制
    private Integer stacktop;

    public String[] getControl() {
        return control;
    }

    public void setControl(String[] control) {
        this.control = control;
    }

    public Map<String, Integer> getDenv() {
        return denv;
    }

    public void setDenv(Map<String, Integer> denv) {
        this.denv = denv;
    }

    public String[] getStack() {
        if (this.stack == null){
            String[] stack = new String[111];
            this.stack = stack;
        }
        return stack;
    }

    public void setStack(String[] stack) {
        this.stack = stack;
    }

    public Integer getCtrlsize() {
        if (this.ctrlsize == null){
            ctrlsize = 0;
        }
        return ctrlsize;
    }

    public void setCtrlsize(Integer ctrlsize) {
        if (ctrlsize == null){
            this.ctrlsize = 0;
        }else {
            this.ctrlsize = ctrlsize;
        }
    }

    public Integer getStacktop() {
        if (this.stacktop == null){
            stacktop = 0;
        }
        return stacktop;
    }

    public void setStacktop(Integer stacktop) {
        if (stacktop == null){
            this.stacktop = 0;
        }else {
            this.stacktop = stacktop;
        }
    }
}