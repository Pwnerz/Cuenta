import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.lang.*;

public class Cuenta {
	
	private double saldo;

	public Cuenta() {
		saldo=0.0;
		 }
	
	public Cuenta(double valor) {
		 this.saldo=valor;
     
		 }
		 //* getsaldo retorna el saldo
	
	public double getSaldo() {
		 return saldo;
	}
		 //* pone el saldo en la cuenta
	public void setSaldo(double valor) {
		 this.saldo = valor;
		 }
		 //* operación propia de la clase cuenta
	public void reintegro(double valor) {
		 this.saldo -= valor;
		 //%.2f marca dos decimales.
		 System.out.printf("Retirando %.2f.",valor);
		 System.out.println();
		 }
		 //* operación propia de la clase cuenta
	public void ingreso(double valor) {
		 this.saldo += valor;
		 System.out.printf("Ingresando %.2f.",valor);
		 System.out.println();
		 }
}
