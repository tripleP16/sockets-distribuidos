package common;

import java.util.concurrent.ThreadLocalRandom;

public class RandomBank {

    public static int randomBank() {
        return ThreadLocalRandom.current().nextInt(0, 1 + 1);
    }
}
