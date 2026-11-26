# Water Requirement Prediction using WEKA

## Project title
Predicting Water Requirements Based on Crop Types, Weather Conditions and Soil Properties

## What this project does
This academic mini-project demonstrates a regression workflow using WEKA. It uses crop, weather and soil attributes to predict a numeric target: `Water_Requirement_mm_day`.

## Dataset disclosure
The included CSV is **synthetic / artificially generated** for demonstrating the software workflow. It is not measured farm data and must not be used for real irrigation decisions. Replace it with validated field observations if real-world accuracy is required.

## Project contents
- `data/`: WEKA-ready ARFF dataset (`agricultural_water_requirement_dataset.arff`) and CSV copy
- `src/`: Java source using the WEKA API
- `screenshots/`: add genuine screenshots from your own WEKA execution here
- `README.md`: setup and run instructions

## Requirements
- Java JDK compatible with your installed WEKA version
- WEKA installed
- `weka.jar` from the WEKA installation
- Windows 10/11, Linux or macOS

Official WEKA site: https://weka.sourceforge.io/

## Run in WEKA Explorer (GUI)
1. Open WEKA GUI Chooser and select **Explorer**.
2. In **Preprocess**, click **Open file** and select `data/agricultural_water_requirement_dataset.arff` (recommended) or the CSV copy.
3. Select the `Record_ID` attribute and apply the **Remove** filter.
4. Set `Water_Requirement_mm_day` as the class attribute.
5. Open **Classify**.
6. Choose `trees` → `RandomForest` (or another regression model).
7. Select **Cross-validation**, set folds to `10`, and click **Start**.
8. Save genuine screenshots of the Preprocess screen and Classify output in `screenshots/`.

## Run the Java source (Windows Command Prompt)
1. Find the actual location of `weka.jar` on your computer.
2. Open Command Prompt in the project root folder.
3. Compile (replace the sample path with the actual jar path):
```bat
javac -cp "C:\Path\To\weka.jar" -d out src\WaterRequirementPrediction.java
```
4. Run:
```bat
java -cp "C:\Path\To\weka.jar;out" WaterRequirementPrediction data\agricultural_water_requirement_synthetic_dataset.csv
```
Do not assume the sample jar path is correct; locate your installed `weka.jar` first.

## Evaluation
For regression, record correlation coefficient, MAE, RMSE and relative absolute error (RAE) from the WEKA output. Do not report classification accuracy for this numeric target.

## Academic integrity
Any screenshots previously described as interface mockups are illustrative only. Replace them with screenshots captured from your own WEKA session. Fill the report's result table only with values from your actual run. Do not upload `weka.jar` unless its distribution terms permit it; link to the official WEKA download instead.
