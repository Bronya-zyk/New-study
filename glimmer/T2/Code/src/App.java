
import java.io.FileInputStream;

public class App {
    public static void main(String[] args) throws Exception {
        int a=0,sum=0,tot=0;
        try(FileInputStream fis = new FileInputStream("微光glimmer/T2/Code/data.txt");) {
            int flag=0;
            while((a=fis.read())!=-1){
                if((char)a=='\n'||(char)a=='\r') continue;
                if((char)a<'0'||(char)a>'9'){
                    flag=2;
                    break;
                }
                sum+=a-'0';
                tot++;
                flag=1;
            }
            switch(flag){
                case 2->System.out.printf("输入不规范");
                case 1->System.out.printf("Sum: %lf", (double)sum/tot);
                case 0->System.out.printf("文件为空");
            }
        }catch(Exception e){
            System.out.println("文件读取失败");
        }finally{
            // fis.close();try会自动关闭
            System.out.println("程序结束");
        }
    }
}
