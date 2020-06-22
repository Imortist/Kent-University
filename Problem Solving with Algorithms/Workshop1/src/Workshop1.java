import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Workshop1 {

    public static int first(int[] ints){
        int largest = 0;
        for(int i : ints){
            if(i > largest) largest = i;
        }
        return largest;
    }

    public static int third(ArrayList<Integer> ints){
        do{
            for(int i=0; i<ints.size()-1; i++){
                if(ints.get(i) < ints.get(i+1)){
                    int hold = ints.get(i+1);
                    ints.set(i+1,ints.get(i));
                    ints.set(i, hold);
                }
            }
        }while(ints.get(2) == 0);
        return ints.get(2);
    }

    public static void reverse() {
        Scanner s = new Scanner(System.in);
        int i = 0;
        int[] an_array = new int[100];
        while(s.hasNextInt()) {
            an_array[i] = s.nextInt();
            i++;
        }
        while(i >=0){
            System.out.println(an_array[i]);
            i--;
        }
    }



    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

    }

    public static int babylonianInt(int n){
        float a = 2.0f;
        while(Math.abs(a-n/a) >= 0.1){
            a = (a+n/a)/2;
        }
        return (int)a;
    }

    public static int babylonianFloat(float n){
        float a = 2.0f;
        while(Math.abs(a-n/a) >= 0.1){
            a = (a+n/a)/2;
        }
        return (int)a;
    }

    public static int babylonianFloat(float n, float acc){
        float a = 2.0f;
        while(Math.abs(a-n/a) >=0.1){
            a = (a+n/a)/2;
        }

        return (int) a;
    }

}
