package Application;

import Application.persistence.AddressBook;
import Application.persistence.BuddyInfo;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AddressBookTest {

    AddressBook addressBookTest;

    @Before
    public void setUp() throws Exception {
        addressBookTest = new AddressBook();
    }

    @Test
    public void addBuddy() {
        BuddyInfo testBud = new BuddyInfo("Babak1", "6131234");
        addressBookTest.addBuddy(testBud);
        assertEquals(1,addressBookTest.getBuddies().size());
    }
}