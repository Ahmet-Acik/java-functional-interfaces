package org.example.functionalInterfaces;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FunctionalInterfaces {
    public static void main(String[] args) {
        demonstrateCustomFunctionalInterface();
        demonstratePrebuiltFunctionalInterfaces();
        demonstrateStreamAndOptional();
        demonstrateCompletableFuture();
        demonstrateAdvancedStreamOperations();
        demonstrateMapStreamOperations();
        demonstrateLambdaAndMethodReferences();
        demonstrateStreamsWithDifferentDataTypes();
    }

    private static void demonstrateCustomFunctionalInterface() {
        System.out.println("=== Custom Functional Interface ===");

        MyFunctionalInterface myFunc = () -> System.out.println("Hello, World!");
        myFunc.execute();

        myFunc.defaultMethod();

        MyFunctionalInterface.printMessage();
    }

    private static void demonstratePrebuiltFunctionalInterfaces() {
        System.out.println("\n=== Prebuilt Functional Interfaces ===");

        Predicate<Integer> isPositive = num -> num > 0;
        System.out.println("Is positive: " + isPositive.test(10));
        BiPredicate<String, Integer> hasLength = (str, len) -> str.length() == len;
        System.out.println("Has length 5: " + hasLength.test("Hello", 5));

        Consumer<List<String>> printList = list -> list.forEach(System.out::println);
        printList.accept(Arrays.asList("Apple", "Banana", "Cherry"));
        BiConsumer<String, Integer> repeatString = (str, times) -> {
            for (int i = 0; i < times; i++) System.out.print(str);
            System.out.println();
        };
        repeatString.accept("Hi", 3);

        Function<String, String> reverseString = str -> new StringBuilder(str).reverse().toString();
        System.out.println("Reversed: " + reverseString.apply("Hello"));
        BiFunction<Integer, Integer, Integer> multiply = (a, b) -> a * b;
        System.out.println("Product: " + multiply.apply(4, 5));

        UnaryOperator<Integer> square = num -> num * num;
        System.out.println("Square: " + square.apply(6));
        BinaryOperator<String> mergeStrings = (s1, s2) -> s1 + "-" + s2;
        System.out.println("Merged: " + mergeStrings.apply("Java", "Programming"));

        Supplier<UUID> randomUUID = UUID::randomUUID;
        System.out.println("Random UUID: " + randomUUID.get());

        IntSupplier randomInt = () -> new Random().nextInt(100);
        System.out.println("Random int: " + randomInt.getAsInt());
        DoubleSupplier randomDouble = () -> Math.random();
        System.out.println("Random double: " + randomDouble.getAsDouble());
        LongSupplier currentTime = System::currentTimeMillis;
        System.out.println("Current time in millis: " + currentTime.getAsLong());
        BooleanSupplier isEven = () -> randomInt.getAsInt() % 2 == 0;
        System.out.println("Is even: " + isEven.getAsBoolean());
    }

    private static void demonstrateStreamAndOptional() {
        System.out.println("\n=== Stream and Optional ===");

        IntStream.range(1, 5).forEach(System.out::println);

        Optional<String> optional = Optional.of("Hello");
        optional.ifPresent(System.out::println);

        List<String> list = Arrays.asList("A", "B", "C");
        list.stream()
                .map(String::toLowerCase)
                .forEach(System.out::println);
    }

    private static void demonstrateCompletableFuture() {
        System.out.println("\n=== CompletableFuture ===");

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello from CompletableFuture!");
        future.thenAccept(System.out::println);

        List<String> items = Arrays.asList("apple", "banana", "cherry");
        items.stream()
                .filter(item -> item.startsWith("a"))
                .forEach(System.out::println);
    }

    private static void demonstrateAdvancedStreamOperations() {
        System.out.println("\n=== Advanced Stream Operations ===");

        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve");
        System.out.println("Names starting with 'C' in uppercase:");
        names.stream()
                .filter(name -> name.startsWith("C"))
                .map(String::toUpperCase)
                .forEach(System.out::println);

        System.out.println("\nGrouping names by length:");
        Map<Integer, List<String>> groupedByLength = names.stream()
                .collect(Collectors.groupingBy(String::length));
        groupedByLength.forEach((length, group) -> System.out.println(length + ": " + group));

        System.out.println("\nPartitioning names by length > 3:");
        Map<Boolean, List<String>> partitioned = names.stream()
                .collect(Collectors.partitioningBy(name -> name.length() > 3));
        partitioned.forEach((key, value) -> System.out.println(key + ": " + value));

        System.out.println("\nEven numbers from 1 to 10:");
        IntStream.rangeClosed(1, 10)
                .filter(n -> n % 2 == 0)
                .forEach(System.out::println);

        System.out.println("\nLongest name:");
        names.stream()
                .max(Comparator.comparingInt(String::length))
                .ifPresent(System.out::println);

        System.out.println("\nShortest name:");
        names.stream()
                .min(Comparator.comparingInt(String::length))
                .ifPresent(System.out::println);

        System.out.println("\nFlattening nested lists:");
        List<List<String>> nestedLists = Arrays.asList(
                Arrays.asList("A", "B"),
                Arrays.asList("C", "D"),
                Arrays.asList("E", "F")
        );
        nestedLists.stream()
                .flatMap(List::stream)
                .forEach(System.out::println);

        System.out.println("\nSum of numbers from 1 to 5:");
        int sum = IntStream.rangeClosed(1, 5).sum();
        System.out.println(sum);

        System.out.println("\nCollecting distinct names to a set:");
        Set<String> distinctNames = names.stream()
                .collect(Collectors.toSet());
        System.out.println(distinctNames);
    }

    private static void demonstrateMapStreamOperations() {
        System.out.println("\n=== Map Stream Operations ===");

        Map<Integer, String> employees = Map.of(
                1, "Alice",
                2, "Bob",
                3, "Charlie",
                4, "David",
                5, "Eve"
        );

        System.out.println("Employees with IDs greater than 2:");
        employees.entrySet().stream()
                .filter(entry -> entry.getKey() > 2)
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));

        System.out.println("\nEmployee names in uppercase:");
        employees.values().stream()
                .map(String::toUpperCase)
                .forEach(System.out::println);

        System.out.println("\nFiltered employees (IDs > 3) collected into a new Map:");
        Map<Integer, String> filteredEmployees = employees.entrySet().stream()
                .filter(entry -> entry.getKey() > 3)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println(filteredEmployees);

        System.out.println("\nEmployees sorted by name:");
        employees.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));

        System.out.println("\nCount of employees with names starting with 'A':");
        long count = employees.values().stream()
                .filter(name -> name.startsWith("A"))
                .count();
        System.out.println(count);

        System.out.println("\nGrouping employees by the first letter of their names:");
        Map<Character, List<String>> groupedByFirstLetter = employees.values().stream()
                .collect(Collectors.groupingBy(name -> name.charAt(0)));
        groupedByFirstLetter.forEach((letter, names) -> System.out.println(letter + ": " + names));

        System.out.println("\nConcatenated employee names:");
        String concatenatedNames = employees.values().stream()
                .reduce("", (a, b) -> a + b + " ");
        System.out.println(concatenatedNames.trim());
    }

    private static void demonstrateLambdaAndMethodReferences() {
        System.out.println("\n=== Lambda and Method References ===");

        Runnable lambdaRunnable = () -> System.out.println("Running with a lambda!");
        lambdaRunnable.run();

        Consumer<String> staticMethodReference = System.out::println;
        staticMethodReference.accept("Hello from a static method reference!");

        String message = "Hello, World!";
        Supplier<Integer> instanceMethodReference = message::length;
        System.out.println("Length of the message: " + instanceMethodReference.get());

        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        System.out.println("Names in uppercase:");
        names.stream()
                .map(String::toUpperCase)
                .forEach(System.out::println);

        Supplier<List<String>> constructorReference = ArrayList::new;
        List<String> newList = constructorReference.get();
        newList.add("New Item");
        System.out.println("List created using constructor reference: " + newList);

        BiFunction<String, String, String> concatenate = String::concat;
        System.out.println("Concatenated string: " + concatenate.apply("Hello, ", "Lambda!"));

        Predicate<Integer> isEven = number -> number % 2 == 0;
        System.out.println("Is 4 even? " + isEven.test(4));
        System.out.println("Is 5 even? " + isEven.test(5));
    }

    private static void demonstrateStreamsWithDifferentDataTypes() {
        System.out.println("\n=== Streams with Different Data Types ===");

        System.out.println("Square of integers:");
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        integers.stream()
                .map(n -> n * n)
                .forEach(System.out::println);

        System.out.println("\nHalf of doubles:");
        List<Double> doubles = Arrays.asList(2.0, 4.0, 6.0, 8.0);
        doubles.stream()
                .map(d -> d / 2)
                .forEach(System.out::println);

        System.out.println("\nStrings with length greater than 3:");
        List<String> strings = Arrays.asList("cat", "elephant", "dog", "tiger");
        strings.stream()
                .filter(s -> s.length() > 3)
                .forEach(System.out::println);

        System.out.println("\nUppercase characters:");
        String word = "stream";
        word.chars()
                .mapToObj(c -> (char) c)
                .map(Character::toUpperCase)
                .forEach(System.out::println);

        System.out.println("\nCount of true values:");
        List<Boolean> booleans = Arrays.asList(true, false, true, true, false);
        long trueCount = booleans.stream()
                .filter(b -> b)
                .count();
        System.out.println(trueCount);

        System.out.println("\nCustom objects sorted by age:");
        class Person {
            String name;
            int age;

            Person(String name, int age) {
                this.name = name;
                this.age = age;
            }

            @Override
            public String toString() {
                return name + " (" + age + ")";
            }
        }
        List<Person> people = Arrays.asList(
                new Person("Alice", 30),
                new Person("Bob", 25),
                new Person("Charlie", 35)
        );
        people.stream()
                .sorted(Comparator.comparingInt(p -> p.age))
                .forEach(System.out::println);

        System.out.println("\nEnum values filtered by condition:");
        enum Day {
            MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
        }
        EnumSet.allOf(Day.class).stream()
                .filter(day -> day.name().startsWith("S"))
                .forEach(System.out::println);
    }
}