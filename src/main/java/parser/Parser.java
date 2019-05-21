package parser;

import exceptions.SyntaxError;
import structure.*;

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

    public Program parse(String input) throws SyntaxError {
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
        Lexer lexer = new Lexer(inputLines[inputLines.length - 1]);
        lexer.nextToken();
        Expression expression = parseExpression(lexer, inputLines.length);
        checkCurToken(lexer, Token.END);
        return new Program(functionDefinitions, expression, inputLines.length);
    }

    private String checkCurToken(Lexer lexer, Set<Token> expectedTokens) throws SyntaxError {
        if (!expectedTokens.contains(lexer.getCurToken())) {
            throw new SyntaxError();
        }
        String res = lexer.getCurTokenString();
        lexer.nextToken();
        return res;
    }

    private String checkCurToken(Lexer lexer, Token expectedToken) throws SyntaxError {
        return checkCurToken(lexer, Collections.singleton(expectedToken));
    }

    private FunctionDefinition parseFunctionDefinition(String input, int lineNumber) throws SyntaxError {
        Lexer lexer = new Lexer(input);
        lexer.nextToken();
        Identifier identifier = new Identifier(checkCurToken(lexer, Token.IDENTIFIER));

        checkCurToken(lexer, Token.LPAR);
        List<Identifier> parameterList = new ArrayList<>();
        parameterList.add(new Identifier(checkCurToken(lexer, Token.IDENTIFIER)));
        while (lexer.getCurToken() == Token.COMMA) {
            lexer.nextToken();
            parameterList.add(new Identifier(checkCurToken(lexer, Token.IDENTIFIER)));
        }
        checkCurToken(lexer, Token.RPAR);
        checkCurToken(lexer, Token.EQUAL);
        checkCurToken(lexer, Token.LCPAR);

        Expression expression = parseExpression(lexer, lineNumber);

        checkCurToken(lexer, Token.RCPAR);
        return new FunctionDefinition(identifier, parameterList, expression, lineNumber);
    }

    private Expression parseExpression(Lexer lexer, int lineNumber) throws SyntaxError {
        Token curToken = lexer.getCurToken();
        String curTokenString = lexer.getCurTokenString();
        lexer.nextToken();
        if (curToken == Token.IDENTIFIER) {
            Identifier identifier = new Identifier(curTokenString);
            if (lexer.getCurToken() == Token.LPAR) {
                lexer.nextToken();
                List<Expression> argumentList = parseArgumentList(lexer, lineNumber);
                checkCurToken(lexer, Token.RPAR);
                return new CallExpression(identifier, argumentList);
            }
            return identifier;
        } else if (curToken == Token.MINUS) {
            return new ConstantExpression(-Integer.parseInt(curTokenString));
        } else if (curToken == Token.NUMBER) {
            return new ConstantExpression(Integer.parseInt(curTokenString));
        } else if (curToken == Token.LPAR) {
            Expression left = parseExpression(lexer, lineNumber);
            String operation = checkCurToken(lexer, operatorTokens);
            Expression right = parseExpression(lexer, lineNumber);
            checkCurToken(lexer, Token.RPAR);
            return new BinaryExpression(left, right, operation);
        } else if (curToken == Token.LSQPAR) {
            Expression condition = parseExpression(lexer, lineNumber);
            checkCurToken(lexer, Token.RSQPAR);
            checkCurToken(lexer, Token.QUERY);
            checkCurToken(lexer, Token.LCPAR);

            Expression thenClause = parseExpression(lexer, lineNumber);
            checkCurToken(lexer, Token.RCPAR);
            checkCurToken(lexer, Token.COLON);
            checkCurToken(lexer, Token.LCPAR);

            Expression elseClause = parseExpression(lexer, lineNumber);
            checkCurToken(lexer, Token.RCPAR);

            return new IfExpression(condition, thenClause, elseClause);
        } else {
            throw new SyntaxError();
        }
    }

    private List<Expression> parseArgumentList(Lexer lexer, int lineNumber) throws SyntaxError {
        List<Expression> result = new ArrayList<>();
        result.add(parseExpression(lexer, lineNumber));
        while (lexer.getCurToken() == Token.COMMA) {
            lexer.nextToken();
            result.add(parseExpression(lexer, lineNumber));
        }
        return result;
    }
}
