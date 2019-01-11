package algorithm;

/**
 * @author: Alex
 * @date: 2018/12/24 16:15
 * description:
 */
public class Test {

    public static void main(String[] args) {
        int n = 50;
        int[] ints = new int[100];
        ints[ints.length - 1] = 1;
        for (int i = n, j = 1; i > 0; i--, j++) {
            ints = algo(ints, j);
        }
        int index = 0;
        for (int i = 0; i < ints.length; i++) {
            if (ints[i] != 0) {
                index = i;
                break;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = index; i < ints.length; i++) {
            sb.append(ints[i]);
        }
        System.out.println(sb.toString());
    }

    public static int[] algo(int[] ints, int num) {
        for (int i = ints.length - 1; i > 0; i--) {
            ints[i] = ints[i] * num;
        }

        for (int i = ints.length - 1; i > 0; i--) {
            ints[i - 1] = ints[i - 1] + ints[i] / 10;
            ints[i] = ints[i] % 10;
        }

        return ints;
    }

}
