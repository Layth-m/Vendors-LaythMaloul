class Item {
    double price;
    int stock;

    public int purchaseCount;
    Item(double price, int numPieces) {
        this.price = price;
        this.stock = numPieces;
        this.purchaseCount = 0;
    }

    void restock(int amount) {
        if(amount <=0 ){
            System.out.print("Amount cannot be <= 0");
            return;
        }
        if(amount + this.stock <= 0){
            this.stock = Integer.MAX_VALUE;
            return;
        }
        else{
            this.stock = this.stock + amount;
            return;
        }

    }

    void purchase(int amount) {
        this.stock = this.stock - amount;
        this.purchaseCount += amount;
    }
}