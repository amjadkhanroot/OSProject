package std.group.os;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.concurrent.ExecutorService;
        import java.util.concurrent.Executors;
        import java.util.concurrent.TimeUnit;
        import java.util.logging.Logger;

/**
 * @author Amjad Khan
 * @create 3/19/2019 - 6:37 PM
 * @project OSProject
 */

public class Main {

    static Matrix mat = new Matrix();
    static public long[][] result;
    static public long[][] a;
    static public long[][] b;
    /* Matrix Size e.g 1000 by 1000. */
    static int MAT_SIZE = 3;
    /* Threads Number. */
    static int THEARDS_NUM = 3;
    /* Number of Task e.g 1 cols X 1 row. */
    static int TASK_NUM = (MAT_SIZE/THEARDS_NUM);
    /* To Keep im which is the Rows in a specific range.*/
    static public int cm = TASK_NUM;
    /* To Define the range of the rows in Matrix Class*/
    static public int im = 0;


    public static void main(String[] args) throws Exception {

        mat.setSize(MAT_SIZE);
        mat.setA(a = mat.loadMatrix());
        mat.setB(b = mat.loadMatrix());
        mat.setResult(result = new long[a.length][b.length]);


        List<Runnable> run = new ArrayList<>();
        ExecutorService pool = Executors.newFixedThreadPool(THEARDS_NUM);
        long t1 = System.currentTimeMillis();

//        result = mat.multiplyMatrix(a,b);


        for(int x = 0; x < THEARDS_NUM; x++){

            run.add(new Matrix(MAT_SIZE,cm,im,a,b,result));
            pool.execute(run.get(x));

                    cm = cm + TASK_NUM;
                    im = im + TASK_NUM;

        }

        /**
         * Shuts down the thread pool
         */
        pool.shutdown();

        if(pool.isShutdown()) {

            long t2 = System.currentTimeMillis();
            //mat.setResult(result);
            mat.storeMatrix(mat.getResult(), "xx2.txt");
            mat.displayMatrix(mat.getResult());
            long totalTime = t2-t1;
            System.out.println("-------------------- the time is " + totalTime + " ms ---------------------");

        }



    }




}