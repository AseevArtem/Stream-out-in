import java.io.*;

public class Basket implements Serializable {

    protected static int[] prices;
    protected static String[] products;
    protected static int sumProducts;
    protected static int[] countProducts = new int[3];


    Basket(int[] prices, String[] products) {
        Basket.prices = prices;
        Basket.products = products;
    }

    Basket(int[] prices, String[] products, int sumProducts, int[] countProducts) {
        Basket.prices = prices;
        Basket.products = products;
        Basket.sumProducts = sumProducts;
        Basket.countProducts = countProducts;
    }

    public static int getSumProducts() {
        return sumProducts;
    }

    public static int[] getCountProducts() {
        return countProducts;
    }

    static Basket loadFromBinFile(File file) throws Exception {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            Basket basket = (Basket) in.readObject();
        }

        return new Basket(prices, products, sumProducts, countProducts);
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

    public void saveBin(File file)  throws Exception {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(new Basket(prices, products, sumProducts, countProducts));
        }
    }
}




