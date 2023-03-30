package suite.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CoreTests {
    @Test
    public void multiplicationTest(){
        CoreEngine coreEngine = new CoreEngine();
        Assertions.assertEquals(coreEngine.multiply(2,2),4);
    }
}
