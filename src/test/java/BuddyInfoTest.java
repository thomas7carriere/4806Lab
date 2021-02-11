import Application.persistence.BuddyInfo;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BuddyInfoTest {

    BuddyInfo testBud;

    @Before
    public void setupTest(){
        testBud = new BuddyInfo("testName", "testPhone");
    }

    @Test
    public void getName() {
        String name = testBud.getName();
        assertEquals("testName", name);
    }

    @Test
    public void getPhone() {
        String phone = testBud.getPhone();
        assertEquals("testPhone",phone);
    }

    @Test
    public void setName() {
        testBud.setName("testName1");
        String testName = testBud.getName();
        assertEquals("testName1",testName);
    }

    @Test
    public void setPhone() {
        testBud.setPhone("testPhone1");
        String testPhone = testBud.getPhone();
        assertEquals("testPhone1",testPhone);
    }
}