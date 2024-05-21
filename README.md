# JSON Parser

## Overview

This project implements a JSON parser in Java without using external libraries. The library supports:

- Reading JSON strings.
- Converting JSON to Java objects.
- Converting JSON to `Map<String, Object>`.
- Converting JSON to specified classes.
- Converting Java objects to JSON strings.

The library supports classes with fields (primitives, boxing types, null, arrays, classes), arrays, and collections. However, it does not handle cyclic dependencies or non-representable in JSON types.

## Features

1. **Read JSON string**: Tokenizes and parses JSON strings.
2. **Convert JSON to Java Object**: Creates Java objects from JSON strings.
3. **Convert JSON to `Map<String, Object>`**: Converts JSON objects to maps.
4. **Convert JSON to specified class**: Maps JSON data to specified Java classes.
5. **Convert Java object to JSON string**: Serializes Java objects to JSON strings.

## Classes

### JsonContainer
- **JsonContainer**: Represents a container for various JSON value types.
  - **JSONValue**: Abstract base class for all JSON value types.
  - **JSONArray**: Represents a JSON array type.
  - **JSONBoolean**: Represents a JSON boolean value type.
  - **JSONNull**: Represents a JSON null value type.
  - **JSONNumber**: Represents a JSON numeric value type.
  - **JSONObject**: Represents a JSON object type.
  - **JSONString**: Represents a JSON string value type.

### JsonMapper
- **JsonMapper**: Provides methods to map JSON objects to Java objects and vice versa. Key methods include:
  - `createSpecifiedClassFromJson`: Converts a JSONObject to an instance of a specified Java class.
  - `createMapFromJson`: Converts a JSONObject to a `Map<String, Object>`.
  - `createJsonFromObject`: Converts a Java object to a JSON string.

### Lexer
- **Lexer**: Tokenizes input JSON strings into individual tokens for further parsing.

### Parser
- **Parser**: Parses a list of tokens into a JSON object.


## Usage

### Dependencies

Make sure to include the following dependencies in your `pom.xml`:

```xml
<dependencies>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-engine</artifactId>
        <version>5.10.1</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

## Running the Application

To run the application, use your IDE's run configuration or execute the Main class from the command line:

```sh
mvn exec:java -Dexec.mainClass="org.example.Main"
```
