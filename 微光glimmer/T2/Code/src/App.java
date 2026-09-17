public class App {
    public static void main(String[] args) throws Exception {
        int n=5;
        for(int i=1;i<=n;i++) {
            for(int j=1;j<=n;j++) {
                if((i+j==(n>>1|1)+1)||(i+j==(n>>1|1)+n)||Math.abs(i-j)==(n>>1|1)-1) 
                    System.out.print("*");
                else System.out.print(" ");
            }
            System.out.println();
        }
    }
}
