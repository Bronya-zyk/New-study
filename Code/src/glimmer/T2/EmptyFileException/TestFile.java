package glimmer.T2.EmptyFileException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
public class TestFile {    // 声明可能抛出 EmptyFileException
    public static void readFile(String path) throws EmptyFileException, IOException {
        File file = new File(path);
        FileInputStream fis = new FileInputStream(file);
        if(!file.exists()){
            fis.close();
            throw new EmptyFileException("文件不存在");
        }
        // 判断文件长度为0
        if(file.length() == 0){
            fis.close();
            // 抛出自定义异常
            throw new EmptyFileException("文件为空！路径：" + path);
        }
        System.out.println("文件读取成功");
        fis.close();
    }
}