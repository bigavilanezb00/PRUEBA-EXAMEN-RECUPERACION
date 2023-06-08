package MaximComuDivisor;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class MaximComuDivisor {
    public static void main(String[] args) {
        int numero1 = 51;
        int numero2 = 89;

        int mcd = calcularMCD(numero1, numero2);
        System.out.println("El MCD de " + numero1 + " i " + numero2 + " és: " + mcd);
    }

    private static int calcularMCD(int a, int b) {
        ForkJoinPool pool = new ForkJoinPool();
        return pool.invoke(new MCDTask(a, b));
    }
}

class MCDTask extends RecursiveTask<Integer> {
    private int a;
    private int b;

    public MCDTask(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    protected Integer compute() {
        if (b == 0) {
            return a;
        } else {
            return new MCDTask(b, a % b).compute();
        }
    }
}

