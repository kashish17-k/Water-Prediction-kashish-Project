import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.RandomForest;
import java.io.File;
import java.util.Random;

public class WaterRequirementPrediction {
    public static void main(String[] args) {
        try {
            String csv = args.length > 0 ? args[0]
                : "data/agricultural_water_requirement_dataset.arff";

            if (!new File(csv).exists()) {
                throw new IllegalArgumentException("CSV not found: " + csv);
            }

            // Load CSV using WEKA
            Instances data = DataSource.read(csv);
            if (data == null || data.numInstances() == 0) {
                throw new IllegalStateException("Dataset is empty.");
            }

            // Remove Record_ID (first attribute)
            Remove removeID = new Remove();
            removeID.setAttributeIndices("1");
            removeID.setInputFormat(data);
            data = Filter.useFilter(data, removeID);

            // Last column is the numeric target for regression
            data.setClassIndex(data.numAttributes() - 1);
            if (!data.classAttribute().isNumeric()) {
                throw new IllegalStateException("Target must be numeric.");
            }

            System.out.println("Instances: " + data.numInstances());
            System.out.println("Predicting: " + data.classAttribute().name());

            RandomForest model = new RandomForest();
            model.setNumIterations(100);

            Evaluation evaluation = new Evaluation(data);
            evaluation.crossValidateModel(model, data, 10, new Random(1));

            System.out.println(evaluation.toSummaryString(
                "=== 10-fold regression evaluation ===\n", false));

            // Train a final model and show one demonstration prediction
            model.buildClassifier(data);
            double prediction = model.classifyInstance(data.instance(0));
            System.out.printf("Demo prediction: %.3f mm/day%n", prediction);
            System.out.println("NOTE: synthetic data; not real irrigation advice.");
        } catch (Exception ex) {
            System.err.println("Execution error: " + ex.getMessage());
            ex.printStackTrace();
            System.exit(1);
        }
    }
}
