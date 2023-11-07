import java.util.concurrent.*;

/**
 * Класс, реализующий интерфейс {@link Callable}<br>
 * Его основной метод {@link #call()} реализован как рекурсивный вызов <br>
 * потоков, вычисляющих определитель матрицы и её миноров
 */
public class Determinant implements Callable<Long>{

    private Matrix matrix;

    /**
     * Конструктор класса {@link Determinant}
     * @param matrix матрица, определитель которой будет вычислен
     */
    public Determinant(Matrix matrix){
        this.matrix = matrix;
    }

    /**
     * Метод, определяющий знак минора по первой строке и i-ому столбцу
     * @param i номер столбца, по которому берётся минор
     * @return 1, если номер столбца чётный, и -1, если нечётный
     */
    private int minorPosNeg(int i){
        if(i%2!=0){
            return (-1);
        }
        else{
            return 1;
        }
    }

    /**
     * Основной метод, рекурсивно вызывающий потоки, ищущие миноры исходной матрицы.
     * Таким образом вычисляется определитель всей исходной матрицы
     * @return Определитель матрицы
     * @throws NonSquareMatrixException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Override
    public Long call() throws NonSquareMatrixException, ExecutionException, InterruptedException {

        // Если минор - единственный элемент, то прекращаем рекурсию и возвращаем этот элемент
        if(matrix.getMatrix().length == 1){
            return matrix.getMatrix()[0][0];
        }

        // Если порядок матрицы больше одного, то продолжаем (запускаем) рекурсию
        else{
            ExecutorService executorService = Executors.newFixedThreadPool(matrix.getMatrix().length);
            Future<Long>[] task = new Future[matrix.getMatrix().length];

            Long result = 0L;
            for(int i = 0;i<matrix.getMatrix().length;i++){
                Matrix newMatrix = matrix.minor(i);
                task[i] = executorService.submit(new Determinant(newMatrix));
            }

            for(int i = 0; i<matrix.getMatrix().length;i++){
                result += task[i].get()*minorPosNeg(i)*matrix.getMatrix()[i][0];
            }

            executorService.shutdown();

            return result;
        }
    }
}
