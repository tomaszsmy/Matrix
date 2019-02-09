package Projekt;

import java.lang.Math;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Matrix {
	public double[][] matrix;
	public int rows;
	public int columns;
	Matrix result;

	public Matrix(int rows, int columns) {

		this.rows = rows;
		this.columns = columns;
		matrix = new double[rows][columns];
	}

	public void setvalue(int i, int j, double value) {
		matrix[i][j] = value;
	}

	public double getvalue(int i, int j) {
		return matrix[i][j];
	}

	public Matrix adding(Matrix x) {
		result = new Matrix(rows, columns);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				this.result.matrix[i][j] = this.matrix[i][j] + x.matrix[i][j];
			}

		}

		return result;
	}

	public Matrix subtraction(Matrix x) {

		result = new Matrix(rows, columns);

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				this.result.matrix[i][j] = this.matrix[i][j] - x.matrix[i][j];
			}

		}

		return result;
	}

	public Matrix transposition() {

		result = new Matrix(columns, rows);

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				result.matrix[j][i] = this.matrix[i][j];
			}
		}

		return result;

	}

	public Matrix multiplication(Matrix x) {

		result = new Matrix(columns, x.rows);
		for (int i = 0; i < columns; i++) {
			for (int j = 0; j < x.rows; j++) {

				for (int k = 0; k < rows; k++) {

					result.matrix[i][j] += this.matrix[k][i] * x.matrix[j][k];
				}

			}
		}

		return result.transposition();
	}

	public Matrix multiplicationByValue(Matrix x) {
		result = new Matrix(rows, columns);

		for (int i = 0; i < rows; i++) {

			for (int j = 0; j < columns; j++) {

				result.matrix[i][j] = this.matrix[i][j] * x.matrix[0][0];
			}

		}

		return result;

	}

	public double det(double a[][], int n) {
		double det = 0;
		int x, y;
		double[][] temp = new double[n][n];

		if (n == 1) {
			return a[0][0];
		} else if (n == 2) {
			det = (a[0][0] * a[1][1] - a[0][1] * a[1][0]);
			return det;
		} else {
			for (int k = 0; k < n; k++) {
				x = 0;
				y = 0;
				for (int i = 1; i < n; i++) {
					for (int j = 0; j < n; j++) {
						if (j == k) {
							continue;
						}
						temp[x][y] = a[i][j];
						y++;
						if (y == n - 1) {
							x++;
							y = 0;
						}
					}
				}
				det = (det + a[0][k] * Math.pow(-1, k) * det(temp, n - 1));

			}
			return det;
		}
	}

	public double[][] deleteVector(Matrix m) {
		double[][] newMatrix = new double[m.rows][m.columns - 1];

		for (int i = 0; i < m.rows; i++) {
			for (int j = 0; j < m.columns - 1; j++) {
				newMatrix[i][j] = m.matrix[i][j];
			}
		}

		return newMatrix;
	}

	private static double round(double value, int places) {
		if (places < 0) {
			throw new IllegalArgumentException();
		}

		BigDecimal bd = new BigDecimal(Double.toString(value));
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	public double[][] changeEquation(double[][] newEquation, int w, int k, double[] vectors, int wyz) { 
		double[][] nowy = new double[w][k];
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < k; j++) {
				nowy[i][j] = newEquation[i][j];
			}
		}

		for (int i = 0; i < w; i++) {
			for (int j = 0; j < k; j++) {
				if (j == wyz)
					nowy[i][wyz] = vectors[i];
			}
		}
		return nowy;
	}

	public String equations() {

		int rows = (int) this.rows - 1;
		int columns= rows + 1;

		double vectors[] = new double[rows];
		double dets[] = new double[rows];


		for (int i = 0; i < rows; i++) {
			vectors[i] = this.matrix[i][rows];
		}

		double[][] newEquation = deleteVector(this);
		double mainDet = det(newEquation, rows);

		for (int i = 0; i < rows; i++) {
			double[][] u;
			u = changeEquation(newEquation, rows, columns - 1, vectors, i);
			dets[i] = det(u, columns - 1);
		}

		String results = "";
		for (int i = 0; i < rows; i++) {
			double[] wynikiKoncowe = new double[rows];
			wynikiKoncowe[i] = ((double) dets[i]) / mainDet;

			results += "x" + (i + 1) + " = " + round(wynikiKoncowe[i], 2) + " " + " ";
		}

		return results + "det: " + Double.toString(round(mainDet, 2));
	}

}
