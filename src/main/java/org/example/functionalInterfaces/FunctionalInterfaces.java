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
        }

        private static void demonstrateCustomFunctionalInterface() {
            System.out.println("=== Custom Functional Interface ===");

            // Using a lambda expression to implement the functional interface
            MyFunctionalInterface myFunc = () -> System.out.println("Hello, World!");
            myFunc.execute();

            // Using a default method
            myFunc.defaultMethod();

            // Using a static method
            MyFunctionalInterface.printMessage();
        }

        private static void demonstratePrebuiltFunctionalInterfaces() {
            System.out.println("\n=== Prebuilt Functional Interfaces ===");

            // Predicate and BiPredicate
            Predicate<String> isEmpty = String::isEmpty;
            System.out.println("Is empty: " + isEmpty.test(""));
            BiPredicate<String, String> startsWith = String::startsWith;
            System.out.println("Starts with 'Hello': " + startsWith.test("Hello, World!", "Hello"));

            // Consumer and BiConsumer
            Consumer<String> print = System.out::println;
            print.accept("Hello from Consumer!");
            BiConsumer<String, String> printConcat = (s1, s2) -> System.out.println(s1 + s2);
            printConcat.accept("Hello, ", "World!");

            // Function and BiFunction
            Function<String, Integer> stringLength = String::length;
            System.out.println("Length of 'Hello': " + stringLength.apply("Hello"));
            BiFunction<String, String, String> concatenate = String::concat;
            System.out.println("Concatenated: " + concatenate.apply("Hello, ", "World!"));

            // UnaryOperator and BinaryOperator
            UnaryOperator<String> toUpperCase = String::toUpperCase;
            System.out.println("Uppercase: " + toUpperCase.apply("hello"));
            BinaryOperator<Integer> add = Integer::sum;
            System.out.println("Sum: " + add.apply(5, 10));

            // Supplier
            Supplier<String> supplier = () -> "Hello from Supplier!";
            System.out.println(supplier.get());

            // IntSupplier, DoubleSupplier, LongSupplier, BooleanSupplier
            IntSupplier intSupplier = () -> 42;
            System.out.println("Int supplier: " + intSupplier.getAsInt());
            DoubleSupplier doubleSupplier = () -> 3.14;
            System.out.println("Double supplier: " + doubleSupplier.getAsDouble());
            LongSupplier longSupplier = () -> 123456789L;
            System.out.println("Long supplier: " + longSupplier.getAsLong());
            BooleanSupplier booleanSupplier = () -> true;
            System.out.println("Boolean supplier: " + booleanSupplier.getAsBoolean());
        }

        private static void demonstrateStreamAndOptional() {
            System.out.println("\n=== Stream and Optional ===");

            // Using IntStream
            IntStream.range(1, 5).forEach(System.out::println);

            // Using Optional
            Optional<String> optional = Optional.of("Hello");
            optional.ifPresent(System.out::println);

            // Using Stream
            List<String> list = Arrays.asList("A", "B", "C");
            list.stream()
                .map(String::toLowerCase)
                .forEach(System.out::println);
        }

        private static void demonstrateCompletableFuture() {
            System.out.println("\n=== CompletableFuture ===");

            CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello from CompletableFuture!");
            future.thenAccept(System.out::println);

            // Using Stream with CompletableFuture
            List<String> items = Arrays.asList("apple", "banana", "cherry");
            items.stream()
                .filter(item -> item.startsWith("a"))
                .forEach(System.out::println);
        }

        private static void demonstrateAdvancedStreamOperations() {
            System.out.println("\n=== Advanced Stream Operations ===");

            // Example 1: Filtering and mapping
            List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve");
            System.out.println("Names starting with 'C' in uppercase:");
            names.stream()
                 .filter(name -> name.startsWith("C"))
                 .map(String::toUpperCase)
                 .forEach(System.out::println);

            // Example 2: Grouping by length
            System.out.println("\nGrouping names by length:");
            Map<Integer, List<String>> groupedByLength = names.stream()
                                                              .collect(Collectors.groupingBy(String::length));
            groupedByLength.forEach((length, group) -> System.out.println(length + ": " + group));

            // Example 3: Partitioning by condition
            System.out.println("\nPartitioning names by length > 3:");
            Map<Boolean, List<String>> partitioned = names.stream()
                                                          .collect(Collectors.partitioningBy(name -> name.length() > 3));
            partitioned.forEach((key, value) -> System.out.println(key + ": " + value));

            // Example 4: Generating a range of numbers
            System.out.println("\nEven numbers from 1 to 10:");
            IntStream.rangeClosed(1, 10)
                     .filter(n -> n % 2 == 0)
                     .forEach(System.out::println);

            // Example 5: Finding max and min
            System.out.println("\nLongest name:");
            names.stream()
                 .max(Comparator.comparingInt(String::length))
                 .ifPresent(System.out::println);

            System.out.println("\nShortest name:");
            names.stream()
                 .min(Comparator.comparingInt(String::length))
                 .ifPresent(System.out::println);

            // Example 6: FlatMapping nested lists
            System.out.println("\nFlattening nested lists:");
            List<List<String>> nestedLists = Arrays.asList(
                Arrays.asList("A", "B"),
                Arrays.asList("C", "D"),
                Arrays.asList("E", "F")
            );
            nestedLists.stream()
                       .flatMap(List::stream)
                       .forEach(System.out::println);

            // Example 7: Summing numbers
            System.out.println("\nSum of numbers from 1 to 5:");
            int sum = IntStream.rangeClosed(1, 5).sum();
            System.out.println(sum);

            // Example 8: Collecting to a set
            System.out.println("\nCollecting distinct names to a set:");
            Set<String> distinctNames = names.stream()
                                             .collect(Collectors.toSet());
            System.out.println(distinctNames);
        }

        private static void demonstrateMapStreamOperations() {
            System.out.println("\n=== Map Stream Operations ===");

            // Example dataset: Map of employee IDs and their names
            Map<Integer, String> employees = Map.of(
                1, "Alice",
                2, "Bob",
                3, "Charlie",
                4, "David",
                5, "Eve"
            );

            // Example 1: Filtering entries by key
            System.out.println("Employees with IDs greater than 2:");
            employees.entrySet().stream()
                     .filter(entry -> entry.getKey() > 2)
                     .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));

            // Example 2: Mapping values to uppercase
            System.out.println("\nEmployee names in uppercase:");
            employees.values().stream()
                     .map(String::toUpperCase)
                     .forEach(System.out::println);

            // Example 3: Collecting filtered entries into a new Map
            System.out.println("\nFiltered employees (IDs > 3) collected into a new Map:");
            Map<Integer, String> filteredEmployees = employees.entrySet().stream()
                                                              .filter(entry -> entry.getKey() > 3)
                                                              .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            System.out.println(filteredEmployees);

            // Example 4: Sorting entries by value
            System.out.println("\nEmployees sorted by name:");
            employees.entrySet().stream()
                     .sorted(Map.Entry.comparingByValue())
                     .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));

            // Example 5: Counting entries with names starting with 'A'
            System.out.println("\nCount of employees with names starting with 'A':");
            long count = employees.values().stream()
                                  .filter(name -> name.startsWith("A"))
                                  .count();
            System.out.println(count);

            // Example 6: Grouping names by their first letter
            System.out.println("\nGrouping employees by the first letter of their names:");
            Map<Character, List<String>> groupedByFirstLetter = employees.values().stream()
                                                                         .collect(Collectors.groupingBy(name -> name.charAt(0)));
            groupedByFirstLetter.forEach((letter, names) -> System.out.println(letter + ": " + names));

            // Example 7: Reducing to a single concatenated string of names
            System.out.println("\nConcatenated employee names:");
            String concatenatedNames = employees.values().stream()
                                                .reduce("", (a, b) -> a + b + " ");
            System.out.println(concatenatedNames.trim());
        }

    }