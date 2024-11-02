package tests;

import substringSearch.FullIterativeSearch;

public class TestFullIterativeSearch {

    public static void main(String[] args) {
        FullIterativeSearch fis = new FullIterativeSearch();
        String text1 = "aaaaaaaaab";
        String sample1 = "aaaab";
        String sample2 = "aaaa";
        String sample3 = "baaaa";

        System.out.println(fis.search(text1, sample1));
        System.out.println(fis.search(text1, sample2));
        System.out.println(fis.search(text1, sample3));

        String text2 = "Сшит колпак не по-колпаковски, надо его переколпаковать, перевыколпаковать";
        String sample4 = ", надо";

        int idx;
        System.out.println((idx = fis.search(text2, sample4)));

        System.out.printf(">>%s<<\n", text2.substring(idx, idx + sample4.length()));

        String text3 = """
                Реализовать алгоритм Бойера-Мура
                Цель:
                1. Написать алгоритм поиска подстроки полным перебором, 2 байта.
                2. Оптимизировать алгоритм, используя сдвиги по префиксу шаблона, 2 байта.
                3. Оптимизировать алгоритм, используя сдвиги по суффиксу текста, 2 байта.
                4. Реализовать алгоритм Бойера-Мура, 2 байта.
                5. Протестировать работу алгоритмов для разных начальных данных, 1 байт.
                6. Составить сравнительную таблицу по тестам и написать вывод, 1 байт.
                7. Для точного расчёта времени можно прогонять тест T раз и
                результат делить на T, где T = 10, 100, 1000 или ещё больше.""";


        String sample5 = "алгоритм Бойера-Мура";
        String sample6 = "T раз и\nрезультат";

        System.out.println(fis.search(text3, sample5));
        System.out.println(fis.search(text3, sample6));

    }

}
