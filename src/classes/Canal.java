package classes;

import java.util.ArrayList;
import java.util.List;
import Interfaces.IObserver;

/*  El Canal actúa como el sujeto. Este es el objeto que mantiene 
una lista de observadores y los notifica de los cambios 
(subida de videos).*/

public class Canal {
    private List<IObserver> subscriptores = new ArrayList<>();
    private String tituloVideo;
    private String nombre;

    public Canal(String nombre) {
        this.nombre = nombre;
    }

    public void subirVideo(String title) {
        this.tituloVideo = title;
        System.out.println("\nEl canal de Youtube " + this.nombre + " ha subido un nuevo video: " + title);
        notificarUsuarios();
    }

    // Método privado para agregar suscriptores
    protected void agregarSuscriptor(IObserver subscriptor) {
        if (!subscriptores.contains(subscriptor)) {
            subscriptores.add(subscriptor);
            System.out.println("\nSe ha agregado un nuevo subscriptor al canal " + this.nombre);
        } else {
            System.out.println("\nEl suscriptor ya está en la lista del canal " + this.nombre);
        }
    }

    // Método privado para quitar suscriptores
    protected void quitarSuscriptor(IObserver subscriptor) {
        if (subscriptores.remove(subscriptor)) {
            System.out.println("\nSe ha quitado un subscriptor de la lista del canal " + this.nombre);
        } else {
            System.out.println("\nEl suscriptor no estaba en la lista del canal " + this.nombre);
        }
    }

    public void notificarUsuarios() {
        for (IObserver subscriptor : subscriptores) {
            subscriptor.update(tituloVideo, nombre);
        }
    }

    public String getNombre() {
        return nombre;
    }
}
