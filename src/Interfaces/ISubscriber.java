package Interfaces;

import classes.Canal;

public interface ISubscriber {
    void suscribirse(Canal canal);
    void desuscribirse(Canal canal);
}