package com.yotira.abstract_machine.test;

import com.yotira.abstract_machine.common.cache.AbstractMachineCache;
import com.yotira.abstract_machine.service.AbstractMachineService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
        System.out.println("控制语句" + abstractMachineCache.getControl()[0]);
        System.out.println("动态变量" + abstractMachineCache.getDenv().getDenv().get("x"));

    }
}