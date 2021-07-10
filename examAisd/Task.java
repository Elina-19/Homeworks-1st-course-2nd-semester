package examAisd;

import java.util.Scanner;

public class Task {

    public static void main(String[] args){

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int k = scan.nextInt();
        int l = scan.nextInt();
        int r = scan.nextInt();
        int i = -1;
        int j = -1;
        boolean flag = true;
        boolean flag1 = true;
        int[] a = new int[n];
        for (int m = 0; m < n; m++){
            a[m] = scan.nextInt();
        }
        for (int d = 0; d < n && flag; d++){
            flag1 = true;
            int s = 0;
            if (d-k > 0){
                s = d - k;
            }
            for (int e = s; e < n && flag1; e++){
                if ((1 <= Math.abs(d-e)) && (Math.abs(d-e) <= k)){
                    if ((l <= Math.abs(a[d]-a[e])) && (Math.abs(a[d]-a[e]) <= r)) {
                        i = d + 1;
                        j = e + 1;
                        flag = false;
                        flag1 = false;
                    }
                }
                else {
                    if (e > d) {
                        flag1 = false;
                    }
                }
            }
        }
        System.out.print(i + " " + j);
    }
}
