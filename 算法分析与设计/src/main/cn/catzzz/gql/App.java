package cn.catzzz.gql;

import java.util.Random;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        for (int i = 0; i < 42; i++) {
            String s = randInt(11, 13) + "\t" + randInt(8, 9) + "\t" + randInt(2, 3) + "\t"
                    + randInt(10, 11) + "\t" + randInt(13, 14) + "\t"
                    + randInt(7, 8) + "\t" + randInt(5, 5) + "\t" + 2 + "\t"
                    + randInt(7, 8) + "\t" + randInt(11, 12) + "\t"
                    + randInt(8, 9) + "\t" + randInt(5, 6);
            System.out.println(s);
        }
    }

    public static int randInt(int begin, int end) {
        Random random = new Random();
        if (begin == end) {
            return begin;
        } else {
            return random.nextInt(end - begin + 1) + begin;
        }
    }
}
