package personal.ws.learning.question;

import java.util.Scanner;

/**
 * @Description:
 * @author: 王上
 * @date: 2017/9/15
 * @project：WSService
 */
public class Question12_Salaries {

    /**
     * 【程序12】
     * 题目：企业发放的奖金根据利润提成。利润(I)低于或等于10万元时，奖金可提10%；
     * 利润高于10万元，低于20万元时，低于10万元的部分按10%提成，高于10万元的部分，可可提成7.5%；
     * 20万到40万之间时，高于20万元的部分，可提成5%；40万到60万之间时高于40万元的部分，可提成3%；
     * 60万到100万之间时，高于60万元的部分，可提成1.5%，高于100万元时，超过100万元的部分按1%提成，
     * 从键盘输入当月利润I，求应发放奖金总数？
     */

    /**
     * 程序分析：请利用数轴来分界，定位。注意定义时需把奖金定义成长整型。
     */

    public static void main(String[] args) {
        double sum;//声明要储存的变量应发的奖金
        Scanner input = new Scanner(System.in);//导入扫描器
        System.out.print("输入当月利润");
        double lirun = input.nextDouble();//从控制台录入利润
        if(lirun <= 100000){
            sum = lirun * 0.1;
        } else if(lirun <= 200000){
            sum = 10000 + lirun * 0.075;
        } else if(lirun <= 400000){
            sum = 17500 + lirun * 0.05;
        } else if(lirun <= 600000){
            sum = lirun * 0.03;
        } else if(lirun <= 1000000){
            sum = lirun * 0.015;
        } else {
            sum = lirun * 0.01;
        }
        System.out.println("应发的奖金是" + sum);
    }
}
