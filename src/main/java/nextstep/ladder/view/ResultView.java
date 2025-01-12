package nextstep.ladder.view;

import nextstep.ladder.domain.*;
import nextstep.ladder.view.dto.RewardDto;
import nextstep.ladder.view.dto.RewardsDto;

import java.util.List;

public class ResultView {

    private static final String CONNECTED = "|-----";
    private static final String NOT_CONNECTED = "|     ";
    private static final String NAME_OUTPUT_FORMAT = "%3s";
    private static final String REWARD_OUTPUT_FORMAT = "%3s";
    private static final String SHOW_RESULT_MESSAGE = "\n실행 결과";

    private ResultView() {
    }

    public static void showPlayers(List<String> players) {
        for (String name : players) {
            System.out.printf(NAME_OUTPUT_FORMAT, name);
        }
        System.out.println();
    }

    public static void showLadder(Ladder ladder, Rewards rewards) {
        ladder.lines()
                .forEach(ResultView::showLine);
        rewards.readOnlyRewards()
                .stream()
                .map(ResultView::convertRewardFormat)
                .forEach(System.out::print);
        System.out.println("\n");
    }

    private static void showLine(Line line) {
        line.points()
                .stream()
                .map(ResultView::convertPoint)
                .forEach(System.out::print);
        System.out.println();
    }

    private static String convertPoint(Point point) {
        return point.isRight() ? CONNECTED : NOT_CONNECTED;
    }

    public static void showResultOfPlayer(RewardDto reward) {
        System.out.println(SHOW_RESULT_MESSAGE);
        System.out.println(reward.getReward());
        System.out.println();
    }

    public static void showResultOfAll(RewardsDto rewardsDto) {
        System.out.println(SHOW_RESULT_MESSAGE);
        rewardsDto.readRewards()
                .forEach(System.out::println);
    }

    private static String convertRewardFormat(Reward r) {
        return String.format(REWARD_OUTPUT_FORMAT, r.getReward());
    }
}
