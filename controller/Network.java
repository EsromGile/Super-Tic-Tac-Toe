package controller;

public abstract class Network {
    public abstract void coordSent(int x, int y);
    public abstract void markReceived(Object object);
    public abstract void close();
}
