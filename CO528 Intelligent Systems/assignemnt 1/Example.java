
class Example {
    public static void main(String[] args){
        //Do not delete/alter the next line
        long startT=System.currentTimeMillis();
        String name="Aleksej Bratkovskij";
        String login = "ab2323";

        double fitness=9001;
        double startVal1 = 0;
        double startVal2 = 0;
        double[] utility = new double[100];
        double[] weight = new double[100];
        double[] resultsOfSolution1;
        double[] solutionOne = new double[20];

        boolean goDown =false;
        boolean[] resultsOfSolution2;

        int iter = 0;

        SolutionOne pso = new SolutionOne();
        SolutionTwo pst = new SolutionTwo();

        while(fitness>0){
            if(!goDown){
                pso.valueUp();
                startVal1=Assess.getTest1(pso.solver());
                pso.valueDown();
                pso.valueDown();
                startVal2=Assess.getTest1(pso.solver());
                goDown = true;
            }
            else{
                solutionOne = pso.solver();
                fitness=Assess.getTest1(solutionOne);
                if(startVal1<startVal2){
                    pso.valueUp();
                }
                else{
                    pso.valueDown();
                }
            }
        }
        resultsOfSolution1=solutionOne;
        System.out.println("Solution 1: " + (Assess.getTest1(resultsOfSolution1)));

        while(iter<100){
            resultsOfSolution2 = pst.inquire(iter);
            double[] tmp =(Assess.getTest2(resultsOfSolution2));
            utility[iter] = tmp[1];
            weight[iter] =tmp[0];
            iter++;
        }
        pst.assignUtilAndWeight(utility, weight);
        boolean[][] wip = pst.knapsack();
        resultsOfSolution2 = pst.convertToSingleArray(wip);


        //Once completed, your code must submit the results you generated, including your name and login: 
        //Use and adapt  the function below:
        Assess.checkIn(name,login,resultsOfSolution1,resultsOfSolution2);

        //Do not delete or alter the next 2 lines
        long endT= System.currentTimeMillis();
        System.out.println("Total execution time was: " +  ((endT - startT)/1000.0) + " seconds");

    }
}
