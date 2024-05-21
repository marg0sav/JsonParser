package org.example.json.test;

public class ExampleClass {
    private boolean isActive;
    private String name;

    public ExampleClass() {
    }

    public ExampleClass(boolean isActive, String name) {
        this.isActive = isActive;
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ExampleClass{" +
                "isActive=" + isActive +
                ", name='" + name + '\'' +
                '}';
    }

    public static void main(String[] args) {
        // Example usage
        ExampleClass example = new ExampleClass(true, "Test Name");
        System.out.println(example);

        // Create an instance with empty name
        ExampleClass emptyNameExample = new ExampleClass(false, "");
        System.out.println(emptyNameExample);
    }
}
