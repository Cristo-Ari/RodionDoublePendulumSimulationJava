package RodionANumbers;

public interface ObservableADoubleValue {
    void addObserver(ADoubleChangeObserver observer);
    void removeObserver(ADoubleChangeObserver observer);
    void notifyObservers();
}
