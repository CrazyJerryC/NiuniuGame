package groupId.niuniu.game.controller;

import groupId.niuniu.game.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("calculate")
    public String calculate(String number) {
        if (number != null && number.length() == 10) {
            int[] array = new int[10];
            char[] charArray = number.toCharArray();
            for (int i = 0; i < charArray.length; i++) {
                char c = charArray[i];
                if (c == '0') {
                    array[i] = 10;
                } else {
                    array[i] = Character.getNumericValue(c);
                }
            }
            return gameService.calculate(array);
        } else {
            return "请输入10位数字，你输入了" + (number == null ? 0 : number.length()) + "位";
        }
    }


    @GetMapping("check")
    public String check() {
        return "success";
    }

}
