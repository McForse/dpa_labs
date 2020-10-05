package ru.mirea.n03pr11;

public class Main {

    public static void main(String[] args) {
	    MyHashTable table = new MyHashTable(10);

	    // Добавление элементов в хеш-таблицу
	    table.add(new Product(0, "cup"));
	    table.add(new Product(2, "plate"));
	    table.add(new Product(3, "spoon"));
	    table.add(new Product(5, "fork"));
	    table.add(new Product(8, "knife"));
	    table.add(new Product(13, "teapot"));
	    table.add(new Product(18, "glass"));
	    table.add(new Product(23, "bread basket"));

	    // Вывод хеш-таблицы
	    System.out.println("Hash table:");
	   	table.print();

		// Удаление элементов хеш-таблицы
		System.out.println("Remove (Product{code=13, name='teapot'}, Product{code=8, name='knife'})");
		table.remove(new Product(13, "teapot"));
		table.remove(new Product(8, "knife"));
		table.print();
    }
}
