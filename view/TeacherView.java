package main.java.lesson4.view;

import lesson4.controllers.UserController;
import lesson4.models.Student;
import lesson4.models.Teacher;

import java.util.List;

public class TeacherView implements UserView {

    private final UserController<Teacher> controller;

    public TeacherView(UserController<Teacher> controller) {
        this.controller = controller;
    }

    @Override
    public void sendOnConsole() {
        sendOnConsole(SortType.NONE);
    }

    @Override
    public void sendOnConsole(SortType sortType) {
        List<Teacher> students = switch (sortType) {
            case NONE -> controller.getAll();
            case NAME -> controller.getAllSortByFullName();
            case ID -> controller.getAllSortById();
            default -> throw new IllegalArgumentException("Unexpected value: " + sortType);
        };

        if (students == null || students.size() == 0) {
            System.out.println("Отсутствуют преподаватели для вывода");
            return;
        }

        System.out.println("===================================");
        System.out.println("Для вывода использована " + sortType);
        students.forEach(System.out::println);
        System.out.println("===================================");
    }

    @Override
    public void create(String fullName, Integer age, String phoneNumber, String groupTitle) {
        controller.create(fullName, age, phoneNumber, groupTitle);
    }

    @Override
    public void removeUser(String fullName) {
        int removeCount = controller.remove(fullName);

        if (removeCount == 0) {
            System.out.println("Удаление не получилось.");
        } else {
            System.out.println("Удалено преподавателей: " + removeCount);
        }

    }
}