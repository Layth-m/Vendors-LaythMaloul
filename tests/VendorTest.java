import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class VendorTest {
    private Vending vendor1;
    private Vending vendor2;
    private Vending vendor3;
    private Vending vendor4;

    private Vending vendor5;
    static Vending vendor;
    @BeforeEach
    public void setup() {
       vendor = new Vending("vendor",5, 5);
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
    @Test
    public void testRestockNewItem(){
        vendor.restock("Coke",20);
        assertEquals(20, vendor.getItemStock("Coke"));
    }

    @Test
    public void TestItemRename(){
        vendor.renameItem("Candy","Twix");

        //old candy no longer exists therefore count = -1
        //new candy should have value of old candy which is 5
        assertEquals(-1,vendor.getItemStock("Candy"));
        assertEquals(5, vendor.getItemStock("Twix"));
    }

    @Test
    public void testPrintInventory(){
        vendor1= new Vending ("Vendor1",1,5);
        vendor2= new Vending ("Vendor2",9,3);
        vendor3= new Vending ("Vendor3",30,10);
        vendor4= new Vending ("Vendor4",7,5);
        vendor5= new Vending ("Vendor5",10,9);
        vendor1.printInventory();
        vendor2.printInventory();
        vendor3.printInventory();
        vendor4.printInventory();
        vendor5.printInventory();
    }

    @Test
    public void testRemoveItem(){
        vendor.RemoveItem("Gum");
        assertEquals(-1,vendor.getItemStock("Gum"));

        //test for item that does not exist
        vendor.RemoveItem("DNE");
    }

    @Test
    public void testPurchaseTracking(){
        vendor.addMoney(10);
        vendor.select("Candy");
        vendor.select("Candy");
        vendor.select("Gum");

        assertEquals(2, vendor.getPurchaseCount("Candy"));
        assertEquals(1, vendor.getPurchaseCount("Gum"));
    }

    @Test
    public void TestItemDescription(){
        assertEquals("Sweat snack for a quick burst of energy",vendor.getDescription("Candy"));
        assertEquals("Freshens Breath and is low calorie", vendor.getDescription("Gum"));

        // test non existent item
        String DNE = vendor.getDescription("DNE");
        assertEquals("Item not found", DNE);
    }
    @Test
    public void TestDiscount(){
        // test apply invalid discount
        vendor.discount("Candy", 200);
        assertEquals(1.25, vendor.getItemPrice("Candy"));
        vendor.discount("Candy", Integer.MIN_VALUE);
        assertEquals(1.25, vendor.getItemPrice("Candy"));
        vendor.discount("Candy", Integer.MAX_VALUE);
        assertEquals(1.25, vendor.getItemPrice("Candy"));

        //test valid discount
        vendor.discount("Candy",20);
        assertEquals(1, vendor.getItemPrice("Candy"));

    }
    @Test
    public void TestBestSeller(){

        // no bestsellers by default
        Assertions.assertFalse(vendor.isBestSeller("Candy"));
        Assertions.assertFalse(vendor.isBestSeller("Gum"));

        //test candy as bestseller
        vendor.setAsBestSeller("Candy");
        Assertions.assertTrue(vendor.isBestSeller("Candy"));
        Assertions.assertFalse(vendor.isBestSeller("Gum"));

        //test Invalid candy
        vendor.setAsBestSeller("DNE");
        Assertions.assertFalse(vendor.isBestSeller("DNE"));
    }





}