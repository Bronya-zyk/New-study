package glimmer.T5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {
    public static void main(String arr[]) {
        Student student = new Student(1, "doro", 2, "114514");
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/glimmer/T5/student.dat"))){
            oos.writeObject(student); // 直接写入整个对象
        }catch(IOException e){
            e.printStackTrace();
        }
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/glimmer/T5/student.dat"));){
            Student stu = (Student) ois.readObject();
            System.out.println(stu.toString());
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }

    }
}
