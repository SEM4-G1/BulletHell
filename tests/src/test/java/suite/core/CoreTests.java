package suite.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sdu.dk.group.one.CoreEngine;

public class CoreTests {
    @Test
    public void multiplicationTest(){
        CoreEngine coreEngine = new CoreEngine();
        Assertions.assertEquals(coreEngine.multiply(2,2),4);
    }
}
