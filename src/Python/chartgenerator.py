import streamlit as st
import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt

# Carregar dados
df = pd.read_csv('../../results.csv', names=['Algoritmo', 'Tamanho', 'Tempo'])

st.title("Análise de Desempenho dos Algoritmos de Ordenação")
st.write("Este aplicativo compara o desempenho de diferentes algoritmos de ordenação com base no tempo de execução.")

# Filtro por algoritmo
algoritmos = df['Algoritmo'].unique()
algoritmos_selecionados = st.multiselect("Escolha os algoritmos para comparar:", algoritmos, default=algoritmos)

# Filtrar dados
df_filtrado = df[df['Algoritmo'].isin(algoritmos_selecionados)]

# Gráfico de desempenho
fig, ax = plt.subplots(figsize=(10, 6))
sns.lineplot(data=df_filtrado, x='Tamanho', y='Tempo', hue='Algoritmo', marker='o', ax=ax)
plt.title("Tempo de Execução por Algoritmo")
plt.xlabel("Tamanho do Array")
plt.ylabel("Tempo (ms)")
plt.grid(True)

st.pyplot(fig)
