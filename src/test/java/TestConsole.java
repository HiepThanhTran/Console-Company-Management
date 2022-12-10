import static topic2.ui.Factory.GREGORIANCALENDAR;
import static topic2.ui.Factory.SIMPLEDATEFORMAT;

import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TestConsole {

    public static void main(String[] args) throws ParseException {
        Date currentDate = GREGORIANCALENDAR.getTime();

        String startDate = "29/1/2003";
        String endDate = SIMPLEDATEFORMAT.format(currentDate);

        Date date1 = SIMPLEDATEFORMAT.parse(startDate);
        Date date2 = SIMPLEDATEFORMAT.parse(endDate);

        long diff = date1.getTime() - date2.getTime();
        long daysDiff = TimeUnit.MILLISECONDS.toDays(diff);
        System.out.println(Math.abs(daysDiff));
    }
}
