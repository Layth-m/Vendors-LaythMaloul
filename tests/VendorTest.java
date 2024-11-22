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

    @Test
    void testBuy()
    {
        vendor.addMoney(1.25);
        vendor.select("Candy");
        assertEquals(0, vendor.getBalance());
    }
    @Test
    void testEmpty(){
        vendor.addMoney(10.0);

        //purchase all candies
        for(int i = 0; i<5; i++){
            vendor.select("Candy");
        }
        //purchase all gum
        for(int i = 0; i<5; i++){
            vendor.select("Gum");
        }

        assertEquals(0, vendor.getItemStock("Candy"));
    }

    @Test
    public void testRestock(){
        //restock items

       //test min
        vendor.restock("Gum",Integer.MIN_VALUE);
        assertEquals(5, vendor.getItemStock("Gum"));
        // test mid
        vendor.restock("Candy",10);
        assertEquals(15,vendor.getItemStock("Candy") );
        //test max
         vendor.restock("Candy",Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, vendor.getItemStock("Candy"));
    }



}