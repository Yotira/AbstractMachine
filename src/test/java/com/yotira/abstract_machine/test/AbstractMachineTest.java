package com.yotira.abstract_machine.test;

import com.yotira.abstract_machine.common.cache.AbstractMachineCache;
import com.yotira.abstract_machine.common.entity.Output;
import com.yotira.abstract_machine.service.AbstractMachineService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author chenyuting
 * @date 2016/6/17 16:53
 */
public class AbstractMachineTest extends AbstractTestCase{

    @Autowired
    private AbstractMachineService abstractMachineService;

    @Test
    public void test() throws Exception{
        String control = "ge(add(var(x),mul(cons(2),var(y))),var(z))";
        String denv = "[x->34, y->7, z->50]";
        AbstractMachineCache abstractMachineCache = abstractMachineService.init(control, denv);
        System.out.println("控制语句：" + abstractMachineCache.getControl()[0]);
        System.out.println("动态变量：" + abstractMachineCache.getDenv().get("x"));

    }

    @Test
    public void testRun() throws Exception{
        String control = "ge(add(var(x),mul(cons(2),var(y))),var(z))";
        String denv = "[x->34, y->7, z->50]";
        AbstractMachineCache abstractMachineCache = abstractMachineService.init(control, denv);

        List<Output> outputList = abstractMachineService.Run(abstractMachineCache);
        for (Output output: outputList){
            System.out.println("控制语句：" + output.getControl());
            System.out.println("栈：" + output.getStack());
            System.out.println("完整子句：" + output.getList());
            System.out.println("当前规则：" + output.getRule());
            System.out.println();
        }
    }
//    ge(var(z),add(mul(var(x),var(y)),cons(2)))
//    [x->2, y->10, z->30]
}