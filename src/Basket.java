import java.io.*;

public class Basket implements Serializable {

    protected int[] prices;
    protected String[] products;
    protected int sumProducts;
    protected int[] countProducts = new int[3];


    Basket(int[] prices, String[] products) {
        this.prices = prices;
        this.products = products;

    }

    Basket(int[] countProducts, int sumProducts) {
        this.sumProducts = sumProducts;
        this.countProducts = countProducts;
    }

    static Basket loadFromBinFile(File file) throws Exception {
        Basket basket1;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            basket1 = (Basket) in.readObject();
        }
        return new Basket(basket1.countProducts, basket1.sumProducts);
    }

    public int getSumProducts() {
        return sumProducts;
    }

    public int[] getCountProducts() {
        return countProducts;
    }

    public int[] getPrices() {
        return prices;
    }

    public String[] getProducts() {
        return products;
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
            out.writeObject(new Basket(prices, products));
        }
    }
}




