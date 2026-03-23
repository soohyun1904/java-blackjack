package view;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public List<String> readNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return Arrays.stream(scanner.nextLine().split(","))
                .map(String::trim)
                .toList();
    }

    public BigDecimal readBetAmount(String name) {
        System.out.println();
        System.out.println(name + "의 배팅 금액은?");
        return new BigDecimal(scanner.nextLine().trim());
    }

    public boolean readHitDecision(String playerName) {
        System.out.println();
        System.out.println(playerName + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String input = scanner.nextLine().trim();
        if (!input.equals("y") && !input.equals("n")) {
            throw new IllegalArgumentException("y 또는 n을 입력해주세요.");
        }
        return input.equals("y");
    }
}
