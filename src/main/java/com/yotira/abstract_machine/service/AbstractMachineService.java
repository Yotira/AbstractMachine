package com.yotira.abstract_machine.service;/**
 * Created by Administrator on 2016/6/16.
 */

import com.yotira.abstract_machine.common.cache.AbstractMachineCache;

/**
 * AbstractMachineService
 *
 * @author chenyuting
 * @date 2016/6/16
 */
public interface AbstractMachineService {

    /**
     * 初始化控制语句和动态变量
     * @param control
     * @param denv
     */
    public AbstractMachineCache init(String control,String denv);

    public String Run(AbstractMachineCache abstractMachineCache);
}
