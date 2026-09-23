package glimmer.T5;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
public class Copy {
    public static void main(String[] args) {
        try(FileInputStream fis = new FileInputStream("src/glimmer/T5/doro.jpg")){
            FileOutputStream fos=new FileOutputStream("src/glimmer/T5/Copy_doro.jpg");
            byte[] buffer = new byte[1024];
            int len;
            while((len=fis.read(buffer))!=-1){
                fos.write(buffer,0,len);
            }
            fos.close();//如果出现异常，此行不会执行，但是try会关闭大括号里的所有流
        }catch(IOException e){
            e.printStackTrace();
        }
    }//try不用closse
}
