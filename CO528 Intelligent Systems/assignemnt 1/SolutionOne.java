/**
 * @author Aleksej Bratkovskij
 */
public class SolutionOne
{
     double value = 0.0;

    public  void valueUp(){
        value += 0.001;
    }

    public  void valueDown(){
        value -=0.001;
    }

    public  double[] solver(){
        double[] vals = new double[20];
        for(int i=0; i<20;i++){
            vals[i] = value;
        }
        return vals;
    }
}