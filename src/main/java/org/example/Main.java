package org.example;

import org.example.json.container.JsonContainer.JSONObject;
import org.example.json.converter.JsonMapper;
import org.example.json.lexer.Lexer;
import org.example.json.parser.Parser;
import org.example.json.test.University;
import org.example.json.test.User;
import org.example.json.test.ExampleClass;

import java.util.List;
import java.util.Map;

/**
 * The Main class contains examples of using the JSON parser, lexer, and converter.
 */
public class Main {
    public static void main(String[] args) {
        try {
            // Example JSON string for the User class
            String userJsonString = "{\"name\":\"John Doe\",\"age\":30,\"email\":\"john.doe@example.com\"}";
            Lexer userLexer = new Lexer(userJsonString);
            List<String> userTokens = userLexer.tokenize();
            System.out.println("User tokens: " + userTokens);

            Parser userParser = new Parser(userTokens);
            JSONObject userJson = userParser.parse();
            System.out.println("User JSON object: " + userJson);

            // Convert JSON to Java object (User)
            User user = JsonMapper.createSpecifiedClassFromJson(User.class, userJson);
            System.out.println("User object: " + user);

            // Convert JSON to Map (User)
            Map<String, Object> userJsonMap = JsonMapper.createMapFromJson(userJson);
            System.out.println("User JSON map: " + userJsonMap);

            // Convert Java object to JSON string (User)
            String userJsonStringFromObject = JsonMapper.createJsonFromObject(user);
            System.out.println(userJsonStringFromObject);

            // Example JSON string for the University class
            String universityJsonString = "{" +
                    "\"name\":\"Tech University\"," +
                    "\"faculties\":[" +
                    "{" +
                    "\"name\":\"Engineering\"," +
                    "\"departments\":[" +
                    "{" +
                    "\"name\":\"Computer Science\"," +
                    "\"professors\":[" +
                    "{\"name\":\"Dr. Alice\",\"age\":50,\"title\":\"Professor\"}," +
                    "{\"name\":\"Dr. Bob\",\"age\":45,\"title\":\"Associate Professor\"}" +
                    "]," +
                    "\"students\":[" +
                    "{\"name\":\"Charlie\",\"age\":20,\"courses\":[\"Algorithms\",\"Data Structures\"]}," +
                    "{\"name\":\"David\",\"age\":21,\"courses\":[\"Operating Systems\",\"Networks\"]}" +
                    "]" +
                    "}" +
                    "]" +
                    "}" +
                    "]" +
                    "}";
            Lexer universityLexer = new Lexer(universityJsonString);
            List<String> universityTokens = universityLexer.tokenize();
            System.out.println("University tokens: " + universityTokens);

            Parser universityParser = new Parser(universityTokens);
            JSONObject universityJson = universityParser.parse();
            System.out.println("University JSON object: " + universityJson);

            // Convert JSON to Java object (University)
            University university = JsonMapper.createSpecifiedClassFromJson(University.class, universityJson);
            System.out.println("University object: " + university);

            // Convert JSON to Map (University)
            Map<String, Object> universityJsonMap = JsonMapper.createMapFromJson(universityJson);
            System.out.println("University JSON map: " + universityJsonMap);

            // Convert Java object to JSON string (University)
            String universityJsonStringFromObject = JsonMapper.createJsonFromObject(university);
            System.out.println(universityJsonStringFromObject);

            // Example JSON string for the ExampleClass
            String exampleJsonString = "{\"isActive\":true,\"name\":\"Test Name\"}";
            Lexer exampleLexer = new Lexer(exampleJsonString);
            List<String> exampleTokens = exampleLexer.tokenize();
            System.out.println("ExampleClass tokens: " + exampleTokens);

            Parser exampleParser = new Parser(exampleTokens);
            JSONObject exampleJson = exampleParser.parse();
            System.out.println("ExampleClass JSON object: " + exampleJson);

            // Convert JSON to Java object (ExampleClass)
            ExampleClass example = JsonMapper.createSpecifiedClassFromJson(ExampleClass.class, exampleJson);
            System.out.println("ExampleClass object: " + example);

            // Convert JSON to Map (ExampleClass)
            Map<String, Object> exampleJsonMap = JsonMapper.createMapFromJson(exampleJson);
            System.out.println("ExampleClass JSON map: " + exampleJsonMap);

            // Convert Java object to JSON string (ExampleClass)
            String exampleJsonStringFromObject = JsonMapper.createJsonFromObject(example);
            System.out.println(exampleJsonStringFromObject);

            // Example JSON string for the ExampleClass with an empty name
            String emptyNameJsonString = "{\"isActive\":false,\"name\":\"\"}";
            Lexer emptyNameLexer = new Lexer(emptyNameJsonString);
            List<String> emptyNameTokens = emptyNameLexer.tokenize();
            System.out.println("Empty name ExampleClass tokens: " + emptyNameTokens);

            Parser emptyNameParser = new Parser(emptyNameTokens);
            JSONObject emptyNameJson = emptyNameParser.parse();
            System.out.println("Empty name ExampleClass JSON object: " + emptyNameJson);

            // Convert JSON to Java object (ExampleClass with an empty name)
            ExampleClass emptyNameExample = JsonMapper.createSpecifiedClassFromJson(ExampleClass.class, emptyNameJson);
            System.out.println("Empty name ExampleClass object: " + emptyNameExample);

            // Convert JSON to Map (ExampleClass with an empty name)
            Map<String, Object> emptyNameJsonMap = JsonMapper.createMapFromJson(emptyNameJson);
            System.out.println("Empty name ExampleClass JSON map: " + emptyNameJsonMap);

            // Convert Java object to JSON string (ExampleClass with an empty name)
            String emptyNameJsonStringFromObject = JsonMapper.createJsonFromObject(emptyNameExample);
            System.out.println(emptyNameJsonStringFromObject);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
