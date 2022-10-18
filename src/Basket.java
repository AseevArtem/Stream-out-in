import java.io.*;

public class Basket {

    protected static int[] prices;
    protected static String[] products;
    protected static int sumProducts;
    protected static int[] countProducts = new int[3];


    Basket(int[] prices, String[] products) {
        Basket.prices = prices;
        Basket.products = products;
    }


    static Basket loadFromTxtFile(File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String[] buy = reader.readLine().split(" ");
            for (int i = 0; i < buy.length; i++) {
                countProducts[i] = Integer.parseInt(buy[i]);
            }
            sumProducts = Integer.parseInt(reader.readLine());
        }
        return new Basket(prices, products);
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


    public void saveTxt(File file) throws IOException {
        try (PrintWriter out = new PrintWriter(file)) {
            for (int countProduct : countProducts) {
                out.print(countProduct + " ");
            }
            out.print("\n");
            out.print(sumProducts);
            out.print("\n");
        }
    }
}



