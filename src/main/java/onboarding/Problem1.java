package onboarding;

import javax.imageio.stream.ImageInputStream;
import java.util.ArrayList;
import java.util.Arrays;
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

        answer = getResult(getScore(pobi), getScore(crong));
        return answer;
    }

    private static boolean validate(List<Integer> person) {
        int left = person.get(0);
        int right = person.get(1);

        return (validateRange(left) && validateRange(right))
                && validateOneDifference(left, right) && validateOddLeftEvenRight(left, right);
    }

    private static int getResult(int pobiScore, int crongScore) {
        if (pobiScore > crongScore) {
            return 1;
        } else if (pobiScore < crongScore) {
            return 2;
        }
        return 0;
    }

    private static int getScore(List<Integer> person) {
        int[] leftNum = Stream.of(String.valueOf(person.get(0)).split("")).mapToInt(Integer::parseInt).toArray();
        int[] rightNum = Stream.of(String.valueOf(person.get(1)).split("")).mapToInt(Integer::parseInt).toArray();

        return Math.max(getMaxScore(leftNum), getMaxScore(rightNum));
    }

    private static int getMaxScore(int[] numbers) {
        return Math.max(getSumOfEachNumber(numbers),getMultipleOfEachNumber(numbers));
    }

    private static int getSumOfEachNumber(int[] numbers) {
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum;
    }

    private static int getMultipleOfEachNumber(int[] numbers) {
        int mul = 1;
        for (int number : numbers) {
            mul *= number;
        }
        return mul;
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