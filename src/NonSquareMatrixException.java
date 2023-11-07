/**
 * Исключение, выбрасываемое если матрица не квадратная
 */
public class NonSquareMatrixException extends Exception{
    public NonSquareMatrixException(){
        super("This matrix is not square!");
    }
}
