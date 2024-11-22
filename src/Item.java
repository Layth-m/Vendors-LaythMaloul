class Item {
    double price;
    int stock;
    public int purchaseCount;
    String ItemDescription;
    boolean bestSeller;
    Item(double price, int numPieces, String description) {
        this.price = price;
        this.stock = numPieces;
        this.purchaseCount = 0;
        this.ItemDescription = description;
        this.bestSeller = false;
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

    String getItemDescription(){
        return ItemDescription;
    }
    double getPrice(){
        return price;
    }

    void applyDiscount(double discountPercent){
        if(discountPercent<=100 && discountPercent > 0){
            this.price = this.price *(1 - discountPercent/100);
        }
        else{
            System.out.print("Enter a valid percent 1 - 100 ");
        }
    }
    void setBestSeller(){
        this.bestSeller = true;
    }
    boolean isbestSeller(){
        return this.bestSeller;
    }
}