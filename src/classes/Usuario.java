package classes;

import Interfaces.IObserver;
import Interfaces.ISubscriber;

/*  Usuario es el observador, que implementa la interfaz IObserver y 
recibe las notificaciones del sujeto.*/

public class Usuario implements IObserver, ISubscriber {
    private String nombre;

    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void update(String tituloVideo, String nombreCanal) {
        System.out.println(nombre + ", hay un nuevo video disponible: " + tituloVideo + " en el canal " + nombreCanal);
    }

    // Métodos para suscribirse y cancelar suscripción
    public void suscribirse(Canal canal) {
        canal.agregarSuscriptor(this);
    }

    public void desuscribirse(Canal canal) {
        canal.quitarSuscriptor(this);
    }

    public String getNombre() {
        return nombre;
    }
}