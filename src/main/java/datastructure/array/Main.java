package datastructure.array;

import java.util.Stack;

/**
 * @author: Alex
 * @date: 2018/11/29 10:40
 * description:
 */
public class Main {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println("stack = " + stack);
        stack.pop();
        System.out.println("stack = " + stack);
        stack.push(4);
        System.out.println("stack = " + stack);
    }

}
