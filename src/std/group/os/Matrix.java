package std.group.os;

import java.io.FileOutputStream;
import java.io.PrintWriter;


/**
 * @author Amjad Khan
 * @create 3/19/2019 - 6:37 PM
 * @project OSProject
 */
public class Matrix implements Runnable {

    /*Tuning Thread Pool

    The optimum size of the thread pool depends on the number of processors
    available and the nature of the tasks.On a N processor system for a queue
    of only computation type processes, a maximum thread pool size of N or N+1
    will achieve the maximum efficiency.But tasks may wait for I/O and in such
    a case we take into account the ratio of waiting time(W) and service time(S)
    for a request; resulting in a maximum pool size of N*(1+ W/S) for maximum efficiency.*/



    private int size;
    private long[][] result;
    private long[][] a;
    private long[][] b;
    private int i = 0;
    private int c = 1;

    public Matrix() {

    }

    public Matrix(int size, int c, int i, long[][] a , long[][] b, long[][] result) {
        this.size = size;
        this.c = c;
        this.i = i;
        this.result = result;
        this.a = a;
        this.b = b;
    }

    /*
     * Setter & Getter
     * */
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long[][] getResult() {
        return result;
    }

    public void setResult(long[][] result) {
        this.result = result;
    }

    public long[][] getA() {
        return a;
    }

    public void setA(long[][] a) {
        this.a = a;
    }

    public long[][] getB() {
        return b;
    }

    public void setB(long[][] b) {
        this.b = b;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    /*
     * Generate a Random Matrix
     * */
    public long[][] loadMatrix(){

        long mat[][] = new long[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                mat[i][j] = (int) (Math.random()*100);
            }

        }
        return mat;
    }



    /*
     * Store The new Matrix
     * */
    public void storeMatrix(long rst[][], String filename) throws Exception {
        int rows = rst.length;
        int cols = rst[0].length;
        PrintWriter pw = new PrintWriter(new FileOutputStream(filename));
        pw.println("rows   " + rows + "      columns     " + cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                pw.print(rst[i][j] + "    ");

            }
            pw.println();
        }
        pw.close();
    }

    /*
     * Display The Matrix
     * */
    public void displayMatrix(long rst[][]){
        int rows = rst.length;
        int cols = rst[0].length;
        //System.out.println("rows   " + rows + "      columns     " + cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(rst[i][j] + "    ");

            }
            System.out.println();
        }


    }


    /*
     * Multiply Matrix
     * */
    public long[][] multiplyMatrix(long[][] a, long[][] b){

        //long [][] result = new long[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                result[i][j] = 0;
                for(int k = 0; k < size; k++)
                    result[i][j] += a[i][k]*b[k][j];
            }
        }

        return  result;

    }


    @Override
    public void run() {

        for(int i = this.i ; i < c; i++){
            for(int j = 0 ; j < size; j++) {
                for (int k = 0; k < size; k++)
                    this.result[i][j] += a[i][k] * b[k][j];
            }
        }

        //displayMatrix(result);
    }
}