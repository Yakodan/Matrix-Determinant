/**
 * Класс матрицы, хранящий в себе двумерный массив (матрицу)<br>
 * и предоставляющий удобные функции по его обработке
 * @author Кодинцев Даниил
 */
public class Matrix {

    /**
     * Матрица
     */
    private Long[][] matrix;

    /**
     * Конструктор объекта матрицы. Кидает исключение, если матрица не квадратная
     * @param matrix
     * @throws NonSquareMatrixException
     */
    public Matrix(Long[][] matrix) throws NonSquareMatrixException {
        if(matrix.length != matrix[0].length){
            throw new NonSquareMatrixException();
        }
        this.matrix = matrix;
    }

    public Long[][] getMatrix() {
        return matrix;
    }

    /**
     * Метод, возвращающий минор матрицы по первой строке и заданному столбцу
     * @param exceptRow столбец, который исключаем
     * @return Минор матрицы, который является объектом класса {@link Matrix}
     * @throws NonSquareMatrixException
     */
    public Matrix minor(int exceptRow) throws NonSquareMatrixException {
        int minorLength = matrix.length-1;
        Long [][] minor = new Long[minorLength][minorLength];

        for (int i = 0; i < exceptRow; i++) {
            System.arraycopy(matrix[i], 1, minor[i], 0, minorLength);
        }
        for (int i = exceptRow + 1; i < matrix.length; i++) {
            System.arraycopy(matrix[i], 1, minor[i - 1], 0, minorLength);
        }

        return new Matrix(minor);
    }

}
