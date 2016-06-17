package com.yotira.abstract_machine.test;

import com.yotira.abstract_machine.common.cache.AbstractMachineCache;
import com.yotira.abstract_machine.service.AbstractMachineService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author chenyuting
 * @date 2016/6/17 16:53
 */
public class AbstractMachineTest extends AbstractTestCase{

    @Autowired
    private AbstractMachineService abstractMachineService;

    @Test
    public void test() throws Exception{
        String control = "";
        String denv = "";
        AbstractMachineCache abstractMachineCache = abstractMachineService.init(control, denv);
        System.out.println(abstractMachineCache.getControl());
        System.out.println(abstractMachineCache.getDenv());

    }
}