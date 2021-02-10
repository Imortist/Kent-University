/**
 * @author Aleksej Bratkovskij
 */
public class SolutionTwo
{
    static double[] util;
    static double[] weight;

    public  void assignUtilAndWeight(double[] util, double[] weight){
        SolutionTwo.util = util;
        SolutionTwo.weight = weight;
    }

    public  boolean[] inquire(int num){
        boolean[] vals = new boolean[100];
        for(int i=0;i<100;i++){
            vals[i]=false;
        }
        vals[num] = true;
        return vals;
    }

    public  boolean[][] knapsack(){
        double[] utility = util;
        double[] weigh = weight;
        int num = 100;
        int cap = 500;
        int i,j;
        double[][] doubles = new double[num+1][cap+1];
        boolean[][] truthTable = new boolean[num][cap+1];

        for(i=1; i<=num;i++) {
            for (j = 1; j <= cap; j++) {
                if (weigh[i - 1] > j) doubles[i][j] = doubles[i - 1][j];
                else {
                    double pi = utility[i - 1] + doubles[i - 1][(int) (j - weigh[i - 1])];
                    double pj = doubles[i - 1][j];
                    if (pi > pj) {
                        truthTable[i - 1][j] = true;
                        doubles[i][j] = pi;
                    } else {
                        doubles[i][j] = pj;
                    }
                }
            }
        }
        return truthTable;
    }

    public  boolean[] convertToSingleArray(boolean[][] solution){
        int temp = 0;
        boolean[] table = new boolean[100];
        double[] weigh = weight;
        int cap = 500;
        int vals = 100;

        for(int i= vals-1;i>=0;i--){
            if(solution[i][cap]){
                temp +=weigh[i];
                table[i]=true;
                cap = (int) (cap-weigh[i]);
            }
        }
        return table;
    }

}
