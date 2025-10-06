import org.example.Calculadora;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculadoraTest {
    Calculadora calc = new Calculadora();

    @Test
    public void deveSomarDoisValores(){

        double valorA = 10;
        double valorB = 20;

        double resultado = calc.somar(valorA, valorB);

        Assertions.assertEquals(30, resultado);


    }

    @Test
    public void deveSubtrairDoisValores() {

        double valorA = 10;
        double valorB = 5;

        double resultado = calc.subtrair(valorA, valorB);

        Assertions.assertEquals(5, resultado);

    }


    @Test
    public void deveDividirDoisValores() {

        double valorA = 10;
        double valorB = 5;

        double resultado = calc.dividir(valorA, valorB);

        Assertions.assertEquals(2, resultado);
    }

    @Test
    public void deveMultiplicarDoisValores() {
        double valorA = 10;
        double valorB = 5;

        double resultado = calc.multiplicar(valorA, valorB);

        Assertions.assertEquals(50, resultado);
    }

}
