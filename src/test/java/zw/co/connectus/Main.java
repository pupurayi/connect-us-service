package zw.co.connectus;

import zw.co.connectus.dal.entity.Product;

class Main {

    public static void main(String[] args) {
        float v = calculateRating(null);
        System.out.println(v);
    }


    public static float calculateRating(Product product) {

        float v = getRandomNumber(2, 5) + (getRandomNumber(0, 9) / 10);
        return v > 5 ? 5 : v;
    }

    public static float getRandomNumber(int min, int max) {
        return (float) ((Math.random() * (max - min)) + min);
    }
}
