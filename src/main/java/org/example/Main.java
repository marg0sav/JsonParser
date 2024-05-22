package org.example;

import org.example.json.container.JsonContainer.JSONObject;
import org.example.json.converter.JsonMapper;
import org.example.json.lexer.Lexer;
import org.example.json.parser.Parser;
import org.example.json.test.Company;
import org.example.json.test.ExampleClass;
import org.example.json.test.University;
import org.example.json.test.User;

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
            System.out.println("Input JSON string (User): " + userJsonString);

            // Convert JSON string to tokens
            Lexer userLexer = new Lexer(userJsonString);
            List<String> userTokens = userLexer.tokenize();

            // Parse tokens to JSON object
            Parser userParser = new Parser(userTokens);
            JSONObject userJson = userParser.parse();

            // Convert JSON to Java object (User)
            User user = JsonMapper.createSpecifiedClassFromJson(User.class, userJson);
            System.out.println("User object: " + user);

            // Convert JSON to Map (User)
            Map<String, Object> userJsonMap = JsonMapper.createMapFromJson(userJson);
            System.out.println("User JSON map: " + userJsonMap);

            // Convert Java object to JSON string (User)
            String userJsonStringFromObject = JsonMapper.createJsonFromObject(user);
            System.out.println("User JSON string from object: " + userJsonStringFromObject);

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
            System.out.println("Input JSON string (University): " + universityJsonString);

            // Convert JSON string to tokens
            Lexer universityLexer = new Lexer(universityJsonString);
            List<String> universityTokens = universityLexer.tokenize();

            // Parse tokens to JSON object
            Parser universityParser = new Parser(universityTokens);
            JSONObject universityJson = universityParser.parse();

            // Convert JSON to Java object (University)
            University university = JsonMapper.createSpecifiedClassFromJson(University.class, universityJson);
            System.out.println("University object: " + university);

            // Convert JSON to Map (University)
            Map<String, Object> universityJsonMap = JsonMapper.createMapFromJson(universityJson);
            System.out.println("University JSON map: " + universityJsonMap);

            // Convert Java object to JSON string (University)
            String universityJsonStringFromObject = JsonMapper.createJsonFromObject(university);
            System.out.println("University JSON string from object: " + universityJsonStringFromObject);

            // Example JSON string for the ExampleClass
            String exampleJsonString = "{\"isActive\":true,\"name\":\"Test Name\"}";
            System.out.println("Input JSON string (ExampleClass): " + exampleJsonString);

            // Convert JSON string to tokens
            Lexer exampleLexer = new Lexer(exampleJsonString);
            List<String> exampleTokens = exampleLexer.tokenize();

            // Parse tokens to JSON object
            Parser exampleParser = new Parser(exampleTokens);
            JSONObject exampleJson = exampleParser.parse();

            // Convert JSON to Java object (ExampleClass)
            ExampleClass example = JsonMapper.createSpecifiedClassFromJson(ExampleClass.class, exampleJson);
            System.out.println("ExampleClass object: " + example);

            // Convert JSON to Map (ExampleClass)
            Map<String, Object> exampleJsonMap = JsonMapper.createMapFromJson(exampleJson);
            System.out.println("ExampleClass JSON map: " + exampleJsonMap);

            // Convert Java object to JSON string (ExampleClass)
            String exampleJsonStringFromObject = JsonMapper.createJsonFromObject(example);
            System.out.println("ExampleClass JSON string from object: " + exampleJsonStringFromObject);

            // Example JSON string for the Company class
            String companyJsonString = "{" +
                    "\"name\":\"Tech Corp\"," +
                    "\"departments\":[" +
                    "{" +
                    "\"name\":\"Research\"," +
                    "\"manager\":{\"name\":\"Dr. Smith\",\"age\":55}," +
                    "\"employees\":[" +
                    "{\"name\":\"Alice\",\"age\":30,\"skills\":[\"Java\",\"Python\"]}," +
                    "{\"name\":\"Bob\",\"age\":35,\"skills\":[\"JavaScript\",\"React\"]}" +
                    "]" +
                    "}," +
                    "{" +
                    "\"name\":\"Development\"," +
                    "\"manager\":{\"name\":\"Mr. Johnson\",\"age\":45}," +
                    "\"employees\":[" +
                    "{\"name\":\"Charlie\",\"age\":25,\"skills\":[\"C++\",\"Go\"]}," +
                    "{\"name\":\"David\",\"age\":28,\"skills\":[\"Swift\",\"Objective-C\"]}" +
                    "]" +
                    "}" +
                    "]" +
                    "}";
            System.out.println("Input JSON string (Company): " + companyJsonString);

            // Convert JSON string to tokens
            Lexer companyLexer = new Lexer(companyJsonString);
            List<String> companyTokens = companyLexer.tokenize();

            // Parse tokens to JSON object
            Parser companyParser = new Parser(companyTokens);
            JSONObject companyJson = companyParser.parse();

            // Convert JSON to Java object (Company)
            Company company = JsonMapper.createSpecifiedClassFromJson(Company.class, companyJson);
            System.out.println("Company object: " + company);

            // Convert JSON to Map (Company)
            Map<String, Object> companyJsonMap = JsonMapper.createMapFromJson(companyJson);
            System.out.println("Company JSON map: " + companyJsonMap);

            // Convert Java object to JSON string (Company)
            String companyJsonStringFromObject = JsonMapper.createJsonFromObject(company);
            System.out.println("Company JSON string from object: " + companyJsonStringFromObject);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
