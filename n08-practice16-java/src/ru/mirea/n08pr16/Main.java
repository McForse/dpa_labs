package ru.mirea.n08pr16;

import java.util.ArrayList;
import java.util.List;

/**
 * Вариант 6
 * Реализовать задачу о рюкзаке методом ветвей и границ
 */
public class Main {

    public static void main(String[] args) throws Exception {
	    int W = 18; // Вместимость рюкзака

        List<Item> items = new ArrayList<Item>() {{
            add(new Item(5, 100));
            add(new Item(10, 200));
            add(new Item(3, 300));
            add(new Item(2, 50));
        }};

        Backpack backpack = new Backpack(W, items);

        System.out.println("Список доступных предметов:");
        items.forEach(System.out::println);
        System.out.println("\nМаксимальная суммарная стоимость: "
                + backpack.getMaxCost());
    }
}
