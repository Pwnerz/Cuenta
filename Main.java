import java.util.*;

class Main {
  public static void main(String[] args) throws java.io.IOException, InterruptedException {
    System.out.println("\nStarted");
    ReplitDBClient client = new ReplitDBClient();
    Cuenta acc1 = new Cuenta();
    Cuenta acc2 = new Cuenta(12.5);
    String valor1 = String.valueOf(acc1.getSaldo());
    String valor2 = String.valueOf(acc2.getSaldo());
    client.set("saldo1", valor1);
    client.set("saldo2", valor2);
    System.out.println("El saldo de la cuenta a es: " + client.get("saldo1"));
    System.out.println("El saldo de la cuenta b es: " + client.get("saldo2"));
    acc1.ingreso(2.3);
    valor1 = String.valueOf(acc1.getSaldo());
    client.set("saldo1", valor1);
    System.out.println("El nuevo saldo de la cuenta a es: " + client.get("saldo1"));
    acc2.reintegro(3.5);
    valor2 = String.valueOf(acc2.getSaldo());
    client.set("saldo2", valor2);
    acc2.reintegro(3.5);
    valor2 = String.valueOf(acc2.getSaldo());
    client.set("saldo2", valor2);
    System.out.println("El nuevo saldo de la cuenta a es: " + client.get("saldo1"));
    System.out.println("El nuevo saldo de la cuenta b es: " + client.get("saldo2"));
    client.delete("saldo1", "saldo2");
  }

}
