package com.yotira.abstract_machine.common.cache;

import com.yotira.abstract_machine.common.entity.DEnv;

import java.util.List;
import java.util.Map;

/**
 * @author chenyuting
 * @date 2016/6/17 11:54
 */
public class AbstractMachineCache {

    // �������
    private String[] control;

    // ��̬����map
    private DEnv denv;

    // ջ�ռ�
    private String[] stack;

    // ��ǰҪ�����������
    private Integer ctrlsize;

    public String[] getControl() {
        return control;
    }

    public void setControl(String[] control) {
        this.control = control;
    }

    public DEnv getDenv() {
        return denv;
    }

    public void setDenv(DEnv denv) {
        this.denv = denv;
    }

    public String[] getStack() {
        return stack;
    }

    public void setStack(String[] stack) {
        this.stack = stack;
    }

    public Integer getCtrlsize() {
        return ctrlsize;
    }

    public void setCtrlsize(Integer ctrlsize) {
        this.ctrlsize = ctrlsize;
    }
}