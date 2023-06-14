public enum CardType {
    NIU_SITIAO("四条", 150,9), // 四条
    NIU_HULU("葫芦", 140,8), // 葫芦
    NIU_SHUN("牛顺", 130,7), // 牛顺
    NIU_10("牛牛", 110,6), // 牛牛
    NIU_DUI("牛对", 100,5), // 牛对
    NIU_9("牛九", 90,4), // 牛九
    NIU_8("牛八", 80,3), // 牛八
    NIU_7("牛七", 70,2), // 牛七
    NIU_6("牛六", 60,1), // 牛六
    NIU_5("牛五", 50,1), // 牛五
    NIU_4("牛四", 40,1), // 牛四
    NIU_3("牛三", 30,1), // 牛三
    NIU_2("牛二", 20,1), // 牛二
    NIU_1("牛一", 10,1), // 牛一
    NO_NIU("无牛", 0,1) // 没有牛
    ;


    private String type;
    private int score;
    private int times;

    CardType(String type, int score, int times) {
        this.type = type;
        this.score = score;
        this.times = times;
    }

    public String getType() {
        return type;
    }

    public int getScore() {
        return score;
    }

    public int getTimes() {
        return times;
    }
}
