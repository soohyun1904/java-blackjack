package domain;

public enum Suit {
    DIAMOND("다이아몬드"),
    SPADE("스페이드"),
    COLVER("클로버"),
    HEART("하트");

    private final String koreanName;

    Suit(String koreanName) {
        this.koreanName = koreanName;
    }

    public String getKoreanName() {
        return koreanName;
    }
}
