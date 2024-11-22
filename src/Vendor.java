import java.util.HashMap;


/**
 * Class for a Vending Machine.  Contains a hashtable mapping item names to item data, as
 * well as the current balance of money that has been deposited into the machine.
 */
class Vending {
    private static HashMap<String, Item> Stock = new HashMap<String,Item>();
    private double balance;
    public String vendorName;


    Vending(String vendorName, int numCandy, int numGum) {
        this.vendorName = vendorName;
        Stock.put("Candy", new Item(1.25, numCandy,"Sweat snack for a quick burst of energy"));
        Stock.put("Gum", new Item(.5, numGum, "Freshens Breath and is low calorie"));
        this.balance = 0;

    }

    //keep track of stock
public int getItemStock(String itemName){
        if(Stock.containsKey(itemName)){
            Item item = Vending.Stock.get(itemName);
            return item.stock;
        }
        else{
            return -1;
        }

}
    /** resets the Balance to 0 */
    void resetBalance () {
        this.balance = 0;
    }

    /** returns the current balance */
    double getBalance () {
        return this.balance;
    }

    /** adds money to the machine's balance
     * @param amt how much money to add
     * */
    void addMoney (double amt)
    {
        if(amt <= 0){

            return;
        }
        //ensure if max int is added then max int is not surpassed.
        if(amt + getBalance() <0){
            this.balance = Integer.MAX_VALUE;
            return;
        }

        this.balance = this.balance + amt;
    }

    /** attempt to purchase named item.  Message returned if
     * the balance isn't sufficient to cover the item cost.
     *
     * @param name The name of the item to purchase ("Candy" or "Gum")
     */
    void select (String name) {
        if (Stock.containsKey(name)) {
            Item item = Stock.get(name);
            if (balance >= item.price) {
                item.purchase(1);
                this.balance = this.balance - item.price;
            }
            else
                System.out.println("Gimme more money");
        }
        else System.out.println("Sorry, don't know that item");
    }
    void restock(String name, int amt){
        if(Stock.containsKey(name)){
            Item item = Stock.get(name);
            item.restock(amt);
        }
        else{
            //item not in list add it
            Stock.put(name, new Item(3.0,amt, ""));
        }
    }

    void renameItem(String oldName, String newName){
        if(Stock.containsKey(oldName)&& !Stock.containsKey(newName)){
            Item item = Stock.get(oldName);
            Stock.remove(oldName);
            Stock.put(newName, item);
        }
        else{
            System.out.print("Invalid name");
        }

    }

    void printInventory(){
        System.out.println("vendor: "+ vendorName);
        for(String itemName: Stock.keySet()){
            Item item = Stock.get(itemName);
            System.out.println("-"+ itemName+ ": "+ item.stock + " price: "+ item.price+" ");
        }
    }
    void RemoveItem(String ItemName){
        if(Stock.containsKey(ItemName)){
            Stock.remove(ItemName);
        }
        else {
            System.out.print("Item not found to be removed");
        }
    }

    int getPurchaseCount(String name){
        Item item = Stock.get(name);
        return item.purchaseCount;
    }

    String getDescription(String name){
       Item item = Stock.get(name);
       if(item == null ){
           return "Item not found";
       }
       else {
          return item.getItemDescription();
       }
    }

    double getItemPrice(String name){
        Item item = Stock.get(name);
        if(item!= null){
            return item.getPrice();
        }
        else{
            System.out.print("Invalid item name");
            return -1;
        }
    }

    void discount(String name, double discountPercentage){
        Item item = Stock.get(name);
        if(item!= null){
            item.applyDiscount(discountPercentage);
        }
        else{
            System.out.print("Item DNE");
        }
    }

    void setAsBestSeller(String name){
        Item item = Stock.get(name);
        if(item != null){
            item.setBestSeller();
        }
        else{
            System.out.print("Item DNE");
        }
    }

    //method to check bestseller status
    boolean isBestSeller(String name){
        Item item = Stock.get(name);

       //checks item exists and is true returns true else false.
        return item != null && item.isbestSeller();
    }


}


class Examples {
}

