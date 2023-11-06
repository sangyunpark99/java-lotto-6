package lotto.controller;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.LottoCount;
import lotto.domain.Profit;
import lotto.domain.PurchaseMoney;
import lotto.domain.WinningDetails;
import lotto.utils.GeneratedLottoNumber;
import lotto.view.InputView;
import lotto.view.OutputView;

public class GameController {

    private static List<Lotto> lottos;
    private static PurchaseMoney purchaseMoney;
    private static Lotto winningNumbers;
    private static BonusNumber bonusNumber;
    private static WinningDetails winningDetails;
    private static Profit profit;

    public static void start(){
        try{
            purchaseLotto();
            setWinningNumbers();
            setBonusNumbers();
            checkWinningDetails();
            calculateProfit();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    private static void purchaseLotto(){
        purchaseMoney = new PurchaseMoney(inputPurchaseMoney()); // 구매 금액 입력
        System.out.println();

        LottoCount lottoCount = new LottoCount(purchaseMoney.getValue()); // 구매 금액 -> 로또 갯수 변환
        outputLottoCount(lottoCount.getValue());
        generateLottos(lottoCount.getValue()); // 로또 번호 생성
        outputLottos();
        System.out.println();
    }

    private static void setWinningNumbers(){
        winningNumbers = new Lotto(inputWinningNumber()); // 당첨 번호 입력
        System.out.println();
    }

    private static void setBonusNumbers(){
        bonusNumber = new BonusNumber(inputBonusNumber(),winningNumbers); // 보너스 번호 입력 받기
        System.out.println();
    }

    private static void checkWinningDetails(){
        winningDetails = new WinningDetails(lottos,winningNumbers.getNumbers(),bonusNumber.getValue()); //당첨 내역
        OutputView.showWinningDetails(winningDetails); // 당첨 내역 출력
    }

    private static void calculateProfit(){
        profit = new Profit(winningDetails.getRank());
        OutputView.earningRate(profit.getTotalEarningRate(purchaseMoney.getValue()));
    }

    private static String inputPurchaseMoney(){
        return InputView.purchaseMoney();
    }

    private static String inputWinningNumber(){
        return InputView.winningNumber();
    }

    private static String inputBonusNumber(){
        return InputView.bonusNumber();
    }

    private static void outputLottoCount(int lottoCount) {
        OutputView.purchaseLotto(lottoCount);
    }

    private static void outputLottos(){
        OutputView.showEachLotto(lottos);
    }

    private static void generateLottos(int lottoCount){
        lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            lottos.add(new Lotto(GeneratedLottoNumber.getRandomNumber()));
        }
    }
}
