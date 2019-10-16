package ejercicios;

import java.util.ArrayList;

public interface ColaADT <T>{
    public boolean isEmpty();
    public T consultaPrimero();
    public T quita();
    public void agrega(T dato);
    public int cuentaElementos();
    public T consultaUltimo();
    public ArrayList<T> multiQuita(int n);
    public boolean noHayVecinosIguales();
    public String toString();
}