package com.Example;
import java.io.FileInputStream;
import java.io.IOException;
import com.Example.tool.EmptyFileException;
import com.Example.tool.TestFile;
public class App {
    public static void main(String[] args) throws Exception {
        try(FileInputStream fis = new FileInputStream("Code/data.txt");) {
            TestFile.readFile("Code/data.txt");
            int a=0,sum=0,tot=0;
            StringBuilder s = new StringBuilder();
            while((a=fis.read())!=-1){
                char c=(char)a;
                if (c=='\n'||c=='\r') {//容易忽略多次换行
                    if(s.length()==0) continue; // 忽略空行
                    String line = s.toString();
                    int val = Integer.parseInt(line);
                    sum+=val;
                    tot++;
                    s.setLength(0); // 清空缓冲区，准备读取下一行
                }
                else {
                    if(c<'0'||c>'9') {
                        throw new NumberFormatException("文件中包含非数字字符");
                    }
                    s.append(c);
                }
            }
            if(s.length()!=0) {
                int val = Integer.parseInt(s.toString());
                sum+=val;
                tot++;
                s.setLength(0); // 清空缓冲区，准备读取下一行
            }
            System.out.printf("Sum: %f", (double)sum/tot);
        } catch (EmptyFileException e) {
            System.out.println("捕获到空文件异常：" + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } catch(NumberFormatException e) {
            System.out.println("捕获到数字格式异常：" + e.getMessage());
        } catch (Exception e) {
            System.out.println("捕获到其他异常：" + e.getMessage());
        }finally {
            System.out.println("程序结束");
        }
    }
}
