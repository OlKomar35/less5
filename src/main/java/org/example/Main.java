package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Map<String, List<Integer>> phoneBook = new HashMap<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int num = 0;
        while (num != 3) {
            System.out.println(" Меню:");
            System.out.println(" 1) Добавить контакт");
            System.out.println(" 2) Вывести всех");
            System.out.println(" 3) Выход");

            num = Integer.parseInt(reader.readLine());

            if (num == 1) {
                System.out.print("Введите имя: ");
                String name = reader.readLine();
                System.out.print("Введите номер: ");
                int number = Integer.parseInt(reader.readLine());

                phoneBook.computeIfAbsent(name, p -> new ArrayList<>()).add(number);

            } else if (num == 2) {

                Map<String, Integer> sortedMap = new HashMap<>();
                for (Map.Entry<String, List<Integer>> entry : phoneBook.entrySet()) {
                    int count = entry.getValue().size();
                    sortedMap.put(entry.getKey(), count);
                }
                Map<String, Integer> sort = sortedMap.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                        .collect(LinkedHashMap::new,
                                (m, c) -> m.put(c.getKey(), c.getValue()),
                                LinkedHashMap::putAll);

                for (Map.Entry<String, Integer> entry : sort.entrySet()) {
                    String key = entry.getKey();
                    System.out.println("[" + key + ": " + phoneBook.get(key) + "]");
                }
            }
        }
        reader.close();
    }
}