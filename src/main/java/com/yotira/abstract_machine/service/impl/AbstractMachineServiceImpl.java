package com.yotira.abstract_machine.service.impl;

import com.yotira.abstract_machine.common.Utils.StringConstants;
import com.yotira.abstract_machine.common.cache.AbstractMachineCache;
import com.yotira.abstract_machine.common.entity.*;
import com.yotira.abstract_machine.service.AbstractMachineService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    // 缓存
    AbstractMachineCache abstractMachineCache;

    // 待处理控制语句个数、栈顶
    //private int ctrlsize;
    //private int stacktop;

    public AbstractMachineCache init(String controlInput, String denvInput){
        abstractMachineCache = new AbstractMachineCache();
        Integer ctrlsize = 0;
        // 字符串数组，初始长度为1，只有controlInput
        String ctrl[] = new String[111];
        ctrl[ctrlsize ++] = controlInput;
        abstractMachineCache.setCtrlsize(ctrlsize);

        // 初始化动态变量
        DEnv denv = init_Denv(denvInput);
        // 将输入的控制语句和动态变量放入缓存
        abstractMachineCache.setControl(ctrl);
        abstractMachineCache.setDenv(denv.getDenv());
        return abstractMachineCache;
    }


    public List<Output> Run(AbstractMachineCache abstractMachineCache) {
        List<Output> outputList = new ArrayList<Output>();
        String[] stack = abstractMachineCache.getStack();
        Integer ctrlsize = abstractMachineCache.getCtrlsize();
        while(ctrlsize > 0) {
            String outList = "";
            Output output = new Output();
            // 获取当前要处理的字符串，控制语句数组的最后一个（数组是倒着存的）
            String now = abstractMachineCache.getControl()[abstractMachineCache.getCtrlsize()-1];
            abstractMachineCache.setCtrlsize(ctrlsize-1);
            String fir = getfirString(now);
            if(fir.equals("ge")) {
                if(now.length() > 2) Devide(now);
                else gaoGe(abstractMachineCache);
            }
            else if(fir.equals("se")) {
                if(now.length() > 2) Devide(now);
                else gaoSe(abstractMachineCache);
            }
            else if(fir.equals("add")) {
                if(now.length() > 3) Devide(now);
                else gaoAdd(abstractMachineCache);
            }
            else if(fir.equals("sub")) {
                if(now.length() > 3) Devide(now);
                else gaoSub(abstractMachineCache);
            }
            else if(fir.equals("mul")) {
//				System.out.println("Debug mul!!!!" + fir);
                if(now.length() > 3) Devide(now);
                else gaoMul(abstractMachineCache);
            }
            else if(fir.equals("div")) {
                if(now.length() > 3) Devide(now);
                else gaoDiv(abstractMachineCache);
            }
            else if(fir.equals("cons")) {
                //System.out.println("常量规则：(vs, const(c):e, sita) => (c:vs, e, sita)");
                // 常量入栈
                Integer stacktop = abstractMachineCache.getStacktop();
                stack[stacktop ++] = getAll(now);
                abstractMachineCache.setStacktop(stacktop);
                abstractMachineCache.setStack(stack);
            }
            else {
                //System.out.println("变量规则：(vs, var(c):e, sita) => (sita(x):vs, e, sita)");
                // 变量先找动态环境值，赋值后入栈
                Integer stacktop = abstractMachineCache.getStacktop();
                stack[stacktop ++] = String.valueOf(abstractMachineCache.getDenv().get(getAll(now)));
                abstractMachineCache.setStacktop(stacktop);
                abstractMachineCache.setStack(stack);
            }

            String controlOut = "";
            //out = "Control: [";
            for(int i = ctrlsize - 1; i >= 0; i --) {
                controlOut += abstractMachineCache.getControl()[i];
                if(i != 0) controlOut += ", ";
            }
            output.setControl(controlOut);

            String stackOut = "";
            //out += "], Stack: [";
            for(int i = abstractMachineCache.getStacktop() - 1; i >= 0; i --) {
                stackOut += stack[i];
                if(i != 0) stackOut += ", ";
            }
            output.setStack(stackOut);

            String denvOut = "";
            //out += "], DEnv: [";
            Boolean flag = false;
            for(Map.Entry<String, Integer> x: abstractMachineCache.getDenv().entrySet()) {
                if(flag) stackOut += ", ";
                flag = true;
                denvOut += x.getKey() + "->" + String.valueOf(x.getValue());
            }
            //out += "]";
            outList = "Control:[" + outList + "],Stack:[" + stackOut + "],DEnv:[" + denvOut +"]";
            output.setList(outList);
            outputList.add(output);
        }
        return outputList;
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

    private String gaoDiv(AbstractMachineCache abstractMachineCache) {
        // TODO Auto-generated method stub
        //System.out.println("除法规则：(n1:n2:vs, div:e, sita) => (n:vs, e, sita), n= n1/n2");
        // 取出栈顶的两个值
        StackTopTwo stackTopTwo = this.getStackTopTwoNumber(abstractMachineCache);
        String[] stack = abstractMachineCache.getStack();
        // 计算
        Integer stacktop = abstractMachineCache.getStacktop();
        stack[stacktop ++] = String.valueOf(stackTopTwo.getNum1() / stackTopTwo.getNum2());
        abstractMachineCache.setStacktop(stacktop);
        abstractMachineCache.setStack(stack);
        return StringConstants.DEV;
    }

    private String gaoMul(AbstractMachineCache abstractMachineCache) {
        // TODO Auto-generated method stub
        //System.out.println("乘法规则：(n1:n2:vs, mul:e, sita) => (n:vs, e, sita), n= n1*n2");
        StackTopTwo stackTopTwo = this.getStackTopTwoNumber(abstractMachineCache);
        String[] stack = abstractMachineCache.getStack();
        Integer stacktop = abstractMachineCache.getStacktop();
        stack[stacktop ++] = String.valueOf(stackTopTwo.getNum1() * stackTopTwo.getNum2());
        abstractMachineCache.setStacktop(stacktop);
        abstractMachineCache.setStack(stack);
        return StringConstants.MUL;
    }

    private String gaoSub(AbstractMachineCache abstractMachineCache) {
        // TODO Auto-generated method stub
        //System.out.println("减法规则：(n1:n2:vs, sub:e, sita) => (n:vs, e, sita), n= n1-n2");
        StackTopTwo stackTopTwo = this.getStackTopTwoNumber(abstractMachineCache);
        String[] stack = abstractMachineCache.getStack();
        Integer stacktop = abstractMachineCache.getStacktop();
        stack[stacktop ++] = String.valueOf(stackTopTwo.getNum1() - stackTopTwo.getNum2());
        abstractMachineCache.setStacktop(stacktop);
        abstractMachineCache.setStack(stack);
        return StringConstants.SUB;
    }

    private String gaoAdd(AbstractMachineCache abstractMachineCache) {
        // TODO Auto-generated method stub
        //System.out.println("加法规则：(n1:n2:vs, add:e, sita) => (n:vs, e, sita), n= n1+n2");
        StackTopTwo stackTopTwo = this.getStackTopTwoNumber(abstractMachineCache);
        String[] stack = abstractMachineCache.getStack();
        Integer stacktop = abstractMachineCache.getStacktop();
        stack[stacktop ++] = String.valueOf(stackTopTwo.getNum1() + stackTopTwo.getNum2());
        abstractMachineCache.setStacktop(stacktop);
        abstractMachineCache.setStack(stack);
        return StringConstants.ADD;
    }

    private String gaoSe(AbstractMachineCache abstractMachineCache) {
        // TODO Auto-generated method stub
        //System.out.println("比较规则：(n1:n2:vs, se:e, sita) => (n:vs, e, sita), n = (n1<=n2)");
        StackTopTwo stackTopTwo = this.getStackTopTwoNumber(abstractMachineCache);
        String[] stack = abstractMachineCache.getStack();
        Integer stacktop = abstractMachineCache.getStacktop();
        if(stackTopTwo.getNum1() <= stackTopTwo.getNum2()) {
            stack[stacktop ++] = "true";
        }
        else {
            stack[stacktop ++] = "false";
        }
        abstractMachineCache.setStacktop(stacktop);
        abstractMachineCache.setStack(stack);
        return StringConstants.SE;
    }

    private String gaoGe(AbstractMachineCache abstractMachineCache) {
        // TODO Auto-generated method stub
        //System.out.println("比较规则：(n1:n2:vs, ge:e, sita) => (n:vs, e, sita), n = (n1>=n2)");
        StackTopTwo stackTopTwo = this.getStackTopTwoNumber(abstractMachineCache);
        String[] stack = abstractMachineCache.getStack();
        Integer stacktop = abstractMachineCache.getStacktop();
        if(stackTopTwo.getNum1() >= stackTopTwo.getNum2()){
            stack[stacktop ++] = "true";
        }
        else stack[stacktop ++] = "false";
        abstractMachineCache.setStacktop(stacktop);
        abstractMachineCache.setStack(stack);
        return StringConstants.GE;
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

    private String Devide(String now) {
        // TODO Auto-generated method stub
        //System.out.println("分解规则:(vs, op(e1,e2):e, sita) => (vs, e2:e1:op:e, sita)");
        String[] control = abstractMachineCache.getControl();
        Integer ctrlsize = abstractMachineCache.getCtrlsize();
        control[ctrlsize ++] = getfirString(now);
        control[ctrlsize ++] = getPartone(now);
        control[ctrlsize ++] = getParttwo(now);

        abstractMachineCache.setCtrlsize(ctrlsize);
        abstractMachineCache.setControl(control);
        return StringConstants.OP;
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

    private DEnv init_Denv(String Denv_init) {
        // TODO Auto-generated method stub
        DEnv denv = new DEnv();
        Map<String, Integer> denvMap = new HashMap<String, Integer>();

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
            Integer num = 0;

            while(i < len && c >= '0' && c <= '9') {
                num = num * 10 + c - '0';
                i ++;
                if(i < len) c = Denv_init.charAt(i);
            }
            denvMap.put(tmp, num);
            denv.setDenv(denvMap);
        }
        return denv;
    }

    /**
     * 私有方法
     * 获取栈顶的两个值
     *
     * @param abstractMachineCache
     * @return
     */
    private StackTopTwo getStackTopTwoNumber(AbstractMachineCache abstractMachineCache){
        String[] nowStack = abstractMachineCache.getStack();
        Integer stacktop = abstractMachineCache.getStacktop();
        Integer num1 = Integer.valueOf(nowStack[-- stacktop]);
        Integer num2 = Integer.valueOf(nowStack[-- stacktop]);
        abstractMachineCache.setStacktop(stacktop);
        abstractMachineCache.setStack(nowStack);
        StackTopTwo stackTopTwo = new StackTopTwo();
        stackTopTwo.setNum1(num1);
        stackTopTwo.setNum2(num2);
        return stackTopTwo;
    }
}
