import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {


        Scanner scanner = new Scanner(System.in);
        String[] products = {"Хлеб", "Яблоки", "Молоко"};
        int[] prices = {100, 200, 300};

        Basket basket = new Basket(prices, products);
        File file = new File("basket.txt");
        if (file.exists()) {
            Basket.loadFromTxtFile(file);
            basket.printCart();
        }

        System.out.println("Список возможных товаров: ");

        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + " " + (products[i]) + " " + (prices[i]) + " " + "руб/шт");
        }

        while (true) {
            System.out.println("Выберите товар и количество или введите `end`");
            String input = scanner.nextLine();
            if ("end".equals(input)) {
                break;
            }

            String[] parts = input.split(" ");
            int productNumber = Integer.parseInt(parts[0]) - 1;
            int productCount = Integer.parseInt(parts[1]);
            basket.addToCart(productNumber, productCount);

        }
        basket.saveTxt(file);
        basket.printCart();
    }

}


