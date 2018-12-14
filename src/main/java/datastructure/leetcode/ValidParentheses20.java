package datastructure.leetcode;

import java.util.Stack;

/**
 * @author: Alex
 * @date: 2018/12/13 17:29
 * description: leetcode-20.【有效的括号】
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
有效字符串需满足：
左括号必须用相同类型的右括号闭合。
左括号必须以正确的顺序闭合。
注意空字符串可被认为是有效字符串。
示例 1:
输入: "()"
输出: true
 */
public class ValidParentheses20 {

    public static boolean isValid(String a){
        char[] chars = a.toCharArray();
        Stack<Character> stack = new Stack<>();
        for(char c : chars){
            if(c == '(' || c == '[' || c == '{'){
                stack.push(c);
            }else{
                if(stack.isEmpty()){
                    return false;
                }
                char s = stack.pop();
                if(s == '[' && c != ']'){
                    return false;
                }else if(s == '(' && c != ')'){
                    return false;
                }else if(s == '{' && c != '}'){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
