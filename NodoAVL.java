package ejercicios;

public class NodoAVL <T extends Comparable <T>> {
    T element;
    NodoAVL<T> izq, der,papa;
    int fe;

    NodoAVL(T elem) {
        element = elem;
        izq = null;
        der = null;
        papa = null;
        fe = 0;
    }
    
    public T getElement() {
        return element;
    }
    
    public String toString() {
        return element.toString() + "\n[" + izq.toString() + "---" + der.toString() + "]";
    }

    public NodoAVL<T> getDer() {
        return der;
    }

    public NodoAVL<T> getIzq() {
        return izq;
    }

    public void  setElement(T element) {
        this.element = element;
    }
    

    public void setIzq(NodoAVL<T> elem) {
        izq = elem ;
    }

    public void setDer(NodoAVL<T> elem) {
        der = elem;
    }
    
    public NodoAVL<T> getPapa() {
        return papa;
    }
    public void setPapa(NodoAVL<T> pap) {
        papa = pap;
    }
    
     
    public void cuelga (NodoAVL<T> N){
        if(N == null)
            return;
        if(N.element.compareTo(element) < 0)
            izq = N;
        else
            der = N;
        N.setPapa(this);
    
    }    

    public int getFe() {
        return fe;
    }

    public void setFe(int fe) {
        this.fe = fe;
    }
    
    
}
