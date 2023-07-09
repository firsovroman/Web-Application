package ru.irkagr.somesite.domen;

public enum Rank {

    ONE("ONE"),
    ONE_HALF("ONE_HALF"),
    TWO("TWO"),
    TWO_HALF("TWO_HALF"),
    THREE("THREE"),
    THREE_HALF("THREE_HALF"),
    FOUR("FOUR"),
    FOUR_HALF("FOUR_HALF"),
    FIVE("FIVE");

    public final String code;

    Rank(String code) {
        this.code = code;
    }


    public boolean isItOne(Rank rank) {
        return rank.equals(ONE);
    }

    public boolean isItOneHalf(Rank rank) {
        return rank.equals(ONE_HALF);
    }

    public boolean isItTwo(Rank rank) {
        return rank.equals(TWO);
    }

    public boolean isItTwoHalf(Rank rank) {
        return rank.equals(TWO_HALF);
    }

    public boolean isItThree(Rank rank) {
        return rank.equals(THREE);
    }

    public boolean isItThreeHalf(Rank rank) {
        return rank.equals(THREE_HALF);
    }

    public boolean isItFour(Rank rank) {
        return rank.equals(FOUR);
    }

    public boolean isItFourHalf(Rank rank) {
        return rank.equals(FOUR_HALF);
    }

    public boolean isItFive(Rank rank) {
        return rank.equals(FIVE);
    }


}
