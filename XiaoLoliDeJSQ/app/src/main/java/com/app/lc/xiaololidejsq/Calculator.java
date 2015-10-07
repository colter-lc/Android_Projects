package com.app.lc.xiaololidejsq;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by LC on 10/3/2015.
 */
public class Calculator {
    private static String plus = "+";
    private static String minus = "-";
    private static String multiply = "*";
    private static String divide = "/";
    private static String lBracket = "(";
    private static String rBracket = ")";

    //遍历循环 得到的后缀表达式，依次压入栈中，如果遇到符号，取出来最近的两个，计算后在压入栈中
    public   double getValue(Object[] expression){
        Stack stack = new Stack();
        for (int i = 0; i < expression.length; i++) {
            String temp = (String) expression[i];
            if(isOprator(temp)){
                double d2 = (Double) stack.pop();
                double d1 = (Double) stack.pop();
                double result = 0;
                if(plus.equals(temp)){
                    result = d1 + d2;
                }else if(minus.equals(temp)){
                    result = d1 - d2;
                }else if(multiply.equals(temp)){
                    result = d1 * d2;
                }else if(divide.equals(temp)){
                    result = d1/d2;
                }
                stack.push(result);

            }else{
                stack.push(Double.parseDouble(temp));
            }
        }

        return (Double)stack.top();
    }

    public  Object[] changeToPostFixExpression(String expression){
        //将 符号替换为前后有空格
        expression = expression.replace(plus, " "+plus + " ");
        expression = expression.replace(minus, " "+minus + " ");
        expression = expression.replace(multiply, " "+multiply + " ");
        expression = expression.replace(divide, " "+divide + " ");
        expression = expression.replace(lBracket, " "+lBracket + " ");
        expression = expression.replace(rBracket, " "+rBracket + " ");

        Object[] objs = expression.split(" ");


        Stack noStack = new Stack();
        Stack operatorStack = new Stack();
        for (int i = 0; i < objs.length; i++) {
            String temp = String.valueOf(objs[i]);

            if("".equals(temp.trim())){
                continue;
            }

            //如果是操作数  直接压入栈中
            if(isNum(temp)){
                noStack.push(temp);
            }else{
                if(isOprator(temp)){
                    Object top = operatorStack.top();
                    if(isRightGreater((String)top, temp)){
                        operatorStack.push(temp);
                    }else{
                        while( true){
                            if(isRightGreater((String)top, temp)){
                                operatorStack.push(temp);
                                break;
                            }else{
                                noStack.push(top);
                                operatorStack.pop();
                                top = operatorStack.top();
                            }
                        }
                    }
                }else{
                    if(temp.equals(lBracket)){
                        operatorStack.push(temp);
                    }else if(temp.equals(rBracket)){
                        Object top = operatorStack.top();
                        while( top != null){
                            if(top.equals(lBracket)){
                                operatorStack.pop();
                                break;
                            }else{
                                Object pop = operatorStack.pop();
                                noStack.push(pop);
                                top = operatorStack.top();
                            }
                        }
                    }
                }
            }
        }

        Object last = null;

        while( (last = operatorStack.top())!= null ){
            if(lBracket.equals((String)last) || rBracket.equals((String)last) ){
                operatorStack.pop();
            }else{
                noStack.push(last);
                operatorStack.pop();
            }
        }
        System.out.println(noStack.toString());
        return noStack.toArray();
    }

    /***
     * 判断右边优先级是否大于左边
     */
    private  boolean isRightGreater(String lOperator,String rOperator){
        //左边为 + - 并且右边为 */， 此时右边优先级大于左边 (或者左边是左括号,或者栈顶为空)
        boolean flag = (plus.equals(lOperator) || minus.equals(lOperator))&& (multiply.equals(rOperator) || divide.equals(rOperator))
                || (lBracket.equals(lOperator)) || lOperator==null;
        return flag;
    }

    //判断是否为加减乘除
    private  boolean isOprator(String str){
        return str.equals(plus) || str.equals(minus) || str.equals(multiply)
                || str.equals(divide);
    }

    //判断是否为数字
    private static boolean isNum(String str){
        return !(str.equals(plus) || str.equals(minus) || str.equals(multiply)
                || str.equals(divide) || lBracket.equals(str) || rBracket.equals(str));
    }
}


class Stack{
    private List<Object> list;

    public Stack(){
        list =  new ArrayList<Object>();
    }

    public void push(Object e){
        list.add(e);
    }

    public Object pop(){
        if(list.size() == 0){
            return null;
        }else{
            Object e = list.get(list.size()-1);
            list.remove(list.size()-1);
            return e;
        }
    }

    public Object top(){
        if(list.size() == 0){
            return null;
        }else{
            Object e = list.get(list.size()-1);
            return e;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if(top() == null){
            return "";
        }else{
            for (Object o :list) {
                sb.append(o);
                sb.append(" ");
            }
            return sb.toString();
        }
    }

    public Object[] toArray() {
        return list.toArray();
    }

}
