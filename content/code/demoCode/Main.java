package demoCode;


	import java.util.Scanner;//导入 Java Util 包

    /**
     * 总体来会说该代码块实现以下需求，
     * 由 Scanner（System.in）方法监听键盘录入的字符，录入在敲下回车键（Enter）后停止录入，
     * 根据条件判断录入的字符串所能返回的参数值，
     * 判断条件为，判断条件是字符串的长度大于等于6，小于等于20，并且字符串中必须包含“@#$”中的一个特殊字符，还要包含0到9之间的数字
     * 如果满足条件n等于1，系统输出 Valid，
     * 如果不能满足条件n等于1，系统输出Invalid
     */
	
	public class Main {
	     public static void main(String[] args) {//构造主函数
	     Scanner sc = new Scanner(System.in);  //Java 输入流，sc 表示监听所有输入字符
	     String s1 = sc.nextLine(); // s1记录所有输入在回车键之前的字符流
	     int n = UserMainCode.goFigure(s1); //n 为，UserMainCode类中 goFigure方法中所返回的值。
	     if(n==1){ //当 n的值为1，
	        System.out.println("Valid"); //控制台输出 Valid
	        }
	     else{ //当n，的值不为1 
	        System.out.println("Invalid"); //控制台输出Invalid
	        }
	     }
	}
	
	class UserMainCode {
	
	     public static int goFigure(String p){
	     if(p.matches(".*[0-9]{1,2}.*") && p.matches(".*[@#$]{1}.*") && p.length()>=6 &&         
	                  p.length()<=20){ //判断条件是字符串的长度大于等于6，小于等于20，并且字符串中必须包含“@#$”中的一个特殊字符，还要包含0到9之间的数字
	                        return 1;
	                        }
	     else{ //不满足以上匹配规则
	                        return -1; //返回-1

	                        }
	      }
	}
