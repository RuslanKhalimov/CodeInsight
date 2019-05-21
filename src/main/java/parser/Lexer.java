package parser;

import exceptions.SyntaxError;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
    private String input;
    private int curPos;
    private Token curToken;
    private Map<Token, Pattern> regex;
    private Matcher matcher;

    public Lexer(String input) {
        this.input = input;
        initRegex();
        matcher = Pattern.compile("").matcher(input);
    }

    private void initRegex() {
        regex = new HashMap<>();
        regex.put(Token.IDENTIFIER, Pattern.compile("[a-zA-Z_]+"));
        regex.put(Token.NUMBER, Pattern.compile("[0-9]+"));
        regex.put(Token.PLUS, Pattern.compile("\\+"));
        regex.put(Token.MINUS, Pattern.compile("-"));
        regex.put(Token.MULTIPLY, Pattern.compile("\\*"));
        regex.put(Token.DIVIDE, Pattern.compile("/"));
        regex.put(Token.MOD, Pattern.compile("%"));
        regex.put(Token.LESS, Pattern.compile("<"));
        regex.put(Token.GREATER, Pattern.compile(">"));
        regex.put(Token.EQUAL, Pattern.compile("="));
        regex.put(Token.LPAR, Pattern.compile("\\("));
        regex.put(Token.RPAR, Pattern.compile("\\)"));
        regex.put(Token.LCPAR, Pattern.compile("\\{"));
        regex.put(Token.RCPAR, Pattern.compile("}"));
        regex.put(Token.LSQPAR, Pattern.compile("\\["));
        regex.put(Token.RSQPAR, Pattern.compile("]"));
        regex.put(Token.QUERY, Pattern.compile("\\?"));
        regex.put(Token.COLON, Pattern.compile(":"));
        regex.put(Token.COMMA, Pattern.compile(","));
        regex.put(Token.ENDLINE, Pattern.compile("\n"));
        regex.put(Token.END, Pattern.compile("$"));
    }

    private boolean findNextToken() {
        for (Token t : Token.values()) {
            matcher.usePattern(regex.get(t));
            matcher.region(curPos, input.length());
            if (matcher.lookingAt()) {
                curToken = t;
                curPos += matcher.end() - matcher.start();
                return true;
            }
        }
        return false;
    }

    public void nextToken() throws SyntaxError {
        if (curPos == input.length()) {
            curToken = Token.END;
            return;
        }
        if (!findNextToken()) {
            throw new SyntaxError();
        }
    }
    public Token getCurToken() {
        return curToken;
    }

    public int getCurPos() {
        return curPos;
    }

    public String getCurTokenString() {
        return matcher.group();
    }
}
