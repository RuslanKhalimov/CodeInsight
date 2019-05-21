package parser;

import structure.*;

import java.text.ParseException;
import java.util.*;

public class Parser {
    private final Set<Token> operatorTokens = new HashSet<>(Arrays.asList(
            Token.PLUS,
            Token.MINUS,
            Token.MULTIPLY,
            Token.DIVIDE,
            Token.MOD,
            Token.LESS,
            Token.GREATER,
            Token.EQUAL
    ));

    public Program parse(String input) throws ParseException {
        String[] inputLines = input.split("\n");
        Map<String, Map<Integer, FunctionDefinition>> functionDefinitions = new HashMap<>();
        for (int i = 0; i < inputLines.length - 1; i++) {
            FunctionDefinition functionDefinition = parseFunctionDefinition(inputLines[i], i + 1);
            String identifier = functionDefinition.getIdentifier().getName();
            if (!functionDefinitions.containsKey(identifier)) {
                functionDefinitions.put(identifier, new HashMap<>());
            }
            functionDefinitions.get(identifier).put(functionDefinition.getParameterList().size(), functionDefinition);

        }
        Expression expression = parseExpression(new Lexer(inputLines[inputLines.length - 1]), inputLines.length);
        return new Program(functionDefinitions, expression);
    }

    private String checkCurToken(Lexer lexer, Set<Token> expectedTokens, int lineNumber) throws ParseException {
        if (expectedTokens.contains(lexer.getCurToken())) {
            throw new ParseException("Unexpected token at position" + lexer.getCurPos(), lineNumber);
        }
        String res = lexer.getCurTokenString();
        lexer.nextToken();
        return res;
    }

    private String checkCurToken(Lexer lexer, Token expectedToken, int lineNumber) throws ParseException {
        return checkCurToken(lexer, Collections.singleton(expectedToken), lineNumber);
    }

    private FunctionDefinition parseFunctionDefinition(String input, int lineNumber) throws ParseException {
        Lexer lexer = new Lexer(input);
        lexer.nextToken();
        Identifier identifier = new Identifier(checkCurToken(lexer, Token.IDENTIFIER, lineNumber));

        checkCurToken(lexer, Token.LPAR, lineNumber);
        List<Identifier> parameterList = new ArrayList<>();
        parameterList.add(new Identifier(checkCurToken(lexer, Token.IDENTIFIER, lineNumber)));
        while (lexer.getCurToken() == Token.COMMA) {
            lexer.nextToken();
            parameterList.add(new Identifier(checkCurToken(lexer, Token.IDENTIFIER, lineNumber)));
        }
        checkCurToken(lexer, Token.RPAR, lineNumber);
        checkCurToken(lexer, Token.EQUAL, lineNumber);
        checkCurToken(lexer, Token.LCPAR, lineNumber);

        Expression expression = parseExpression(lexer, lineNumber);

        checkCurToken(lexer, Token.RCPAR, lineNumber);
        return new FunctionDefinition(identifier, parameterList, expression);
    }

    private Expression parseExpression(Lexer lexer, int lineNumber) throws ParseException {
        Token curToken = lexer.getCurToken();
        int curPos = lexer.getCurPos();
        String curTokenString = lexer.getCurTokenString();
        lexer.nextToken();
        if (curToken == Token.IDENTIFIER) {
            Identifier identifier = new Identifier(curTokenString);
            if (lexer.getCurToken() == Token.LPAR) {
                List<Expression> argumentList = parseArgumentList(lexer, lineNumber);
                checkCurToken(lexer, Token.RPAR, lineNumber);
                return new CallExpression(identifier, argumentList);
            }
            return identifier;
        } else if (curToken == Token.MINUS) {
            return new ConstantExpression(-Integer.parseInt(curTokenString));
        } else if (curToken == Token.NUMBER) {
            return new ConstantExpression(Integer.parseInt(curTokenString));
        } else if (curToken == Token.LPAR) {
            Expression left = parseExpression(lexer, lineNumber);
            String operation = checkCurToken(lexer, operatorTokens, lineNumber);
            Expression right = parseExpression(lexer, lineNumber);
            checkCurToken(lexer, Token.RPAR, lineNumber);
            return new BinaryExpression(left, right, operation);
        } else if (curToken == Token.LSQPAR) {
            Expression condition = parseExpression(lexer, lineNumber);
            checkCurToken(lexer, Token.RSQPAR, lineNumber);
            checkCurToken(lexer, Token.QUERY, lineNumber);
            checkCurToken(lexer, Token.LCPAR, lineNumber);

            Expression thenClause = parseExpression(lexer, lineNumber);
            checkCurToken(lexer, Token.RCPAR, lineNumber);
            checkCurToken(lexer, Token.COLON, lineNumber);
            checkCurToken(lexer, Token.LCPAR, lineNumber);

            Expression elseClause = parseExpression(lexer, lineNumber);
            checkCurToken(lexer, Token.RCPAR, lineNumber);

            return new IfExpression(condition, thenClause, elseClause);
        } else {
            throw new ParseException("Unexpected token at position" + curPos, lineNumber);
        }
    }

    private List<Expression> parseArgumentList(Lexer lexer, int lineNumber) throws ParseException {
        List<Expression> result = new ArrayList<>();
        result.add(parseExpression(lexer, lineNumber));
        while (lexer.getCurToken() == Token.COMMA) {
            lexer.nextToken();
            result.add(parseExpression(lexer, lineNumber));
        }
        return result;
    }
}
