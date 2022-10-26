import java.io.*;

public class Basket implements Serializable {

    protected int[] prices;
    protected String[] products;
    private int sumProducts;
    private int[] countProducts = new int[3];



    Basket(int[] prices, String[] products) {
        this.prices = prices;
        this.products = products;
    }




    static Basket loadFromBinFile(File file) throws Exception {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            Basket basket = (Basket) in.readObject();
            basket.printCart();
            return basket;
        }
    }

    void addToCart(int productNum, int amount) {

        int currentPrice = prices[productNum] * amount;
        sumProducts += currentPrice;
        countProducts[productNum] += amount;
    }

    void printCart() {

        System.out.println("Ваша корзина: ");

        for (int j = 0; j < countProducts.length; j++) {
            if (countProducts[j] != 0) {
                System.out.println(products[j] + " " + countProducts[j] + " " + "шт " + prices[j] + " " + "руб/шт "
                        + countProducts[j] * prices[j] + " руб в сумме");
            }
        }
        System.out.println("Итого " + sumProducts + " руб");
    }

    public void saveBin(File file) throws Exception {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(this);
        }
    }
}




