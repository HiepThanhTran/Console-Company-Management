package topic2;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class T {

    public static void main(String[] args) {
        List<String> values = createDummyData();

        values.forEach(value -> System.out.println(value));

    }

    public static List<String> createDummyData() {
        int max = 1000000;
        List<String> values = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }
        return values;
    }
}
