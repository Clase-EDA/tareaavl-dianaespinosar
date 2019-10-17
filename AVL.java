package ejercicios;

import java.util.ArrayList;
import java.util.Iterator;

public class AVL <T extends Comparable<T>> {
    protected NodoAVL raiz = new NodoAVL(null);
    
    
    public boolean isEmpty() {
        if(raiz == null)
            return true;
        else 
            return false;
    }
    
    public void setRaiz(NodoAVL<T> a){
        raiz = a;
    }
    
//inserta a partir del nodo insertado
    public void add(T ele) {
        System.out.println("Vamos a agregar a " + ele);
        if(raiz != null){
            NodoAVL<T> elem = new NodoAVL<T>(ele);
            add(raiz, elem);
        }    
        else
            raiz = new NodoAVL(ele);
        
    }
    
    private void add(NodoAVL actual, NodoAVL elem){
        
        if(actual.getElement().compareTo(elem.getElement()) > 0){
            if( actual.getIzq() != null)
                add(actual.getIzq(), elem);
            else{
                actual.setIzq(elem);
                actual.getIzq().setPapa(actual);
                System.out.println("Se agregó a la izquierda de " + actual.getElement());
                arreglaFE(actual.getIzq());
            }
        }
        else{
            if(actual.getDer() != null)
                add(actual.getDer(), elem);
            else{
                actual.setDer(elem);
                actual.getDer().setPapa(actual);
                System.out.println("Se agregó a la derecha de " + actual.getElement());
                arreglaFE(actual.getDer());
            }
       }
    }
    
    public void arreglaFE(NodoAVL<T> nuevo) {
        NodoAVL<T> actual = nuevo;
        boolean termine = false;
        while (!termine && actual != null && actual.getPapa() != null) {
            if (actual.equals(actual.getPapa().getIzq())) {
                //actual.getPapa().setFe(actual.getPapa().getFe() - 1);
                actual.getPapa().fe -= 1;
            } else {
                actual.getPapa().setFe(actual.getPapa().getFe() + 1);
                //actual.getPapa().fe += 1;
            }
            
            if (Math.abs(actual.getPapa().fe) == 2) {
                actual = rotacion(actual.getPapa());
            }
            if (actual.getPapa() != null && actual.getPapa().fe == 0) {
                termine = true;
            }
            
            actual = actual.getPapa();


        }
    }
    
    
     public int altura(NodoAVL<T> n){
         
         if(n != null){
             return altura(n.getDer(), n.getIzq());
         
         }else
             return 0;
     }
    
    private int altura(NodoAVL <T> der, NodoAVL <T> izq){
        int alt;
        if( der != null && izq != null){
            alt = Math.max(altura(der.getDer(),der.getIzq()), altura(izq.getDer(),izq.getIzq()));
        }
        else{
            if(der != null)
                alt = altura(der.getDer(),der.getIzq());
            else{
                if(izq != null)
                    alt = altura(izq.getDer(),izq.getIzq());
                else
                    alt = 0;
            }
               
        }
        alt = alt +1; 
        return alt;
    }

    
    private NodoAVL<T> rotacion(NodoAVL<T> n) {
        System.out.println("Rotación");
        System.out.println(n.getElement() + " tiene F.E. de " + n.getFe());
        NodoAVL<T> alfa = null;
        NodoAVL<T> beta = null;
        NodoAVL<T> gamma = null;
        NodoAVL<T> A = null;
        NodoAVL<T> B = null;
        NodoAVL<T> C = null;
        NodoAVL<T> D = null;
        NodoAVL<T> papa = null;
         
        if(n.getFe() == -2){
            //izquierda-Derecha
            if(n.getIzq() != null &&n.getIzq().getFe() == 1){
                System.out.println("Izquierda-Derecha");
                alfa = n;
                papa = n.getPapa();
                beta = alfa.getIzq();
                
                gamma = beta.getDer();
                A = beta.getIzq();
                B = gamma.getIzq();
                C = gamma.getDer();
                D = alfa.getDer();

                gamma.cuelga(beta);
                gamma.cuelga(alfa);
                if(C != null)
                    alfa.cuelga(C);
                else 
                    alfa.setIzq(null);
                alfa.cuelga(D);
                beta.cuelga(A);
                if(B != null)
                    beta.cuelga(B);
                else
                    beta.setDer(null);
                if (papa != null) {
                    papa.cuelga(gamma);
                } else {
                    gamma.setPapa(null);
                    raiz = gamma;
                }
                alfa.setFe(altura(D) - altura(C));          
                beta.setFe(altura(B) - altura(A));
                gamma.setFe(altura(alfa) - altura(beta));
                return gamma;
            }
            //izquierda-izquierda
            else{
                System.out.println("Izquierda-Izquierda");
                alfa = n;
                papa = n.getPapa();
                beta = alfa.getIzq();
                gamma = beta.getIzq();
                A = gamma.getIzq();
                B = gamma.getDer();
                C = beta.getDer();
                D = alfa.getDer();
                gamma.cuelga(A);
                gamma.cuelga(B);
                if(C != null)
                    alfa.cuelga(C);
                else
                    alfa.setIzq(null);
                alfa.cuelga(D);
                beta.cuelga(alfa);
                beta.cuelga(gamma);
                if (papa != null) {
                    papa.cuelga(beta);
                } else {
                    beta.setPapa(null);
                    raiz = beta;
                }
                int k = altura(alfa.getDer());
                int r = altura(alfa.getIzq());
                alfa.setFe(k - r);
                beta.setFe(altura(alfa) - altura(gamma));
                return beta;
            }
        }
        else{
            //derecha-izquierda
            if(n.getDer() != null && n.getDer().getFe() == -1){
                System.out.println("Derecha-Izquierda");
                alfa = n;
                papa = n.getPapa();
                beta = alfa.getDer();
                gamma = beta.getIzq();
                A = alfa.getIzq();
                B = gamma.getIzq();
                C = gamma.getDer();
                D = beta.getDer();

                gamma.cuelga(beta);
                gamma.cuelga(alfa);
                alfa.cuelga(A);
                if(B != null)
                    alfa.cuelga(B);
                
                else
                    alfa.setDer(null);
                if(C != null)
                    beta.cuelga(C);
                else
                    beta.setIzq(null);
                beta.cuelga(D);
                if (papa != null) {
                    papa.cuelga(gamma);
                } else {
                    gamma.setPapa(null);
                    raiz = gamma;
                }
                alfa.setFe(altura(B) - altura(A));
                beta.setFe(altura(D) - altura(C));
                gamma.setFe(altura(beta) - altura(alfa));
                return gamma;
            }
            //derecha-derecha
            else{
                System.out.println("Derecha-Derecha");
                alfa = n;
                papa = n.getPapa();
                beta = alfa.getDer();
                gamma = beta.getDer();
                A = alfa.getIzq();
                B = beta.getIzq();
                
                C = gamma.getIzq();
                
                D = gamma.getDer();
                alfa.cuelga(A);
                
                if(B != null)
                    alfa.cuelga(B);
                else
                    alfa.setDer(null);
                gamma.cuelga(C);
                gamma.cuelga(D);
               
                beta.cuelga(alfa);
                beta.cuelga(gamma);
                if (papa != null) {
                    papa.cuelga(beta);
                } else {
                    beta.setPapa(null);
                    raiz = beta;
                }
                int k = altura(alfa.getDer());
                int r = altura(alfa.getIzq());
                alfa.setFe(k - r);    
                beta.setFe(altura(gamma) - altura(alfa));
                return beta;
            }
        }
             
    }

   
    //Buscar
    public NodoAVL<T> busca(T elem){
        return busca(raiz, elem);
    }
    
    private NodoAVL<T> busca(NodoAVL<T> N, T elem){
        boolean encontre= false;
        while(!encontre && N != null){
            if(elem.compareTo(N.getElement()) < 0)
                N = N.getIzq();
            else
                if (elem.compareTo(N.getElement()) > 0)
                    N = N.getDer();
                else
                    encontre =true;           
        }
        if(encontre)
            return N;
        return null;
    }
    //Elimina
    
   public T remove(T elem) {
       System.out.println("\nSe va a eliminar: " + elem);
        remove1(elem);
        return elem;
    }
    
    private boolean remove1(T elem){
        NodoAVL<T> bor = busca(raiz, elem);
        if (bor == null) {
            return false;
        } else {
            
            if (bor.getIzq() == null && bor.getDer() == null) {
                System.out.println("NO TIENE HIJOS");
                if(bor == raiz){
                    raiz = null;
                }else{
                    if (bor.getPapa().getElement().compareTo(elem) > 0) {
                        System.out.println("Es hijo izquierdo");
                        NodoAVL<T> aux = bor.getPapa();
                        System.out.println("Papa es " + bor.getPapa().getElement());
                        aux.setIzq(null);
                        aux.fe += 1;
                        if(Math.abs(aux.fe) != 1 )
                            eliminaFE(aux);    
                        
                    } else {
                        System.out.println("Es hijo Derecho");
                        NodoAVL<T> aux = bor.getPapa();
                        System.out.println("Papa es " + bor.getPapa().getElement());
                        aux.setDer(null);
                        aux.fe -= 1;
                        if(Math.abs(aux.fe) != 1 )
                            eliminaFE(aux);
                    }
                }
            } else {
                System.out.println("TIENE AL MENOS UN HIJO");
                if (bor.getIzq() == null) {
                    System.out.println("No tiene hijo Izq");
                    if(bor == raiz){;
                        raiz = bor.getDer();
                        raiz.setPapa(null);
                    }
                    else{
                        bor.getPapa().cuelga(bor.getDer());
                        bor.getPapa().fe -= 1;
                        System.out.println(bor.getPapa().fe);
                        eliminaFE(bor.getPapa());
                    }
                }else if(bor.getDer() == null){
                    System.out.println("No tiene hijo Der");
                    if(bor == raiz){
                        raiz = bor.getDer();
                        raiz.setPapa(null);
                    }
                    else{
                        System.out.println("Tiene los 2 hijos");
                        bor.getPapa().cuelga(bor.getIzq());
                        bor.getPapa().fe += 1;
                        System.out.println(bor.getPapa().fe);
                        eliminaFE(bor.getPapa());
                        
                    }
                    
                }
                else{
                    System.out.println("TIENE LOS 2 HIJOS");
                    NodoAVL<T> suc = bor.getDer();
                    System.out.println("Se va a eliminar " + bor.getElement() + " y fe --> " + bor.getFe());
                    System.out.println("primer suc " + suc.getElement());
                    
                    if(suc.getIzq() == null){
                        System.out.println("Suc no tiene hijos");
                        bor.setElement(suc.getElement());
                        bor.setDer(suc.getDer());
                        bor.fe -= 1;
                        if(Math.abs(bor.fe) != 1 )
                            eliminaFE(bor);
                    }
                    else{
                        
                        while( suc.getIzq().getIzq() != null){
                        suc = suc.getIzq();
                        }
                        bor.setElement(suc.getIzq().getElement());
                        System.out.println("Ahora bor es " + bor.getElement());
                        System.out.println("Suc es " + suc.getElement());
                        NodoAVL aux = suc;
                        aux.setIzq(suc.getIzq().getDer());
                        
                        if(aux.getIzq() != null)
                            System.out.println("Ahora el hijo izquierdo de suc es " + aux.getIzq().getElement());
                        else
                            System.out.println(aux.getElement() + " hijo izq es null");
                        aux.fe += 1;
                        if(Math.abs(aux.fe) != 1 )
                            eliminaFE(aux);
                    }
                    
                    
                }

            }
           
        
        }
         
        return true;
    }
    
    public void eliminaFE(NodoAVL<T> nuevo){
        System.out.println("Vamos a arreglar los factores de equilibrio");
        NodoAVL<T> actual = nuevo;
        System.out.println(actual.getElement()  + " y su F.E. es " + actual.getFe());
        boolean termine = false;
        if(Math.abs(actual.fe) == 2){
               actual = rotacion (actual);
        }
        System.out.println("actual ahora es " + actual.getElement() + " F.E. " + actual.fe);
        if(actual.getPapa() != null)
            System.out.println("papa actual " + actual.getPapa().getElement());
        while(!termine && actual != null && actual.getPapa() != null){  
            if(actual == actual.getPapa().getIzq()){
                    actual.getPapa().fe += 1;
                    
            }
            else{
                actual.getPapa().fe -= 1;
            }
            
           if(Math.abs(actual.getPapa().fe) == 2){
               
               actual = rotacion (actual.getPapa()); 
               System.out.println("actual " + actual.getElement() + " --> " + actual.fe);
           }
           if(actual.getPapa() != null && Math.abs(actual.getPapa().fe) == 1)
               termine = true;
           actual = actual.getPapa();

        }
        
    }

   
    
    public String imprimirArbol(){
        StringBuilder res = new StringBuilder();
        Cola<NodoAVL> a = new Cola();
       if(raiz == null)
           return "No hay elementos";
       else{
           a.agrega(raiz);
           correArbol(res, a);
           return res.toString();
       }
        
    }

    private void correArbol(StringBuilder res, Cola<NodoAVL> a){
        if(a.isEmpty()){
            return;
        }
        else{
            NodoAVL aux = a.quita();
            res.append(aux.getElement());
            res.append(" y su F.E. es " ).append(aux.getFe());
            res.append("  ");
            if(aux.getIzq() != null)
                a.agrega(aux.getIzq());
            if(aux.getDer() != null)
                a.agrega(aux.getDer());
            correArbol(res,a);
        }
    }
    
    
    
    
    public static void main(String[] args) {
        
       NodoAVL a = new NodoAVL(100);

       AVL arbol = new AVL();/*
       arbol.setRaiz(a);
       arbol.add(5);
       arbol.add(110);
       arbol.add(100);
       arbol.add(4);
       arbol.add(200);
       arbol.add(50);
       arbol.add(115);
       System.out.println(arbol.imprimirArbol());
       System.out.println(arbol.busca(110).getPapa().getElement());
       arbol.add(300);
       System.out.println(arbol.imprimirArbol());
       
       arbol.add(37);
       System.out.println(arbol.busca(37).getPapa().getElement());
       System.out.println("Inicia eliminación");
       arbol.remove(100);
       arbol.remove(5);
       System.out.println(arbol.imprimirArbol());
       arbol.remove(37);
       arbol.remove(110);
       */

       arbol.setRaiz(a);
       arbol.add(300);
       arbol.add(400);
       arbol.add(350);
       arbol.add(375);
       arbol.add(50);
       arbol.add(200);
       arbol.add(360);
       arbol.add(380);
       arbol.add(500);
       arbol.add(390);
       System.out.println(arbol.imprimirArbol());
       arbol.remove(375);
       System.out.println(arbol.imprimirArbol());
       arbol.remove(50);
       System.out.println(arbol.imprimirArbol());
       arbol.remove(400);
       System.out.println(arbol.imprimirArbol());
       arbol.remove(380);
       System.out.println(arbol.imprimirArbol());
     }

}
