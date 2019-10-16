package ejercicios;

public class EmptyCollectionException extends RuntimeException {

    public EmptyCollectionException() {
        super("Colección vacía");
    }
    
    public EmptyCollectionException(String mensaje){
        super(mensaje);
    }
    
}