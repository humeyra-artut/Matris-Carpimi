
import java.util.Random;

public class matrisCarpimi {
    static final int N = 5;
    static int[][] A = new int[N][N];
    static int[][] B = new int[N][N];
    static int[][] C = new int[N][N];

    public static void main(String[] args) {
        Random random = new Random();

        // Rastgele matrisleri oluştur
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                A[i][j] = random.nextInt(10); // 0-9 arası rastgele sayılar
                B[i][j] = random.nextInt(10); // 0-9 arası rastgele sayılar
            }
        }

        System.out.println("Matrix A:");
        printMatrix(A);
        System.out.println("Matrix B:");
        printMatrix(B);

        Thread[] threads = new Thread[N];

        for (int i = 0; i < N; i++) {
            final int row = i;
            threads[i] = new Thread(() -> calculateRow(row));
            threads[i].start();
        }

        for (int i = 0; i < N; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Matrix C:");
        printMatrix(C);
    }

    static void calculateRow(int row) {
        for (int col = 0; col < N; col++) {
            int sum = 0;
            for (int k = 0; k < N; k++) {
                sum += A[row][k] * B[k][col];
            }
            C[row][col] = sum;
        }
    }

    static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}
