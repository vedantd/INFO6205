import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from scipy.optimize import curve_fit

# Load the CSV data


def load_data(file_path):
    return pd.read_csv(file_path)

# Define a function to model and create a fit


def model_func(x, a, b):
    return a * np.sqrt(x) + b

# Perform the curve fit


def fit_curve(data):
    params, params_covariance = curve_fit(
        model_func, data['Steps'], data['MeanDistance'])
    return params

# Plot the data along with the fitted line


def plot_data_and_fit(data, params):
    plt.figure(figsize=(10, 6))
    plt.scatter(data['Steps'], data['MeanDistance'], label='Data', alpha=0.5)
    plt.plot(data['Steps'], model_func(data['Steps'], params[0],
             params[1]), label='Fitted function', color='red')
    plt.xlabel('Number of Steps (m)')
    plt.ylabel('Mean Distance (d)')
    plt.title('Fitted Curve: Mean Distance vs. Number of Steps')
    plt.legend()
    plt.grid(True)
    plt.show()

# Main function to run the analysis


def main():
    file_path = 'results.csv'  # Replace with your file path
    data = load_data(file_path)
    params = fit_curve(data)
    # plot_data_and_fit(data, params)

    # Print the fitted parameters
    print("Fitted parameters:", params)

    # Print the mathematical relationship
    print(
        f"The fitted mathematical relationship is: d = {params[0]:.4f} * sqrt(m) + {params[1]:.4f}")


if __name__ == "__main__":
    main()
