import java.util.HashMap;


/**
 * Class for a Vending Machine.  Contains a hashtable mapping item names to item data, as
 * well as the current balance of money that has been deposited into the machine.
 */
class Vending {
    private static HashMap<String, Item> Stock = new HashMap<String,Item>();
    private double balance;

    Vending(int numCandy, int numGum) {
        Stock.put("Candy", new Item(1.25, numCandy));
        Stock.put("Gum", new Item(.5, numGum));
        this.balance = 0;
    }

    //keep track of stock
public int getItemStock(String itemName){
        Item item = Vending.Stock.get(itemName);
        return item.stock;
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
            System.out.print("That item cannot be added");
        }
    }

}

class Examples {
}

