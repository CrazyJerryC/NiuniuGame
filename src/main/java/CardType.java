public enum CardType {
    NIU_SHUN("牛顺", 120), // 牛顺
    NIU_10("牛牛", 110), // 牛牛
    NIU_DUI("牛对",100), // 牛对
    NIU_9("牛九",90), // 牛九
    NIU_8("牛八",80), // 牛八
    NIU_7("牛七",70), // 牛七
    NIU_6("牛六",60), // 牛六
    NIU_5("牛五",50), // 牛五
    NIU_4("牛四",40), // 牛四
    NIU_3("牛三",30), // 牛三
    NIU_2("牛二",20), // 牛二
    NIU_1("牛一",10), // 牛一
    NO_NIU("无牛",0) // 没有牛
    ;


    private String type;
    private int score;

    CardType(String type, int score) {
        this.type = type;
        this.score = score;
    }

    public String getType() {
        return type;
    }

    public int getScore() {
        return score;
    }

}
