package org.example.json.lexer;

import java.util.ArrayList;
import java.util.List;

/**
 * The Lexer class tokenizes input strings into individual tokens.
 */
public class Lexer {
    private final String input;
    private int position;

    public Lexer(String input) {
        this.input = input;
        this.position = 0;
    }

    /**
     * Tokenizes the input string and returns a list of tokens.
     *
     * @return A list of tokens.
     */
    public List<String> tokenize() {
        List<String> tokens = new ArrayList<>();
        while (position < input.length()) {
            char currentChar = currentChar();
            switch (currentChar) {
                case '{':
                    tokens.add("{");
                    break;
                case '}':
                    tokens.add("}");
                    break;
                case '[':
                    tokens.add("[");
                    break;
                case ']':
                    tokens.add("]");
                    break;
                case ':':
                    tokens.add(":");
                    break;
                case ',':
                    tokens.add(",");
                    break;
                case '"':
                    tokens.add(parseString());
                    continue;
                default:
                    if (Character.isWhitespace(currentChar)) {
                        // Пропустить пробелы
                    } else if (Character.isDigit(currentChar) || currentChar == '-') {
                        tokens.add(parseNumber());
                        continue;
                    } else if (isLiteralStart(currentChar)) {
                        tokens.add(parseLiteral());
                        continue;
                    } else {
                        throw new IllegalArgumentException("Invalid character: " + currentChar);
                    }
                    break;
            }
            advance();
        }
        return tokens;
    }

    private char currentChar() {
        return input.charAt(position);
    }

    private void advance() {
        position++;
    }

    private String parseString() {
        StringBuilder sb = new StringBuilder();
        advance(); // Пропустить начальную кавычку
        while (currentChar() != '"') {
            sb.append(currentChar());
            advance();
        }
        advance(); // Пропустить конечную кавычку
        return "\"" + sb.toString() + "\"";
    }

    private String parseNumber() {
        StringBuilder sb = new StringBuilder();
        while (Character.isDigit(currentChar()) || currentChar() == '-' || currentChar() == '.') {
            sb.append(currentChar());
            advance();
        }
        return sb.toString();
    }

    private boolean isLiteralStart(char c) {
        return Character.isLetter(c);
    }

    private String parseLiteral() {
        StringBuilder sb = new StringBuilder();
        while (Character.isLetter(currentChar())) {
            sb.append(currentChar());
            advance();
        }
        return sb.toString();
    }
}