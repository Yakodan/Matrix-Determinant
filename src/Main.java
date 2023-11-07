import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Основной класс программы
 * @author Кодинцев Даниил
 */
public class Main {
    public static void main(String[] args) {
        try {
            // Создаём матрицу 12х12
            Matrix matrix = new Matrix(new Long[][] {
                    {-6L, -1L, -3L, 20L, 9L, 2L},
                    {-3L, -11L, -18L, 5L, 13L, -14L},
                    {-19L, 5L, 20L, -10L, -3L, -2L},
                    {2L, -1L, -3L, -9L, -18L, -10L},
                    {12L, 15L, 1L, -1L, -2L, -14L},
                    {18L, 15L, 15L, 12L, -9L, -2L}
            });
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Future<Long> future = executor.submit(new Determinant(matrix));
            System.out.println(future.get());
            executor.shutdown();
        } catch (NonSquareMatrixException | InterruptedException | ExecutionException e) {
            System.out.println(e.getCause());
        }
    }
}