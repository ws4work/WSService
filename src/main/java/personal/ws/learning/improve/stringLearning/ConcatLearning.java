package personal.ws.learning.improve.stringLearning;

/**
 * @Description:
 * @author: 王上
 * @date: 2017/10/18
 * @project：WSService
 */
public class ConcatLearning {

    public static void main(String args[]) {

        int max = 10000;

        plusString(max);
        concatString(max);
        //buffer和builder的区别要1亿数据才能凸显
        bufferString(max);
        builderString(max);

    }

    public static void plusString(int max) {
        long start = System.currentTimeMillis();
        System.out.println("加号操作开始:" + start);
        String str = "";
        for (int i = 0; i < max; i++) {
            str += "a";
        }
        long end = System.currentTimeMillis();
        System.out.println("加号操作结束:" + end);
        System.out.println("耗时:"+(end-start));
    }

    public static void concatString(int max) {
        long start = System.currentTimeMillis();
        System.out.println("concat操作开始:" + start);
        String str = "";
        for (int i = 0; i < max; i++) {
            str = str.concat("a");
        }
        long end = System.currentTimeMillis();
        System.out.println("concat操作结束:" + end);
        System.out.println("耗时:"+(end-start));
    }

    public static void bufferString(int max) {
        long start = System.currentTimeMillis();
        System.out.println("buffer操作开始:" + start);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < max; i++) {
            sb.append("a");
        }
        long end = System.currentTimeMillis();
        System.out.println("buffer操作结束:" + end);
        System.out.println("耗时:"+(end-start));
    }

    public static void builderString(int max) {
        long start = System.currentTimeMillis();
        System.out.println("builder操作开始:" + start);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < max; i++) {
            sb.append("a");
        }
        long end = System.currentTimeMillis();
        System.out.println("builder操作结束:" + end);
        System.out.println("耗时:"+(end-start));
    }

}
