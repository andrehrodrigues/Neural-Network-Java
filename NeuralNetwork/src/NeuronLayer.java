import jsc.distributions.Uniform;

import java.util.Random;

/**
 * Created by Andre on 13/05/2017.
 */
public class NeuronLayer {

    int size;
    int previousLayerSize;
    double[] gradients;
    Matrix weights;
    Matrix previousWeights;
    Matrix calculatedOutput;


    NeuronLayer(int size){
        this.size = size;
    }

    NeuronLayer(int size, int previousLayerSize){
        this.size = size;
        this.previousLayerSize = previousLayerSize;
        this.weights = new Matrix(size,previousLayerSize+1);

        setInitialWeights(size, previousLayerSize);
    }

    public void setInitialWeights(){
        Random r = new Random();
        for(int a = 0; a < size; a++){
            for (int b = 0; b < previousLayerSize+1; b++) {
                this.weights.get()[a][b] = r.nextDouble();
            }
        }
    }

    public void setInitialWeights(int numInputs, int numOutputs){
        double r =  4*( Math.sqrt( (6.0/(Double.valueOf(numInputs+numOutputs).doubleValue())) ) ) ;

        Uniform uni = new Uniform( -r, r );
        uni.setSeed(System.currentTimeMillis());

        for(int a = 0; a < size; a++){
            for (int b = 0; b < previousLayerSize+1; b++) {
                this.weights.get()[a][b] = uni.random();
            }
        }

    }

    public double[][] getLayerOutput(double[][] input){
        calculatedOutput.set(Matrix.multiply(input, Matrix.transposeMatrix(this.weights.get()), ActivationFunction.SIGMOID));
        return this.calculatedOutput.get();
    }

    public int getPreviousLayerSize() {
        return previousLayerSize;
    }

    public void setPreviousLayerSize(int previousLayerSize) {
        this.previousLayerSize = previousLayerSize;
    }

    public int getSize() {
        return size;
    }

    public double[][] getWeights() {
        return weights.get();
    }

    public void setWeights(double[][] weights) {
        this.weights.set(weights);
    }

    public void setSize(int size) {
        this.size = size;
    }

    public double[][] getPreviousWeights() {
        return previousWeights.get();
    }

    public void setPreviousWeights(double[][] previousWeights) {
        this.previousWeights.set(previousWeights);
    }

    public double[][] getCalculatedOutput() {
        return calculatedOutput.get();
    }

    public void setCalculatedOutput(double[][] calculatedOutput) {
        this.calculatedOutput.set(calculatedOutput);
    }

    public double[] getGradients() {
        return gradients;
    }

    public void setGradients(double[] gradients) {
        this.gradients = gradients;
    }
}
