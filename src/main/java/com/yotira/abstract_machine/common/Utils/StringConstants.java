package com.yotira.abstract_machine.common.Utils;

/**
 * @author chenyuting
 * @date 2016/6/17 11:05
 */
public final class StringConstants {

    public static final String CONS = "常量规则：(vs, const(c):e, sita) => (c:vs, e, sita)";

    public static final String VAR = "变量规则：(vs, var(c):e, sita) => (sita(x):vs, e, sita)";

    public static final String DEV = "除法规则：(n1:n2:vs, div:e, sita) => (n:vs, e, sita), n= n1/n2";

    public static final String MUL = "乘法规则：(n1:n2:vs, mul:e, sita) => (n:vs, e, sita), n= n1*n2";

    public static final String ADD = "加法规则：(n1:n2:vs, add:e, sita) => (n:vs, e, sita), n= n1+n2";

    public static final String SUB = "减法规则：(n1:n2:vs, sub:e, sita) => (n:vs, e, sita), n= n1-n2";

    public static final String SE = "比较规则：(n1:n2:vs, se:e, sita) => (n:vs, e, sita), n = (n1<=n2)";

    public static final String GE = "比较规则：(n1:n2:vs, ge:e, sita) => (n:vs, e, sita), n = (n1>=n2)";

    public static final String OP = "分解规则:(vs, op(e1,e2):e, sita) => (vs, e2:e1:op:e, sita)";

    //*******************************详细***********************************

    /*public static final String CONS = "常量规则：(vs, const(c):e, sita) => (c:vs, e, sita)";

    public static final String VAR = "变量规则：(vs, var(c):e, sita) => (sita(x):vs, e, sita)";

    public static final String DEV = "除法规则：(n1:n2:vs, div:e, sita) => (n:vs, e, sita), n= n1/n2";

    public static final String MUL = "乘法规则：(n1:n2:vs, mul:e, sita) => (n:vs, e, sita), n= n1*n2";

    public static final String ADD = "加法规则：(n1:n2:vs, add:e, sita) => (n:vs, e, sita), n= n1+n2";

    public static final String SUB = "减法规则：(n1:n2:vs, sub:e, sita) => (n:vs, e, sita), n= n1-n2";

    public static final String SE = "比较规则：(n1:n2:vs, se:e, sita) => (n:vs, e, sita), n = (n1<=n2)";

    public static final String GE = "比较规则：(n1:n2:vs, ge:e, sita) => (n:vs, e, sita), n = (n1>=n2)";

    public static final String OP = "分解规则:(vs, op(e1,e2):e, sita) => (vs, e2:e1:op:e, sita)";*/




}