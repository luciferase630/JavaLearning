package FourthChapterExceptionAndjavadocUnit3;

import ExceptionExample.OutOfRangeException;
import java.io.*;
public class YiChang {
}

/**
 * 这是一个为了第“7”条内容做代码支撑的示例
 * @author 方镐翔  632077567@qq.com
 * @author 刘乙麟 （希望真的有这么一天）
 * @version 1.0.12
 * @see ExceptionExample.OutOfRangeException 这个是用来了解自己在其他包内定义的异常类的
 * @see YiChangmain #readInteger() 推荐查看如何从控制台读取输入
 * @see #stdIn 标准输入流
 * @see OutOfRangeException#getA() ;
 */
// * @param
// * @return
// * @exception
    //没办法，这是我在一个类前面创建的java文档注释，自然是不能有这些参数，返回值和异常的
    //顺带提一嘴，这个@see 是用来产生一个html超链接的，指向其他文档或者当前文档的其他位置
    //工具->生成javadoc->记得要改成private级别。记得看级别！！！
class YiChangmain{




    /**
     * 这个函数主要是为了在第“2”部分讲解这个throw关键字
     * 这段话会出现在方法摘要部分，算是复习javadoc了，但是还不够
     * 这个函数判断这个初始值是否为正
     *
     * @param initialValue 就是一开始的初始值
     * @return 如果为正久返回
     */
    private int positiveInteger(int initialValue)
    {


        if (initialValue < 0){
            throw new OutOfRangeException("Number is not positive");//这是一个自定义异常类
        }
        else{
            return initialValue;
        }
    }

    /**
     * 这是在定义标准输入流
     */
    private static BufferedReader stdIn=new BufferedReader(new InputStreamReader(System.in));//卧槽，这类型是什么？
    //卧槽，这构造器的传入参数居然是一个输入流对象，
    /**
     * 定义标准错误流
     */
    private static PrintWriter stdErr = new PrintWriter(System.err,true);//自动清除缓冲区？这我还是不太能理解
    /**
     * 定义标准输出流
     */
    private static PrintWriter stdOut =new PrintWriter(System.out,true);

    /**
     * 这主要是为了讲解部分"4",我也想知道java怎么从控制台读东西进来，我不想用scanner！！
     * 这个方法是为了从控制台读取一个整数
     * @return
     */
    public static int readInteger(){
        do{
            try{
                stdErr.print("Enter an integer > ");
                stdErr.flush();
                return Integer.parseInt(stdIn.readLine());
            }
            catch (NumberFormatException nfe){
                stdErr.println("Invalid number format");
            }
            catch (IOException ioe){//这经典的输入输出流错误，我都不知道怎么触发
                ioe.printStackTrace();
                System.exit(1);//这个1是什么意思？
            }
            stdOut.println("running after catch");
            stdOut.println("-----------------------");

        }
        while(true);


    }

    /**
     * 就没什么好说的，演示一下可能抛出异常的函数如何调用
     * @throws Exception 就是普通的Exception而已
     */
    public static void f()throws Exception{
        throw new Exception("This is the Exception of fuction f()");

    }
    public static void main(String[] args) {
        /*
        1，异常处理机制的引入，就是觉得ifelse，ifelse会影响正常逻辑的业务代码，把所有出现的异常封装成为了对象
        关于各种异常的继承关系你得好好看看书p97,。
        2，throw关键字，就是用来手动抛出异常的，如果没有处理，这个异常会逐级上抛，如果没有处理最后到main里就会异常终止程序
        3，try-catch关键字，try一段可能出现异常的代码，而catch后面的参数是某种类型的异常，我在后面都写好了，语法看一眼就知道了
        4，书中小例子，关于如何从控制台读取一个整数，我真是服了，这里用的很多都没学，我这api没学我看个集贸的书啊？
        读取整数的例子，痛苦死我了
        5还有上课当时讲的，为了避免什么输入流输出流一直被打开，用try with source什么的？
        6异常类和throws关键字，就是相当于是给大家提示，啊我这个函数是会抛出异常的，记得处理。
        我会在后面写一个使用了throws关键字的函数
        补充getMessage返回简短消息，printStackTrace直接把这个throwable和追踪信息输入到标准错误流（红色字体吧)
        然后toString比message稍微多一点{
        Number is not positive
        -----------------------
        Example.OutOfRangeException: Number is not positive
        }
        7javadoc文档的编辑。
         */
        YiChangmain eg1=new YiChangmain();

        try {
            f();
            eg1.positiveInteger(-5);//这样就已经能看到这个异常是Exception in thread "main"里了，主线程异常
        }
        catch (RuntimeException exception1){
            StackTraceElement[] elements = exception1.getStackTrace();
            //以上是gpt给我教的怎么用这个getStackTrace数组的,这™居然还有这种奇怪的StackTraceElement这样的类型
            System.out.println(exception1.getMessage());
            System.out.println("-----------------------");
            System.out.println(exception1.toString());
            System.out.println("-----------------------");
            exception1.printStackTrace();//这个和我手动遍历StackTraceElement还是有一点点区别
            for (StackTraceElement element:elements){
                System.out.println(element);
            }

        }
        catch (Exception exception2){
            exception2.printStackTrace();//说什么应该替换为更可靠的日志，也不知道在说啥

        }
        stdOut.println("The value is :"+readInteger());//除非读取到整数不然我不给你你过
    }
}
