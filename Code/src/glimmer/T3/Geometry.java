package glimmer.T3;

import java.util.Scanner;
abstract class getsc{
    abstract void gets();
    abstract void getc();
}
class Circle extends getsc{
    double R;
    public Circle(double R){
        this.R=R;
    }
    double s;
    double c;
    @Override
    void gets() {
        s=3.14*R*R;
        System.out.println("圆的面积为："+s);
    }

    @Override
    void getc() {
        c=2*3.14*R;
        System.out.println("圆的周长为："+c);
    }
}
class Rectangle extends getsc{
    double k4;
    double l4;
    public Rectangle(double k4,double l4){
        this.k4=k4;
        this.l4=l4;
    }
    double s;
    double c;
    @Override
    void gets() {
        s=k4*l4;
        System.out.println("矩形的面积为："+s);
    }

    @Override
    void getc() {
        c=2*(k4+l4);
        System.out.println("矩形的周长为："+c);
    }
}
class Triangle extends getsc{
    double s1,s2,s3;
    public Triangle(double s1,double s2,double s3){
        this.s1=s1;
        this.s2=s2;
        this.s3=s3;
    }
    double s;
    double c;
    @Override
    void gets() {
        s=Math.sqrt((s1+s2+s3)/2*((s1+s2+s3)/2-s1)*((s1+s2+s3)/2-s2)*((s1+s2+s3)/2-s3));
        System.out.println("正三角形的面积为："+s);
    }

    @Override
    void getc() {
        c=s1+s2+s3;
        System.out.println("正三角形的周长为："+c);
    }
}
public class Geometry {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double R=sc.nextDouble();
        double s1=sc.nextDouble();
        double s2=sc.nextDouble();
        double s3=sc.nextDouble();
        double l4=sc.nextDouble();
        double k4=sc.nextDouble();
        getsc[] S = {
                new Circle(R),
                new Triangle(s1,s2,s3),
                new Rectangle(k4,l4)
        };
        for (getsc getsc : S) {
            getsc.gets();
            getsc.getc();
        }
        sc.close();
    }
}
