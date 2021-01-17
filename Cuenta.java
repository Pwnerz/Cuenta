import java.lang.*;

public class Cuenta {

  private double saldo;

  public Cuenta() {
    saldo = 0.0;
  }

  public Cuenta(double valor) {
    this.saldo = valor;

  }
  // * getsaldo retorna el saldo

  public double getSaldo() {
    return saldo;
  }

  // * pone el saldo en la cuenta
  public void setSaldo(double valor) {
    this.saldo = valor;
  }

  // * operación propia de la clase cuenta
  public void reintegro(double valor) {
    this.saldo -= valor;
    // %.2f marca dos decimales.
    System.out.printf("Retirando %.2f.", valor);
    System.out.println();
  }

  // * operación propia de la clase cuenta
  public void ingreso(double valor) {
    this.saldo += valor;
    System.out.printf("Ingresando %.2f.", valor);
    System.out.println();
  }
}
