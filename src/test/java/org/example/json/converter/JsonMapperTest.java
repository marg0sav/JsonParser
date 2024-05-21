package org.example.json.converter;

import org.example.json.container.JsonContainer;
import org.example.json.lexer.Lexer;
import org.example.json.parser.Parser;
import org.example.json.test.User;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class JsonMapperTest {

    @Test
    public void testCreateSpecifiedClassFromJson() throws Exception {
        String userJsonString = "{\"name\":\"John Doe\",\"age\":30,\"email\":\"john.doe@example.com\"}";
        Lexer userLexer = new Lexer(userJsonString);
        List<String> userTokens = userLexer.tokenize();

        Parser userParser = new Parser(userTokens);
        JsonContainer.JSONObject userJson = userParser.parse();

        User user = JsonMapper.createSpecifiedClassFromJson(User.class, userJson);
        assertNotNull(user);
        assertEquals("John Doe", user.getName());
        assertEquals(30, user.getAge());
        assertEquals("john.doe@example.com", user.getEmail());
    }

    @Test
    public void testCreateMapFromJson() throws Exception {
        String userJsonString = "{\"name\":\"John Doe\",\"age\":30,\"email\":\"john.doe@example.com\"}";
        Lexer userLexer = new Lexer(userJsonString);
        List<String> userTokens = userLexer.tokenize();

        Parser userParser = new Parser(userTokens);
        JsonContainer.JSONObject userJson = userParser.parse();

        Map<String, Object> userJsonMap = JsonMapper.createMapFromJson(userJson);
        assertNotNull(userJsonMap);
        assertEquals("John Doe", userJsonMap.get("name"));
        assertEquals(30.0, userJsonMap.get("age"));
        assertEquals("john.doe@example.com", userJsonMap.get("email"));
    }

    @Test
    public void testCreateJsonFromObject() throws Exception {
        User user = new User();
        user.setName("John Doe");
        user.setAge(30);
        user.setEmail("john.doe@example.com");

        String userJsonString = JsonMapper.createJsonFromObject(user);
        assertNotNull(userJsonString);
        assertEquals("{\"name\": \"John Doe\", \"age\": 30.0, \"email\": \"john.doe@example.com\"}", userJsonString);
    }
}