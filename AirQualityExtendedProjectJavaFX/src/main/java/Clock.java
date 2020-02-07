import java.time.LocalTime;
import java.util.ArrayList;

public class Clock implements Runnable, Observable {
    public Thread worker;
    protected volatile boolean isRunning = false;
    private int interval;
    private LocalTime localTime;
    private ArrayList<Observer> observers;

    public Clock(int interval) {
        this.interval = interval;
        observers = new ArrayList<>();
    }

    public Clock() {
        interval = 1000*60*60;
        observers = new ArrayList<>();
    }
    public void start(){
        worker = new Thread(this,"Clock thread");
        worker.start();
        System.out.println("Creating new thread");
    }
    public void stop(){
        isRunning = false;
    }

    public void interrupt(){
        isRunning = false;
        worker.interrupt();
    }

    @Override
    public void registerObserver(Observer o) {
        if(!observers.contains(o)){
            observers.add(o);
            System.out.println(o + " connected to the clock");
        }
    }

    @Override
    public void removeObserver(Observer o) {
        if(observers.contains(o)){
            observers.remove(o);
            System.out.println(o + "disconnected");
        }

    }

    @Override
    public void updateObservers() {
        for (Observer o: observers) {
         localTime = LocalTime.now();
         o.update(localTime);
        }
    }

    @Override
    public void run() {
        isRunning = true;
        while(isRunning){
            System.out.println("Updating");
            updateObservers();
        }

        try{
            Thread.sleep(interval);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
