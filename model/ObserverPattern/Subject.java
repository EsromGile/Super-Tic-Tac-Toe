package model.ObserverPattern;

import model.TicTacToe;

public interface Subject {
    void subscribe(Observer o);
    void unsubscribe(Observer o);
    void notifyObservers(TicTacToe.Event event);
}
