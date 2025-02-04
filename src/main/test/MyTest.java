import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MathOperationsTest {

    @Test
    public void testAddition() {
        // Arrange
        int a = 5;
        int b = 3;

        // Act
        int result = MathOperations.add(a, b);

        // Assert
        assertEquals(8, result);
    }

    @Test
    public void testSubtraction() {
        // Arrange
        int a = 10;
        int b = 4;

        // Act
        int result = MathOperations.subtract(a, b);

        // Assert
        assertEquals(6, result);
    }

    @Test
    public void testMultiplication() {
        // Arrange
        int a = 6;
        int b = 7;

        // Act
        int result = MathOperations.multiply(a, b);

        // Assert
        assertEquals(42, result);
    }

    @Test
    public void testDivision() {
        // Arrange
        int a = 20;
        int b = 5;

        // Act
        int result = MathOperations.divide(a, b);

        // Assert
        assertEquals(4, result);
    }

    @Test
    public void testDivisionByZero() {
        // Arrange
        int a = 10;
        int b = 0;

        // Act & Assert
        assertThrows(ArithmeticException.class, () -> {
            MathOperations.divide(a, b);
        });
    }
}
