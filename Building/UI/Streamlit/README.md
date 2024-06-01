## Streamlit 
 Streamlit is an open-source Python library.
- It enables the creation of interactive, web-based data applications with minimal effort.
- No need for frontend experience (HTML, CSS, JavaScript).

## Key Features  
- **Simplicity:** Write Python scripts, and Streamlit turns them into web apps.
- **Interactivity:** Supports widgets (e.g., sliders, buttons) for interactive interfaces.
- **Flexibility:** Integrate with popular Python libraries like Pandas, NumPy, Matplotlib, Plotly.
- **Real-time updates:** Automatic updates when the script changes.

## Use Cases  
- **Data Exploration:** Visualize and interact with datasets.
- **Machine Learning:** Build and share ML models with interactive interfaces.
- **Prototyping:** Quickly prototype and share data applications.
- **Dashboards:** Create interactive dashboards for data analysis.

## Basic Commands  
- `st.write()`: Display text, data, and Matplotlib figures.
- `st.title()`: Display a title.
- `st.header()`: Display a header.
- `st.subheader()`: Display a subheader.
- `st.text()`: Display fixed-width text.
- `st.markdown()`: Display text formatted as Markdown.

##  Displaying Data with Streamlit
Displaying Data  
- `st.dataframe()`: Display a dataframe as an interactive table.
- `st.table()`: Display a static table.
- `st.json()`: Display JSON objects.
- `st.metric()`: Display a metric with a label, value, and delta.

## Adding Interactivity
- `st.button()`: Add a button.
- `st.checkbox()`: Add a checkbox.
- `st.radio()`: Add radio buttons.
- `st.selectbox()`: Add a dropdown select box.
- `st.slider()`: Add a slider for numeric values.
- `st.text_input()`: Add a text input box.

## Displaying Media  
- `st.image()`: Display images.
- `st.audio()`: Play audio files.
- `st.video()`: Play video files.

Simple App  
```python
import streamlit as st

st.title("Simple Streamlit App")
st.header("Welcome to Streamlit!")
st.write("This is an example of a basic Streamlit app.")
name = st.text_input("Enter your name:")
if st.button("Submit"):
    st.write(f"Hello, {name}!")
```
