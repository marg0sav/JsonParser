package org.example.json.container;

import java.util.*;

/**
 * The JsonContainer class represents a container for various JSON value types.
 */
public class JsonContainer {

    /**
     * The abstract class JSONValue serves as the base class for all JSON value types.
     */
    public abstract static class JSONValue {
        public abstract String toString();
    }

    /**
     * The JSONArray class represents a JSON array type.
     */
    public static class JSONArray extends JSONValue {
        protected List<JSONValue> values = new ArrayList<>();

        public JSONArray() {
        }

        public JSONArray(List<JSONValue> values) {
            this.values = values;
        }

        public void add(JSONValue value) {
            values.add(value);
        }

        public List<JSONValue> getValues() {
            return values;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("[");
            boolean first = true;
            for (JSONValue value : values) {
                if (!first) {
                    sb.append(", ");
                }
                first = false;
                sb.append(value.toString());
            }
            sb.append("]");
            return sb.toString();
        }
    }

    /**
     * The JSONBoolean class represents a JSON boolean value type.
     */
    public static class JSONBoolean extends JSONValue {
        private final boolean value;

        public JSONBoolean(boolean value) {
            this.value = value;
        }

        public boolean getValue() {
            return value;
        }

        @Override
        public String toString() {
            return Boolean.toString(value);
        }
    }

    /**
     * The JSONNull class represents a JSON null value type.
     */
    public static class JSONNull extends JSONValue {

        @Override
        public String toString() {
            return "null";
        }
    }

    /**
     * The JSONNumber class represents a JSON numeric value type.
     */
    public static class JSONNumber extends JSONValue {
        private final double value;

        public JSONNumber(double value) {
            this.value = value;
        }

        public double getValue() {
            return value;
        }

        @Override
        public String toString() {
            return Double.toString(value);
        }
    }

    /**
     * The JSONObject class represents a JSON object type.
     */
    public static class JSONObject extends JSONValue {
        private Map<String, JSONValue> values = new LinkedHashMap<>();

        public JSONObject(Map<String, JSONValue> values) {
            this.values = values;
        }

        public JSONObject() {
        }

        public Map<String, JSONValue> values() {
            return values;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("{");
            boolean first = true;
            for (Map.Entry<String, JSONValue> entry : values.entrySet()) {
                if (!first) {
                    sb.append(", ");
                }
                first = false;
                sb.append("\"").append(entry.getKey()).append("\": ").append(entry.getValue().toString());
            }
            sb.append("}");
            return sb.toString();
        }
    }



    /**
     * The JSONString class represents a JSON string value type.
     */
    public static class JSONString extends JSONValue {
        private final String value;

        public JSONString(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "\"" + value + "\"";
        }
    }
}
