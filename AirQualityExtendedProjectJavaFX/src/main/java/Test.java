import java.time.LocalDate;
import java.time.LocalTime;

public class Test {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        System.out.println(localTime.getHour());
    }
}
