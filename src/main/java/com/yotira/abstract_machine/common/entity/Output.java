package com.yotira.abstract_machine.common.entity;

import java.util.List;

/**
 * @author chenyuting
 * @date 2016/6/17 14:58
 */
public class Output {

    // �������
    private String control;

    // ��̬����
    private String denv;

    // ջ
    private String stack;

    // ��ǰʹ�ù���
    private List<String> rule;

    public String getControl() {
        return control;
    }

    public void setControl(String control) {
        this.control = control;
    }

    public String getDenv() {
        return denv;
    }

    public void setDenv(String denv) {
        this.denv = denv;
    }

    public String getStack() {
        return stack;
    }

    public void setStack(String stack) {
        this.stack = stack;
    }

    public List<String> getRule() {
        return rule;
    }

    public void setRule(List<String> rule) {
        this.rule = rule;
    }
}