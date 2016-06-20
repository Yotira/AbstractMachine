package com.yotira.abstract_machine.common.entity;

import java.util.List;

/**
 * @author chenyuting
 * @date 2016/6/17 14:58
 */
public class Output {

    // 控制语句
    private String control;

    // 栈
    private String stack;

    // 当前使用规则
    private String rule;

    //当前语句完整结果
    private String list;

    public String getControl() {
        return control;
    }

    public void setControl(String control) {
        this.control = control;
    }

    public String getStack() {
        return stack;
    }

    public void setStack(String stack) {
        this.stack = stack;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }
}