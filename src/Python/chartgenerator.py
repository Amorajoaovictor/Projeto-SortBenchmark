import streamlit as st
import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt

# Load data
data = pd.read_csv('../../results.csv', names=['Algorithm', 'Size', 'Time'])

st.title("Sorting Algorithms Performance Analysis")
st.write("This app compares the performance of different sorting algorithms based on execution time.")

# Filter by algorithm
algorithms = data['Algorithm'].unique()
selected_algorithms = st.multiselect("Select algorithms to compare:", algorithms, default=algorithms)

# Filter data
filtered_data = data[data['Algorithm'].isin(selected_algorithms)]

# Performance chart
fig, ax = plt.subplots(figsize=(10, 6))
sns.lineplot(data=filtered_data, x='Size', y='Time', hue='Algorithm', marker='o', ax=ax)
plt.title("Execution Time by Algorithm")
plt.xlabel("Array Size")
plt.ylabel("Time (ms)")
plt.grid(True)
st.pyplot(fig)
