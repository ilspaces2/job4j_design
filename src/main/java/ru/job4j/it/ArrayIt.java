package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Шаблон "итератор" позволяет последовательно получить элементы набора данных.
 * Описывается интерфейсом - java.util.Iterator.
 * Используется в коллекциях, базах данных, чтении файлов.
 * <p>
 * Описание интерфейса Iterator в спецификации
 * <a href="https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html">API Java 8 - Iterator</a>
 * <ul>
 *      <li>Метод next в случае отсутствия элементов к возврату генерирует NoSuchElementException.
 *      <li>Метод next должен возвращать верные значения вне зависимости
 *          от того вызвал ли перед этим программист метод hasNext.
 *          Аналогично для hasNext. Результат работы ваших методов не должен зависеть
 *          от последовательности в которой программист вызывает методы, т.е. не полагайтесь на то,
 *          что программист будет вызывать методы именно в том порядке в котором вы ожидаете.
 *      <li>Не допускайте дублирования кода. Если вы видите, что методы next и hasNext имеют одинаковый код,
 *          то выносите этот код в отдельный метод и делайте уже его вызов.
 *      <li>Не используйте эксепшены для управления логикой вашей программы. Они созданы для обработки
 *          критических ситуаций + очень дороги в создании по сравнению с обычными объектами в Java.
 *      <li>Не оставляйте пустых методов в коде. Обратите внимание, что метод remove объявлен как
 *          дефолтный - это значит, что нет необходимости его реализовывать пустым, если вы не собираетесь
 *          переопределять его поведение. В коде не должно быть пустых методов, если ваша программа
 *          не поддерживает какой-либо функционал задекларированный в интерфейсе - прокидывайте UnsupportedOperationException.
 * </ul>
 */
public class ArrayIt implements Iterator<Integer> {
    private final int[] data;
    private int point = 0;

    public ArrayIt(int[] data) {
        this.data = data;
    }

    /**
     * Метод hasNext проверяет, если ли следующий элемент.
     * Многократный вызов этого метода должен быть одинаковым.
     *
     * @return если следующего элемента нету то возвращает false
     */
    @Override
    public boolean hasNext() {
        return point < data.length;
    }

    /**
     * Метод next сдвигает указатель итератора.
     * Указатель - это ссылка на элемент, который нужно вернуть.
     *
     * @return возвращает элемент
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[point++];
    }
}