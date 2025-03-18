/*
 * Виконав:       Савченко Максим
 * Група:         ІН-22-1
 * Дисципліна:    Об'єктно-орієнтоване програмування (Java)
 * Лабораторна:   №4
 * Дата:          2025-03-25 14:00 (UTC+2)
 * Час виконання: 1 година
 *
 * Опис:
 * Створення класу Phone з полями конструктором, гетерами та сетерами, перезапис методів toString(), equals().
 * Створення ArrayList<Phone> з 5 елементів та вивід їх в консоль
 *
 * Використання штучного інтелекту:
 * - Генерування значень для ScreenResolution
 * - Довідка для класу метода equals()
 * - Генерація документації для класу Phone
 */

package labs.lab4;

import java.util.ArrayList;

public class Lab4 {
    public static void main(String[] args) {
        ArrayList<Phone> phoneList = new ArrayList<>();

        phoneList.add(new Phone("Smartphone", "Xiaomi", "15 Ultra", 1500.00, 16, 1024, ScreenResolution.QUAD_HD));
        phoneList.add(new Phone("Smartphone", "Apple", "iPhone 13", 1200.00, 6, 128, ScreenResolution.FULL_HD));
        phoneList.add(new Phone("Feature Phone", "Nokia", "3310", 50.00, 0, 16, ScreenResolution.WVGA));
        phoneList.add(new Phone("Smartphone", "Samsung", "Galaxy S21", 999.99, 8, 256, ScreenResolution.UHD_4K));
        phoneList.add(new Phone("Smartphone", "OnePlus", "9 Pro", 1069.99, 12, 256, ScreenResolution.QHD));

        for (Phone phone : phoneList) {
            System.out.println(phone);
        }

        System.out.print("\n\n\n");

        Phone phone = new Phone("Smartphone", "Xiaomi", "15 Ultra", 1500.00, 16, 1024, ScreenResolution.QUAD_HD);
        System.out.println("Base parameters: " + phone.getType() + phone.getBrand() + phone.getModel());
        phone.setBrand("New Smartphone");
        phone.setModel("New Xiaomi");
        phone.setBrand("New 15 Ultra");

        System.out.println("New base parameters: " + phone.getType() + phone.getBrand() + phone.getModel());

        System.out.print("\n");
        if (phoneList.get(0).equals(phoneList.get(0))) {
            System.out.println("Phones are equals");
        } else {
            System.out.println("Phones are NOT equals");
        }

        if (phoneList.get(0).equals(phoneList.get(1))) {
            System.out.println("Phones are equals");
        }else {
            System.out.println("Phones are NOT equals");
        }
    }
}