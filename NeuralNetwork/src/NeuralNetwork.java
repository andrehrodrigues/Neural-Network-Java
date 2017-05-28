import java.util.ArrayList;

/**
 * Created by Andre on 27/05/2017.
 */
public class NeuralNetwork {

    private double momentum;
    private double learningFactor;
    private int crossValidation;
    private int outputLayerSize;
    private double[] netError;
    private InputData inputData;
    private ArrayList<NeuronLayer> layers;

    NeuralNetwork(double[][] inputData, int numberOutputNeurons, double momentum, double learningFactor){
        this.inputData = new InputData(numberOutputNeurons, inputData);
        this.momentum = momentum;
        this.learningFactor = learningFactor;
        this.outputLayerSize = numberOutputNeurons;
        this.netError = new double[numberOutputNeurons];
        layers.add(new NeuronLayer(numberOutputNeurons));
    }

    public void trainNetwork(int epochs){
        while (!(epochs > 150)) {

            double[] netError = new double[outputLayerSize];

            calculateLayerOutput(this.inputData.trainingData, layers.get(0));
            for(int a = 1; a < layers.size(); a++){
                calculateLayerOutput(layers.get( a-1 ).getCalculatedOutput(), layers.get(a));
            }

            calculateNetworkError(batchSize);

            calculateLayersGradient(batchSize);












        }//End of while that repeats the network calculating

    }

    public void calculateLayerOutput(double[][] inputData, NeuronLayer layer){
        layer.getLayerOutput(inputData);
    }


    public void calculateLayersGradient(int batchSize){

        NeuronLayer outputLayer = this.layers.get( this.layers.size() - 1 );
        double[] outputGradients = new double[outputLayerSize];
        for (int a = 0; a < outputLayerSize ; a++) {
            for (int b = 0; b < batchSize ; b++) {
                outputGradients[a] += gradient(outputLayer.getCalculatedOutput()[b][a], this.netError[a]);
            }
            outputGradients[a] = outputGradients[a] / batchSize;
        }

        outputLayer.setGradients(outputGradients);

        double[] hiddenLayerGradients = new double[hiddenLayerSize + 1];
        for (int a = 0; a <  batchSize; a++) {
            for (int b = 0; b < hiddenLayerSize + 1; b++) {
                double sumErrors = 0.0;
                for (int c = 0; c < outputLayerSize; c++) {
                    sumErrors += outputGradients[c]
                            * outputLayer.getWeights()[c][b];
                }
                hiddenLayerGradients[b] += gradient(resultLayer1[a][b],
                        sumErrors);
            }
        }

        for (int b = 0; b < hiddenLayerSize + 1; b++) {
            hiddenLayerGradients[b] = hiddenLayerGradients[b] / (input.getTrainingDataSize() / batchSize);
        }

    }

    public void updateLayerWeights(int batchSize, NeuronLayer layer){

        // Calculate the new weights for the output layer
        for (int a = 0; a < layer.getSize(); a++) {
            for (int b = 0; b < layer.getPreviousLayerSize() + 1; b++) {
                double error = 0.0;
                for (int c = 0; c < batchSize ; c++) {
                    error += layer.getGradients()[a] * layer.getCalculatedOutput()[c][a];
                }
                error = (error / batchSize) * this.learningFactor;// (calculatedOutput / numInputs) *
                // learningFactor;
                layer.getWeights()[a][b] = updateWeight(
                        layer.getWeights()[a][b],
                        (layer.getPreviousWeights() == null ? layer.getWeights()[a][b] : layer.getPreviousWeights()[a][b]),
                        momentum, error);
            }
        }

    }

    public static double updateWeight(double oldWeight, double olderWeight, double momentum, double error) {
        return oldWeight + (momentum * olderWeight) + error;
    }

    public void calculateNetworkError(int batchSize){
        for (int a = 0; a < this.netError.length; a++) {
            for (int b = 0; b < batchSize; b++) {
                this.netError[a] += (a == this.inputData.getExpectedTrainingOutput()[b] ? 1.0 : 0.0) - layers.get(layers.size()-1).calculatedOutput.get()[b][a];
            }
            this.netError[a] = this.netError[a] / batchSize;
        }
    }

    public static double gradient(double output, double netError) {
        return (output * (1 - output) * netError);
    }

    public void addLayer(int layerSize, int dimensions){
        layers.add((layers.size() - 1),new NeuronLayer(layerSize, dimensions));
    }

    public void addLayer(int layerSize, int dimensions, int layerLevel){
        layers.add(layerLevel,new NeuronLayer(layerSize, dimensions));
    }
}
