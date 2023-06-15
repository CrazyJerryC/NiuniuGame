package groupId.niuniu.game.service;

import groupId.niuniu.game.util.Card;
import groupId.niuniu.game.util.Game;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

@Service
public class GameService {


    public String calculate(int[] array) {

        System.out.println("传入参数：" + Arrays.toString(array));

        StringBuilder sb = new StringBuilder();

        Arrays.sort(array);
        Set<Game> gameSet = new HashSet<>();

        PriorityQueue<Game> firstQueue = new PriorityQueue<>((game1, game2) -> {
            // 自定义比较逻辑，按照第一道大小降序排列(如果第一道score一样，则比较第二道)
            return Integer.compare(game2.getCards()[0].getScore() * 1000 + game2.getCards()[1].getScore(),
                    game1.getCards()[0].getScore() * 1000 + game1.getCards()[1].getScore());
        });

        PriorityQueue<Game> secondQueue = new PriorityQueue<>((game1, game2) -> {
            // 自定义比较逻辑，按照第二道大小降序排列(如果第二道score一样，则比较第一道)
            return Integer.compare(game2.getCards()[1].getScore() * 1000 + game2.getCards()[0].getScore(),
                    game1.getCards()[1].getScore() * 1000 + game1.getCards()[0].getScore());
        });

        PriorityQueue<Game> sumQueue = new PriorityQueue<>((game1, game2) -> {
            // 自定义比较逻辑，按照总和降序排列
            return Integer.compare(game2.getCards()[0].getScore() + game2.getCards()[1].getScore(),
                    game1.getCards()[0].getScore() + game1.getCards()[1].getScore());
        });

        PriorityQueue<Game> timeQueue = new PriorityQueue<>((game1, game2) -> {
            // 自定义比较逻辑，按照倍数和降序排列
            return Integer.compare(game2.getCards()[0].getCardType().getTimes() + game2.getCards()[1].getCardType().getTimes(),
                    game1.getCards()[0].getCardType().getTimes() + game1.getCards()[1].getCardType().getTimes());
        });



        // 遍历所有可能的组合
        for (int i = 0; i < array.length - 4; i++) {
            for (int j = i + 1; j < array.length - 3; j++) {
                for (int k = j + 1; k < array.length - 2; k++) {
                    for (int m = k + 1; m < array.length - 1; m++) {
                        for (int n = m + 1; n < array.length; n++) {
                            // 分组
                            int[] group1 = {array[i], array[j], array[k], array[m], array[n]};
                            int[] group2 = new int[array.length - 5];
                            int group2Index = 0;

                            // 将剩余的元素放入group2数组
                            for (int p = 0; p < array.length; p++) {
                                if (p != i && p != j && p != k && p != m && p != n) {
                                    group2[group2Index] = array[p];
                                    group2Index++;
                                }
                            }

                            Game game = new Game(new Card[]{new Card(group1), new Card(group2)});
                            if (!gameSet.contains(game)){
                                firstQueue.offer(game);
                                secondQueue.offer(game);
                                sumQueue.offer(game);
                                timeQueue.offer(game);
                                gameSet.add(game);
                            }

                        }
                    }
                }
            }
        }


//        System.out.println("所有排列组合数量: " + gameSet.size() + "\n");
//        System.out.println("第一道最大的组合: " + firstQueue.peek() + "\n");
//        System.out.println("第二道最大的组合: " + secondQueue.peek() + "\n");
//        System.out.println("总和最大的组合: " + sumQueue.peek() + "\n");
//        System.out.println("倍数之和最大的组合: " + timeQueue.peek() + "\n");
        sb.append("第一道最大的组合: " + firstQueue.peek() + "\n\n");
        sb.append("第二道最大的组合: " + secondQueue.peek() + "\n\n");
        sb.append("总和最大的组合: " + sumQueue.peek() + "\n\n");
        sb.append("倍数之和最大的组合: " + timeQueue.peek() + "\n");

        return sb.toString();
    }
}
