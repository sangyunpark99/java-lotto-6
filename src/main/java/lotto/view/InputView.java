package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String PURCHASE_MONEY = "구입금액을 입력해 주세요.";
    private static final String WON_NUMBER = "당첨번호를 입력해 주세요.";

    public static String purchaseMoney(){
        System.out.println(PURCHASE_MONEY);
        return Console.readLine();
    }

    public static String wonNumber(){
        System.out.println(WON_NUMBER);
        return Console.readLine();
    }
}
