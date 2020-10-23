/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrix;

import java.util.Arrays;
import java.util.LinkedList;

/**
 *
 * @author Sunshine
 */
public class Matrix {
    /*
    Write a function that accepts a square matrix (N x N 2D array) and returns the determinant of the matrix.

    How to take the determinant of a matrix -- it is simplest to start with the smallest cases:

        A 1x1 matrix |a| has determinant a.

        A 2x2 matrix [ [a, b], [c, d] ] or
        |a  b|
        |c  d|
        has determinant: a*d - b*c.

    The determinant of an n x n sized matrix is calculated by reducing the problem to the calculation of the determinants of n matrices ofn-1 x n-1 size.

    For the 3x3 case, [ [a, b, c], [d, e, f], [g, h, i] ] or
        |a b c|  
        |d e f|  
        |g h i|  
    the determinant is: a * det(a_minor) - b * det(b_minor) + c * det(c_minor) where det(a_minor) refers to taking the determinant of the 2x2 matrix created by crossing out the row and column in which the element a occurs:
        |- - -|
        |- e f|
        |- h i|  
    Note the alternation of signs.

    The determinant of larger matrices are calculated analogously, e.g. if M is a 4x4 matrix with first row [a, b, c, d], then:

    det(M) = a * det(a_minor) - b * det(b_minor) + c * det(c_minor) - d * det(d_minor)
    */
    public static int determinant(int[][] matrix) {
        int result = 0;
        int sign = -1;
        if (matrix.length == 1) {
            return matrix[0][0];
        }
        if (matrix.length == 2) {
            return matrix[0][0] * matrix[1][1] - (matrix[0][1] * matrix[1][0]);
        }

        int temp[][];
        temp = new int[matrix.length - 1][matrix[0].length - 1];
        LinkedList<Integer> tempList = new LinkedList();

        for (int k = 0; k < matrix.length; k++) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    if (i != 0 && j != k) {
                        tempList.add(matrix[i][j]);
                    }
                }
            }
            for (int i = 0; i < temp.length; i++) {
                for (int j = 0; j < temp.length; j++) {
                    temp[i][j] = tempList.remove();
                }
            }

            result += (-1) * (sign) * (matrix[0][k] * determinant(temp));
            sign = -sign;
            System.out.println(Arrays.deepToString(temp));
        }
        return result;
    }

}
