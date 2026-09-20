
import java.io.FileInputStream;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws Exception {
        try(FileInputStream fis = new FileInputStream("glimmer/T2/Code/data.txt");) {
            int flag=0,a=0,sum=0,tot=0;
            StringBuilder s = new StringBuilder();
            while((a=fis.read())!=-1){
                char c=(char)a;
                if (s.length() > 0) {//容易忽略多次换行
                    String line = s.toString();
                    int val = Integer.parseInt(line);
                    sum+=val;
                    tot++;
                    s.setLength(0); // 清空缓冲区，准备读取下一行
                }
                sum+=a-'0';
                tot++;
                flag=1;
            }
            switch(flag){
                case 1->System.out.printf("Sum: %lf", (double)sum/tot);
                case 0->System.out.printf("文件为空");
            }
        }catch (IOException e) {
            // IO异常：文件不存在 / 文件打开失败，进入这个catch
            System.out.println("文件读取失败");
        } catch (NumberFormatException e) {
            // 捕获数字解析异常，防止程序崩溃
            System.out.println("输入不规范");
        } finally {
            // finally块：无论是否发生异常，代码一定会执行
            System.out.println("程序结束");
        }
    }
}
