package ch.arpage.testokhttp;

import java.util.Observable;


public class MyObservable<T> extends Observable {

    private T value;

    public MyObservable(T initialValue) {
        this.value = initialValue;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
        this.setChanged();
        this.notifyObservers(value);
    }
}
