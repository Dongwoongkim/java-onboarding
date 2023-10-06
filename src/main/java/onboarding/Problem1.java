package onboarding;

import javax.imageio.stream.ImageInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

class Problem1 {

    private static final Integer FIRST_PAGE = 1;
    private static final Integer LAST_PAGE = 400;

    private final List<Integer> pobi = new ArrayList<>();
    private final List<Integer> crong = new ArrayList<>();

    public static int solution(List<Integer> pobi, List<Integer> crong) {
        int answer = Integer.MAX_VALUE;

        if (!(validate(pobi) && validate(crong))) {
            return -1;
        }

        int pobiScore = getScore(pobi);
        int crongScore = getScore(crong);
        answer = getResult(pobiScore, crongScore);
        return answer;
    }

    private static int getScore(List<Integer> person) {
        int[] leftNum = Stream.of(String.valueOf(person.get(0)).split("")).mapToInt(Integer::parseInt).toArray();
        int[] rightNum = Stream.of(String.valueOf(person.get(1)).split("")).mapToInt(Integer::parseInt).toArray();

        return Math.max(getMaxScore(leftNum), getMaxScore(rightNum));
    }

    private static int getMaxScore(int[] numbers) {
        int sum = 0;
        int sum1 = 1;

        for (int number : numbers) {
            sum += number;
            sum1 *= number;
        }
        return Math.max(sum, sum1);
    }

    private static int getResult(int pobiScore, int crongScore) {
        if (pobiScore > crongScore) {
            return 1;
        } else if (pobiScore < crongScore) {
            return 2;
        }
        return 0;
    }

    /**
     * 입력 값 검증 기능
     * 1. validateRange(value) : 범위(1~400) 안에 있는 지 검사
     * 2. validateOneDifference(left, right) : 왼쪽과 오른쪽 값의 차가 1인지 검사
     * 3. validateOddLeftEvenRight(left, right) : 왼쪽값이 홀수, 오른쪽값이 짝수인지 검사
     */
    private static boolean validate(List<Integer> person) {
        int left = person.get(0);
        int right = person.get(1);

        return (validateRange(left) && validateRange(right))
                && validateOneDifference(left, right) && validateOddLeftEvenRight(left, right);
    }

    private static boolean validateRange(int page) {
        if (1 <= page && page <= 400) {
            return true;
        }
        return false;
    }
    private static boolean validateOneDifference(int left, int right) {
        if (right - left == 1) {
            return true;
        }
        return false;
    }

    private static boolean validateOddLeftEvenRight(int left, int right) {
        if(left % 2 == 1 && right % 2 == 0) return true;
        return false;
    }
}