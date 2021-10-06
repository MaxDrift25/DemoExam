package ru.pa4ok.demoexam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * shift + F10 - запуск последней конфигурации
 * alt + enter - контекстное меню по исправлению ошибки
 * ctrl + space - контекстное меню по доступыным полям и методам объекта (после точки)
 * alt + insert - контекстное меню по генерации кода (конструкторы, геттеры, сеттеры, toString...)
 */

public class App
{
    /*
        Book
        - String author
        - String title
        - ing pages

        Library
        - String address
        - List<Book> books
        - public boolean hasBook(String title, String author, int pages)
        - public Book addBook(String title, String author, int pages)
        - public Book removeBook(String title, String author, int pages)

        методы addBook и removeBook принимают поля книги

        добавить книгу нужно только в том случае, если нет аналогичной
        если аналогичная книга есть, метод возврает null, если книга успешно добавлена - вернуть ее

        метод удаления книги возвращает объект удаленной из коллекции книги
        если такой книги в списке нет - возращаете null
     */

    public static void main(String[] args)
    {
        /*Container c1 = new Container("wefwfewf");
        String s = (String) c1.value;

        Container c2 = new Container(new Book("efjiwef", "werfwef", 22));
        Book book = (Book) c2.value;*/

        /*Container<String> c1 = new Container<>("wefwfewf");
        String s = c1.value;

        Container<Book> c2 = new Container<>(new Book("efjiwef", "werfwef", 22));
        Book book = c2.value;*/

        /*List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");
        System.out.println(list);
        System.out.println(list.size());
        System.out.println(list.isEmpty());
        //list.clear();
        System.out.println(list.get(1));
        list.set(0, "aaaa");
        System.out.println(list);
        list.remove(2);
        System.out.println(list);*/

        List<String> list = new ArrayList<>(Arrays.asList(
                "1", "22", "3", "44", "55", "7", "88"
        ));

        System.out.println(list);

        /*for(String s : list) {
            //...
        }*/

        /*for(int i=0; i<list.size(); i++) {
            if(list.get(i).length() > 1) {
                list.remove(i);
                i--;
            }
        }*/

        /*for(int i=list.size()-1; i>=0; i--) {
            if(list.get(i).length() > 1) {
                list.remove(i);
            }
        }*/

        list.removeIf(s -> s.length() > 1);

        System.out.println(list);
    }
}

class Container<T>
{
    public T value;

    public Container(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Container{" +
                "value=" + value +
                '}';
    }
}