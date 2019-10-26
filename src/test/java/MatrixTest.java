import org.junit.*;
import Jama.*; 


public class MatrixTest {

    @Test
    public void testMultiplication() {
        System.out.println("Test multiplication");
        double[][] m1Array = {{1,2},{1,2}}; 
        Matrix m1 = new Matrix(m1Array);
        double[][] v1Array = {{1, 1}}; 
        Matrix v1 = new Matrix(v1Array);
        Matrix result = v1.times(m1);
        
        double[][] exp = {{2, 4}};
        Assert.assertArrayEquals(result.getArray(), exp);
    }
    
    @Test
    public void testTwoMatricesMultiplication() {
        System.out.println("Test two matrices multiplication");
        double[][] m1Array = {{1,2},{1,2}}; 
        Matrix m1 = new Matrix(m1Array);
        double[][] m2Array = {{1, 1}, {2, 2}, {3, 3}}; 
        Matrix m2 = new Matrix(m2Array);
        Matrix result = m2.times(m1);
        
        double[][] exp = {{2, 4}, {4, 8}, {6, 12}};
        Assert.assertArrayEquals(result.getArray(), exp);
    }
}
