package Game;

import java.util.ArrayList;

public class GamePublisher {
    private ArrayList<IGameObserver> observers = new ArrayList<>();

    public void subscribe(IGameObserver observer) {
        observers.add(observer);
    }

    public void update(Game game) {
        for (IGameObserver observer: observers) {
            observer.update(game);
        }
    }
}
