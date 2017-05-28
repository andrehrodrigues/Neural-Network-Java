/**
 * Created by Andre on 27/05/2017.
 */
public class ActivationFunction {

    public static final int SIGMOID = 0;

    public static double sigmoid(double x){
        return (1 / (1 + Math.exp(-x)));
    }
}
