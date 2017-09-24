package personal.ws.learning.question;

/**
 * @Description:
 * @author: 王上
 * @date: 2017/9/15
 * @project：WSService
 */
public class Question5_JudgeGrades {

    /**
     * 【程序5】
     * 题目：利用条件运算符的嵌套来完成此题：学习成绩> =90分的同学用A表示，60-89分之间的用B表示，60分以下的用C表示。
     */

    /**
     * 1.程序分析：(a> b)?a:b这是条件运算符的基本例子。
     */
    public static void main(String[] args){
        String str="96";
        int N;
        N=0;
        try{
            N=Integer.parseInt(str);
        }
        catch(NumberFormatException e){
            e.printStackTrace();
        }
        str=(N>90?"A":(N>60?"B":"C"));
        System.out.println(str);
    }
}
