/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main;

import controller.CityJpaController;
import controller.CountryJpaController;
import db.City;
import db.Country;
import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Spliterator;
import java.util.StringJoiner;
import java.util.TreeMap;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Ashraf-XCODER
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    private static EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("WorldPU");

    public static void main(String[] args) throws Exception {
        highestPopulation();
    }

    public static void highestPopulation() {
        CountryJpaController countries = new CountryJpaController(emf);
        //Optional<Integer> max = 
                countries.findCountryEntities().stream()
                .map(Country::getPopulation)
                .max(Comparator.naturalOrder())
                .ifPresent(System.out::println);
    }
    
    
    public static void misc() {
        Comparator<Integer> comp = Integer::compare;

        SecurityManager manager;

        CityJpaController city = new CityJpaController(emf);

        List<City> cities = city.findCityEntities();

        long worldPopulation = cities.stream()
                .mapToLong(City::getPopulation)
                .sum();
        //.reduce(0, (x,y) -> x+y);

        System.out.println(worldPopulation);

        //Map<String, List<City> map =
        TreeMap<String, List<City>> map = cities
                .stream()
                .filter(c -> c.getCountryCode().contains("PAK"))
                .collect(Collectors.groupingBy(
                                City::getCountryCode,
                                TreeMap::new, Collectors.toList()
                        ));
        map.forEach((s, l) -> {
            System.out.printf("%s has %d citie(s)%n", s, l.size());
            l.forEach(c -> System.out.println(c.getName() + " " + c.getDistrict()));
        });

        //cities.forEach(c -> System.out.println(c.getName()));
        CountryJpaController countries = new CountryJpaController(emf);
        List<Country> CountryEntities = countries.findCountryEntities();

        //CountryEntities.forEach(c -> System.out.println(c.getCode()));
        //System.out.println(CountryEntities.stream().count());
        //walkDirs();
        //StreamCollectors collectors = new StreamCollectors();
        //collectors.collectPerson();
        //Streams test = new Streams();
        //test.flatMap();
        //showDirs(new File("F:\\Java"));
        //consumerTests();
    }

    public static void listDirs() throws Exception {
        Path path = Paths.get("G:", "University");
        try (Stream<Path> stream = Files.list(path)) {
            stream
                    .filter(p -> p.toFile().getName().endsWith(".pdf"))
                    .forEach(System.out::println);
        }
    }

    public static void walkDirs() throws Exception {
        Path path = Paths.get("G:", "University");
        try (Stream<Path> stream = Files.walk(path)) {
            stream
                    //.filter(p -> p.toFile().isDirectory())
                    .forEach(System.out::println);
        }
    }

    private static void streamsIntro() {
        BinaryOperator<Integer> result = (x, y) -> x + y;

        new Random().ints().limit(10).forEach(i -> System.err.println(i));

        Function<String, String> upCase = name -> name.toUpperCase();

        Consumer<String> show = s -> System.err.println(s);

        List<String> names = Arrays.asList(new String[]{"ashraf", "umair", "hassan", "rasheed"});

        if (names.stream().anyMatch(s -> s.startsWith("um"))) {
            System.out.println("Umair is present");
        }

        Spliterator.OfInt spliterator = Arrays.spliterator(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});

        System.out.println(upCase.apply("ashraf"));

        show.accept("hi");

        StringJoiner joiner = new StringJoiner(",", "[", "]");
        joiner.add("kotlin");
        joiner.add("phantom");
        joiner.add("java");
        joiner.add("groovy");
        joiner.add("scala");
        System.out.println(joiner.toString());

        System.out.println(result.apply(2, 3));
    }

    public static void showDirs(File dir) {
        FileFilter filter = (p) -> p.getName().endsWith(".java");
        FilenameFilter nameFilter = (d, n) -> n.endsWith(".java");

        Arrays.asList(dir.list())
                .forEach(System.out::println);
    }

    public static void consumerTests() {

        List<Integer> ints = Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        List<Integer> result = new ArrayList<>();

        Consumer<Integer> print = System.out::println;
        Consumer<Integer> add = result::add;
        Consumer<Integer> addAndShow = add.andThen(print);

        ints.forEach(addAndShow);
        result.forEach(print);
    }

    public static void countWords(File file) throws Exception {
        Pattern pattern = Pattern.compile("\\s+");

        TreeMap<String, Long> wordsCount = Files.lines(file.toPath())
                .map(line -> line.replaceAll("(?!')\\p{P}", ""))
                .flatMap(line -> pattern.splitAsStream(line))
                .collect(Collectors.groupingBy(String::toLowerCase,
                                TreeMap::new, Collectors.counting()));

        wordsCount.entrySet()
                .stream()
                .collect(Collectors.groupingBy(entry -> entry.getKey().charAt(0),
                                TreeMap::new, Collectors.toList()))
                .forEach((letter, wordList) -> {
                    System.out.printf("%n%C%n", letter);
                    wordList.stream()
                    .forEach(word -> System.out.printf("%13s: %d%n", word.getKey(), word.getValue()));
                });

    }

    public static void RollADie() {
        SecureRandom random = new SecureRandom();
        System.out.printf("%-6s%s%n", "Face", "Frequency");
        random.ints(6_000_000, 1, 7)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .forEach((face, frequency) -> {
                    System.out.printf("%-6d%d%n", face, frequency);
                });
    }

    /**
    *Given a list [1, 2, 3] and a list [3, 4] you should return 
    *    [(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]
    **/
    public static void makePairs() {
        
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<int[]> pairs
                = numbers1.stream()
                .flatMap(i -> numbers2.stream()
                        .map(j -> new int[]{i, j})
                )
                .collect(toList());
    }
}
