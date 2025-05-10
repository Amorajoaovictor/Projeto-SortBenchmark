# SortBenchmark

## Application Overview

SortBenchmark is a benchmarking tool designed to evaluate the performance of various sorting algorithms. It generates datasets using a Java-based utility, processes the data, and visualizes the results through an interactive Python-based Streamlit application. The tool is ideal for analyzing sorting efficiency and comparing algorithm performance across different dataset sizes and configurations.


## How to Run the Code

### 1. Clone the Repository  
Clone the project to your local machine using the following command:

```bash
git clone https://github.com/your-repo/Projeto-SortBenchmark.git
```

### 2. Navigate to the Project Directory  
Move into the project folder:

```bash
cd Projeto-SortBenchmark
```

### 3. Install Dependencies  
Ensure all required dependencies are installed. Make sure to have Java 17 or above and Python 3 installed on your computer:

```bash
pip install -r src/python/requirements.txt
```

### 4. Compile and Run the Java Code  

#### a. Navigate to the Java Source Directory  
Move to the directory containing the Java files:

```bash
cd Projeto-SortBenchmark/Projeto-SortBenchmark/src/main/java/utils
```

#### b. Compile the Java File  
Compile the `DatasetGenerator.java` file using the following command:

```bash
javac DatasetGenerator.java
```

#### c. Execute the Java Program  
Run the compiled Java program:

```bash
java main.java.utils.DatasetGenerator
```

### 5. Visualize Results  
To visualize the benchmark results with charts, use the Streamlit framework:

#### a. Navigate to the Python Source Directory  
Move to the directory containing the Python scripts:

```bash
cd src/python/
```

#### b. Run the Streamlit Application  
Execute the following command to launch the Streamlit application:

```bash
streamlit run ./chartgenerator.py
```

