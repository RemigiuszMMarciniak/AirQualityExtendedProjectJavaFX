import java.time.LocalTime;

public class ChartMaker implements Observer{
    @Override
    public void update(LocalTime localTime) {
        System.out.println(localTime.getHour());
    }
}
