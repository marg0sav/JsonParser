package org.example.json.parser;

import org.example.json.container.JsonContainer;
import org.example.json.container.JsonContainer.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * The Parser class parses a list of tokens into a JSON object.
 */
public class Parser {
    private final List<String> tokens;
    private int currentPosition;

    public Parser(List<String> tokens) {
        this.tokens = tokens;
        this.currentPosition = 0;
    }

    /**
     * Parses the list of tokens into a JSONObject.
     *
     * @return The parsed JSONObject.
     * @throws IllegalArgumentException if the input tokens are not valid JSON.
     */
    public JSONObject parse() {
        if (tokens.get(currentPosition).equals("{")) {
            return parseObject();
        } else {
            throw new IllegalArgumentException("Invalid JSON input");
        }
    }

    private JSONObject parseObject() {
        Map<String, JsonContainer.JSONValue> map = new HashMap<>();
        consume("{");

        while (!currentToken().equals("}")) {
            String key = parseString();
            consume(":");
            JsonContainer.JSONValue value = parseValue();
            map.put(key, value);

            if (currentToken().equals(",")) {
                consume(",");
            }
        }

        consume("}");
        return new JSONObject(map);
    }

    private JsonContainer.JSONValue parseValue() {
        String token = currentToken();
        if (token.equals("{")) {
            return parseObject();
        } else if (token.equals("[")) {
            return parseArray();
        } else if (token.startsWith("\"")) {
            return new JsonContainer.JSONString(parseString());
        } else if (token.equals("true") || token.equals("false")) {
            JsonContainer.JSONBoolean boolValue = new JsonContainer.JSONBoolean(Boolean.parseBoolean(token));
            consume(token); // Add this line to consume the boolean token
            return boolValue;
        } else if (token.equals("null")) {
            consume("null");
            return new JsonContainer.JSONNull();
        } else {
            JsonContainer.JSONNumber numberValue = new JsonContainer.JSONNumber(Double.parseDouble(token));
            consume(token); // Add this line to consume the number token
            return numberValue;
        }
    }

    private JsonContainer.JSONArray parseArray() {
        List<JsonContainer.JSONValue> array = new ArrayList<>();
        consume("[");

        while (!currentToken().equals("]")) {
            array.add(parseValue());
            if (currentToken().equals(",")) {
                consume(",");
            }
        }

        consume("]");
        return new JsonContainer.JSONArray(array);
    }

    private String parseString() {
        String token = currentToken();
        consume(token);
        return token.substring(1, token.length() - 1); // Удаление кавычек
    }

    private String currentToken() {
        return tokens.get(currentPosition);
    }

    private void consume(String expectedToken) {
        if (!currentToken().equals(expectedToken)) {
            throw new IllegalArgumentException("Expected token " + expectedToken + " but found " + currentToken());
        }
        currentPosition++;
    }
}
