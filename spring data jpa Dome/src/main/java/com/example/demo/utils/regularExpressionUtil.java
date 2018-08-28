package com.example.demo.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class regularExpressionUtil {

    /**
     * 通过正则表达式过滤、
     * @param compileExpression 表达式:url：[http]{4}\\:\\/\\/([a-zA-Z]|[0-9])*(\\.([a-zA-Z]|[0-9])*)*(\\/([a-zA-Z]|[0-9])*)*\\s?
     *                          @param matcherExpression  待验证
     * */
    public  boolean isIn(String compileExpression ,String matcherExpression){
        Pattern pattern =  Pattern.compile(compileExpression);
        Matcher matcher = pattern.matcher(matcherExpression);
        if(matcher.find())
            return true;  //2015年的列表
        else
            return false;
    }
}
