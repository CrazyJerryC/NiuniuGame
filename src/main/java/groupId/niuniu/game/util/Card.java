package groupId.niuniu.game.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Card implements Comparable<Card> {
    int[] values;
    CardType cardType;
    int score;

    public Card(int[] values) {
        this.values = values;
        Arrays.sort(this.values);
        this.score = values[values.length - 1];
        cardType = CardType.NO_NIU;

        if (findFourSameNumber(this.values) > 0) {
            cardType = CardType.NIU_SITIAO;
            score = cardType.getScore() + findFourSameNumber(this.values);

        } else if (findThreeSameAndTwoSameNumber(this.values) > 0) {
            cardType = CardType.NIU_HULU;
            score = cardType.getScore() + findThreeSameAndTwoSameNumber(this.values);

        } else if (isConsecutive(this.values)) {
            cardType = CardType.NIU_SHUN;
            if (Arrays.equals(this.values, new int[]{1, 2, 3, 4, 10})) {
                score = cardType.getScore() + 4;
            } else {
                score = cardType.getScore() + values[4];
            }
        } else {
            // 遍历所有可能的组合
            for (int i = 0; i < values.length - 2; i++) {
                for (int j = i + 1; j < values.length - 1; j++) {
                    for (int k = j + 1; k < values.length; k++) {
                        // 获取选中的三个元素
                        int num1 = values[i];
                        int num2 = values[j];
                        int num3 = values[k];

                        // 获取剩余的两个元素
                        int[] remainingArr = getRemainingElements(values, i, j, k);

                        // 输出结果
//                    System.out.println("Combination: " + num1 + ", " + num2 + ", " + num3);
//                    System.out.println("Remaining: " + remainingArr[0] + ", " + remainingArr[1]);
//                    System.out.println("--------------------");

                        if ((num1 + num2 + num3) % 10 == 0) {
                            int r1 = remainingArr[0];
                            int r2 = remainingArr[1];
                            if (r1 == r2 && r1 != 5 && r1 != 10) {
                                int tmpScore = CardType.NIU_DUI.getScore() + r1;
                                if (tmpScore > score) {
                                    this.score = tmpScore;
                                    cardType = CardType.NIU_DUI;
                                }
                                continue;
                            }

                            CardType tmpType = getCardTypeByMod((r1 + r2) % 10);
                            int tmpScore = tmpType.getScore() + Math.max(r1, r2);
                            //牛牛的情况下(5,5)或者(10,10)单独计算处理
                            if (CardType.NIU_10.equals(tmpType)) {
                                if (r1 == 5) {
                                    tmpScore = tmpType.getScore() + 10;
                                } else if (r1 == 10) {
                                    tmpScore = tmpType.getScore() + 11;
                                }
                            }
                            if (tmpScore > score) {
                                this.score = tmpScore;
                                cardType = tmpType;
                            }
                        }
                    }
                }
            }


        }

    }

    //判断是否是4+1
    private static int findFourSameNumber(int[] arr) {
        int[] count = new int[11]; // 由于数字的范围是1到10，所以创建长度为11的计数数组

        for (int num : arr) {
            count[num]++;
            if (count[num] == 4) {
                return num;
            }
        }

        return -1;
    }

    //判断是否是3+2
    private static int findThreeSameAndTwoSameNumber(int[] arr) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : arr) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);

        }

        if (countMap.size() == 2) {
            for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
                if (entry.getValue() == 3) return entry.getKey();
            }
        }

        return -1;
    }

    //判断是否是顺子
    private static boolean isConsecutive(int[] arr) {
        if (Arrays.equals(arr, new int[]{1, 2, 3, 4, 10})) {
            return true;
        }

        // 检查相邻元素是否连续递增
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i + 1] - arr[i] != 1) {
                return false;
            }
        }

        return true;
    }

    // 获取剩余的元素
    private static int[] getRemainingElements(int[] array, int index1, int index2, int index3) {
        int[] remainingElements = new int[array.length - 3];
        int remainingIndex = 0;

        for (int i = 0; i < array.length; i++) {
            if (i != index1 && i != index2 && i != index3) {
                remainingElements[remainingIndex] = array[i];
                remainingIndex++;
            }
        }

        return remainingElements;
    }

    // 根据余数获取对应的牌型
    private static CardType getCardTypeByMod(int mod) {
        switch (mod) {
            case 0:
                return CardType.NIU_10;
            case 1:
                return CardType.NIU_1;
            case 2:
                return CardType.NIU_2;
            case 3:
                return CardType.NIU_3;
            case 4:
                return CardType.NIU_4;
            case 5:
                return CardType.NIU_5;
            case 6:
                return CardType.NIU_6;
            case 7:
                return CardType.NIU_7;
            case 8:
                return CardType.NIU_8;
            case 9:
                return CardType.NIU_9;
        }
        return CardType.NO_NIU;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Arrays.equals(values, card.values);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(values);
    }

    @Override
    public int compareTo(Card o) {
        return score - o.getScore();
    }

    public int[] getValues() {
        return values;
    }

    public void setValues(int[] values) {
        this.values = values;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "(" + cardType.getType() + "-" + cardType.getTimes() + "倍) " + Arrays.toString(values) + ", score=" + score;
    }
}
