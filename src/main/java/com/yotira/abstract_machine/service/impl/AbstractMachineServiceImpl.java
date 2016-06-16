package com.yotira.abstract_machine.service.impl;

import com.yotira.abstract_machine.service.AbstractMachineService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * AbstractMachineServiceImpl
 *
 *
 * @author chenyuting
 * @date 2016/6/16
 */
@Service("abstractMachineService")
public class AbstractMachineServiceImpl implements AbstractMachineService {
    private String ctrl[] = new String[111];
    private String stack[] = new String[111];
    private Map<String, Integer> Denv = new HashMap<String, Integer>();
    private int ctrlsize, stacktop;

    AbstractMachineServiceImpl(String ctrl_init, String Denv_init) {
        ctrlsize = 0; stacktop = 0; Denv.clear();
        ctrl[ctrlsize ++] = ctrl_init;
        init_Denv(Denv, Denv_init);
    }

    public String Run() {
        String out = "";
        System.out.println(out);
        while(ctrlsize > 0) {
            String now = ctrl[ctrlsize - 1];
            ctrlsize --;
//			System.out.println("Debug: " + now + " "  + "end");
            String fir = getfirString(now);
//			System.out.println("--> " + fir);
            if(fir.equals("ge")) {
//				System.out.println("Debug ge!");
                if(now.length() > 2) Devide(now);
                else gaoGe();
            }
            else if(fir.equals("se")) {
                if(now.length() > 2) Devide(now);
                else gaoSe();
            }
            else if(fir.equals("add")) {
                if(now.length() > 3) Devide(now);
                else gaoAdd();
            }
            else if(fir.equals("sub")) {
                if(now.length() > 3) Devide(now);
                else gaoSub();
            }
            else if(fir.equals("mul")) {
//				System.out.println("Debug mul!!!!" + fir);
                if(now.length() > 3) Devide(now);
                else gaoMul();
            }
            else if(fir.equals("div")) {
                if(now.length() > 3) Devide(now);
                else gaoDiv();
            }
            else if(fir.equals("cons")) {
                System.out.println("常量规则：(vs, const(c):e, sita) => (c:vs, e, sita)");
                stack[stacktop ++] = getAll(now);
            }
            else {
                System.out.println("变量规则：(vs, var(c):e, sita) => (sita(x):vs, e, sita)");
                stack[stacktop ++] = String.valueOf(Denv.get(getAll(now)));
            }
            out = "Control: [";
            for(int i = ctrlsize - 1; i >= 0; i --) {
                out += ctrl[i];
                if(i != 0) out += ", ";
            }
            out += "], Stack: [";
            for(int i = stacktop - 1; i >= 0; i --) {
                out += stack[i];
                if(i != 0) out += ", ";
            }
            out += "], DEnv: [";
            Boolean flag = false;
            for(Map.Entry<String, Integer> x: Denv.entrySet()) {
                if(flag) out += ", ";
                flag = true;
                out += x.getKey() + "->" + String.valueOf(x.getValue());
            }
            out += "]";
        }
        return out;
    }


    private String getAll(String now) {
        // TODO Auto-generated method stub
        int len = now.length(), i = 0;
        while(i < len && now.charAt(i) != '(') i ++;
        i ++;
        String ret = "";
        while(i < len && now.charAt(i) != ')') {
            ret += now.charAt(i);
            i ++;
        }
        return ret;
    }

    private void gaoDiv() {
        // TODO Auto-generated method stub
        System.out.println("除法规则：(n1:n2:vs, div:e, sita) => (n:vs, e, sita), n= n1/n2");
        int a = Integer.valueOf(stack[-- stacktop]);
        int b = Integer.valueOf(stack[-- stacktop]);
        stack[stacktop ++] = String.valueOf(a / b);
    }

    private void gaoMul() {
        // TODO Auto-generated method stub
        System.out.println("乘法规则：(n1:n2:vs, mul:e, sita) => (n:vs, e, sita), n= n1*n2");
        int a = Integer.valueOf(stack[-- stacktop]);
        int b = Integer.valueOf(stack[-- stacktop]);
        stack[stacktop ++] = String.valueOf(a * b);
    }

    private void gaoSub() {
        // TODO Auto-generated method stub
        System.out.println("减法规则：(n1:n2:vs, sub:e, sita) => (n:vs, e, sita), n= n1-n2");
        int a = Integer.valueOf(stack[-- stacktop]);
        int b = Integer.valueOf(stack[-- stacktop]);
        stack[stacktop ++] = String.valueOf(a - b);
    }

    private void gaoAdd() {
        // TODO Auto-generated method stub
        System.out.println("加法规则：(n1:n2:vs, add:e, sita) => (n:vs, e, sita), n= n1+n2");
        int a = Integer.valueOf(stack[-- stacktop]);
        int b = Integer.valueOf(stack[-- stacktop]);
        stack[stacktop ++] = String.valueOf(a + b);
    }

    private void gaoSe() {
        // TODO Auto-generated method stub
        System.out.println("比较规则：(n1:n2:vs, se:e, sita) => (n:vs, e, sita), n = (n1<=n2)");
        int a = Integer.valueOf(stack[-- stacktop]);
        int b = Integer.valueOf(stack[-- stacktop]);
        if(a <= b) stack[stacktop ++] = "true";
        else stack[stacktop ++] = "false";
    }

    private void gaoGe() {
        // TODO Auto-generated method stub
        System.out.println("比较规则：(n1:n2:vs, ge:e, sita) => (n:vs, e, sita), n = (n1>=n2)");
        int a = Integer.valueOf(stack[-- stacktop]);
        int b = Integer.valueOf(stack[-- stacktop]);
        if(a >= b) stack[stacktop ++] = "true";
        else stack[stacktop ++] = "false";
    }

    private String getfirString(String now) {
        // TODO Auto-generated method stub
        int len = now.length(), i = 0;
        char c = now.charAt(i);
        while(i < len && (c < 'a' || c > 'z')) {
            i ++;
            if(i < len) c = now.charAt(i);
        }
        String ret = "";
        while(i < len && c >= 'a' && c <= 'z') {
            ret += c;
            i ++;
            if(i < len) c = now.charAt(i);
        }
        return ret;
    }

    private void Devide(String now) {
        // TODO Auto-generated method stub
        System.out.println("分解规则:(vs, op(e1,e2):e, sita) => (vs, e2:e1:op:e, sita)");
        ctrl[ctrlsize ++] = getfirString(now);
        ctrl[ctrlsize ++] = getPartone(now);
        ctrl[ctrlsize ++] = getParttwo(now);
    }

    private String getPartone(String now) {
        // TODO Auto-generated method stub
        int len = now.length(), i = 0;
        while(i < len && now.charAt(i) != '(') i ++;
        i ++;
        String ret = "";
        int cnt = 0;
        while(i < len && now.charAt(i) == ' ') i ++;
//		System.out.print(i);
        while(i < len && (now.charAt(i) != ',' || cnt != 0)) {
            ret += now.charAt(i);
            if(now.charAt(i) == '(') cnt ++;
            if(now.charAt(i) == ')') cnt --;
            i ++;
        }
//		System.out.println(" Debug partone: " + ret);
        return ret;
    }

    private String getParttwo(String now) {
        // TODO Auto-generated method stub
        int len = now.length(), i = 0;
        while(i < len && now.charAt(i) != '(') i ++;
        i ++;
        int cnt = 0;
        while(i < len && now.charAt(i) == ' ') i ++;
//		System.out.print(i);
        while(i < len && (now.charAt(i) != ',' || cnt != 0)) {
            if(now.charAt(i) == '(') cnt ++;
            if(now.charAt(i) == ')') cnt --;
            i ++;
        }
        i ++;
        while(i < len && now.charAt(i) == ' ') i ++;
        String ret = "";
        while(i < len - 1) {
            ret += now.charAt(i);
            i ++;
        }
        return ret;
    }

    private static void init_Denv(Map<String, Integer> Denv, String Denv_init) {
        // TODO Auto-generated method stub
        int len = Denv_init.length();

        for(int i = 0; i < len; i ++) {
            char c = Denv_init.charAt(i);
            while(i < len && (c < 'a' || c > 'z')) {
                i ++;
                if(i < len) c = Denv_init.charAt(i);
            }
            String tmp = "";
            while(i < len && c >= 'a' && c <= 'z') {
                tmp += c;
                i ++;
                if(i < len) c = Denv_init.charAt(i);
            }
            while(i < len && (c < '0' || c > '9')) {
                i ++;
                if(i < len) c = Denv_init.charAt(i);
            }
            int num = 0;
            while(i < len && c >= '0' && c <= '9') {
                num = num * 10 + c - '0';
                i ++;
                if(i < len) c = Denv_init.charAt(i);
            }
            Denv.put(tmp, num);
        }
    }
}
