package com.yotira.abstract_machine.common.Utils;

/**
 * @author chenyuting
 * @date 2016/6/17 11:05
 */
public final class StringConstants {

    public static final String CONS = "��������(vs, const(c):e, sita) => (c:vs, e, sita)";

    public static final String VAR = "��������(vs, var(c):e, sita) => (sita(x):vs, e, sita)";

    public static final String DEV = "��������(n1:n2:vs, div:e, sita) => (n:vs, e, sita), n= n1/n2";

    public static final String MUL = "�˷�����(n1:n2:vs, mul:e, sita) => (n:vs, e, sita), n= n1*n2";

    public static final String ADD = "�ӷ�����(n1:n2:vs, add:e, sita) => (n:vs, e, sita), n= n1+n2";

    public static final String SUB = "��������(n1:n2:vs, sub:e, sita) => (n:vs, e, sita), n= n1-n2";

    public static final String SE = "�ȽϹ���(n1:n2:vs, se:e, sita) => (n:vs, e, sita), n = (n1<=n2)";

    public static final String GE = "�ȽϹ���(n1:n2:vs, ge:e, sita) => (n:vs, e, sita), n = (n1>=n2)";

    public static final String OP = "�ֽ����:(vs, op(e1,e2):e, sita) => (vs, e2:e1:op:e, sita)";



}