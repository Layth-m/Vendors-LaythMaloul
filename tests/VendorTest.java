import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VendorTest {

    static Vending vendor;
    @BeforeEach
    public void setup() {
       vendor = new Vending(5, 5);
    }
    @Test
    void addition() {
        assertEquals(2, 1 + 1);
    }

    @Test
    void testAddMax(){
        vendor.addMoney(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, vendor.getBalance());
    }

    @Test
    void testAddMIN(){
        vendor.addMoney(Integer.MIN_VALUE);{
            assertEquals(0, vendor.getBalance());
        }
    }
    @Test
    void testAddMID(){
        vendor.addMoney(5000);{
            assertEquals(5000, vendor.getBalance());
        }
    }
}