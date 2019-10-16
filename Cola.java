
package ejercicios;

import java.util.ArrayList;


public class Cola <T> implements ColaADT <T>{

    private T[] cola;
    private int frente;
    private int fin;
    private final int MAX = 20;
    
    
    public Cola(){
        cola = (T[]) new Object[MAX];
        frente = -1;
        fin = -1;
        
    }
    
    public Cola(int max){
        cola = (T[]) new Object[max];
        frente = -1;
        fin = -1;
        
    }
    
    public boolean isEmpty() {
        return frente == -1;
    }

    public T consultaPrimero() {
        if(isEmpty())
            throw new EmptyCollectionException();
        return cola[frente];
    }

    public T quita() {
        if(isEmpty())
            throw new EmptyCollectionException();;
        T res = cola[frente];
        cola[frente] = null;
        if(frente == fin){ //Hay un solo dato
            frente = -1; //cola quedó vacía
            fin = -1;
        }
        else
            frente = (frente + 1) % cola.length;
        return res;
    }

    public void agrega(T dato) {
        if(frente == -1)
            frente = 0;
        else if((fin + 1) % cola.length == frente)
                expande();
        fin = (fin + 1) % cola.length;
        cola[fin] = dato;
        
    }
    
    private void expande(){
        T[] nuevo = (T[]) new Object[cola.length * 2];
        int i, n, j;
        n = cola.length;
        for(i = 0; i < n; i++){
            j = (frente + i) % n;
            nuevo[i] = cola[j];
        }
        
        cola = nuevo;
        frente = 0;
        fin = n - 1;
    }

    
    public int cuentaElementos() {
        int cont;
        if(isEmpty())
            cont = 0;
        else if(frente <= fin)
            cont =  fin - frente + 1;
        else
            cont =  cola.length - frente + fin + 1;
            
        return cont;
    }

    public T consultaUltimo() {
        if(isEmpty())
            throw new EmptyCollectionException();
        return cola[fin];
    }

    public ArrayList multiQuita(int n) {
        ArrayList<T> aux;
        if (cuentaElementos() >= n){
            aux = new ArrayList();
            for (int i = 0; i < n; i++)
               aux.add(quita());
            
        } else
            aux = null;
        return aux;
    }

    
    public String toString() {
        StringBuilder res = new StringBuilder();
        int i;
        for( i = frente; i != fin;  i = (i + 1) % cola.length){
            res.append(cola[i] + " ");
        }
        
        return res.toString();
    }
    
    
    
    public boolean noHayVecinosIguales(){
        boolean res = false;
        int i = frente;
        System.out.println("frente = " + frente + "\nfin = " + fin);
        while(i != fin  && cola[i] != cola[(i + 1) % cola.length]){
            System.out.println("cola[" + i + "] = " + cola[i]);
            System.out.println("cola[" + (i + 1) % cola.length + "] = " + cola[(i + 1) % cola.length]);
            i++;
        }
        if( i == fin)
            res = true;
        
        return res;
        
    }
    
    public boolean boHayVecinosIguales(){
        if(frente != -1){
            int i = cuentaElementos();
            System.out.println("i = " + i);
            T auxi = quita();
            System.out.println("auxi 0 = "  + auxi);
            return boHayVecinosIguales(auxi, true, i, 1);
        }
        else
            return false;
    }
    
    private boolean boHayVecinosIguales(T auxi, boolean res, int i, int j){
        T otro;
        if(j < i){
            otro = quita();
            agrega(auxi);
            return boHayVecinosIguales(otro, !auxi.equals(otro), i, j + 1);
            
        }
        else{
            agrega(auxi);
            return res;
        }
        
    }
    
   
}
