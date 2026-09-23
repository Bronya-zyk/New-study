package glimmer.T5;
import java.util.Arrays;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class NameControl {
    public static void main(String[] args) {
        
        String s[] = new String[100];
        try {
            File source = new File("src/glimmer/T5/name.txt");
            //创建字节流
            FileInputStream fileInputStream = new FileInputStream(source);
            //创建转换流
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            //创建缓冲流
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        
            int tot = 0;
            String a;
            while((a = bufferedReader.readLine())!=null) {
                s[tot++]=a.trim();;
            }
            String[] realArr = Arrays.copyOf(s, tot);
            Arrays.sort(realArr);
            List<String> list=Arrays.asList(realArr);
            
            list.forEach(S->System.out.println(S));
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
