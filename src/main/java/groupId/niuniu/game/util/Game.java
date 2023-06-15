package groupId.niuniu.game.util;

import java.util.Arrays;

public class Game {
    Card[] cards;

    public Game(Card[] cards) {
        this.cards = cards;
        Arrays.sort(cards);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Arrays.equals(cards, game.cards);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(cards);
    }

    public Card[] getCards() {
        return cards;
    }

    public void setCards(Card[] cards) {
        this.cards = cards;
    }

    @Override
    public String toString() {
        return "第一道:" + cards[0] + " |  第二道:" + cards[1];
    }
}
