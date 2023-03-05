package suite.desktop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sdu.dk.group.one.DesktopLauncher;

public class DesktopTest {
    @Test
    public void addTest(){
        Assertions.assertEquals(DesktopLauncher.add(2,2),4);
    }
    @Test
    public void test1(){
        Assertions.assertEquals(2,2);
    }
}
