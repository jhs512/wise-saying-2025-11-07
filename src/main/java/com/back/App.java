package com.back;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private final Scanner scanner = new Scanner(System.in);
    private int lastId = 0;
    private List<WiseSaying> wiseSayings = new ArrayList<>();

    public void run() {
        System.out.println("== 명언 앱 ==");

        while1:
        while (true) {
            System.out.print("명언) ");
            String cmd = scanner.nextLine().trim();

            if (cmd.isBlank()) continue;

            switch (cmd) {
                case "종료" -> {
                    System.out.println("프로그램이 종료됩니다.");
                    break while1;
                }
                case "등록" -> actionWrite();
                case "목록" -> actionList();
            }
        }

        scanner.close();
    }

    private void actionWrite() {
        System.out.print("명언 : ");
        String content = scanner.nextLine().trim();

        System.out.print("작가 : ");
        String author = scanner.nextLine().trim();

        WiseSaying wiseSaying = write(content, author);

        System.out.printf("%d번 명언이 등록되었습니다.\n", wiseSaying.getId());
    }

    private void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        List<WiseSaying> forList = findForList();

        for (WiseSaying wiseSaying : forList) {
            System.out.printf("%d / %s / %s\n", wiseSaying.getId(), wiseSaying.getAuthor(), wiseSaying.getContent());
        }
    }

    private WiseSaying write(String content, String author) {
        int id = ++lastId;
        WiseSaying wiseSaying = new WiseSaying(id, content, author);

        wiseSayings.add(wiseSaying);

        return wiseSaying;
    }

    private List<WiseSaying> findForList() {
        return wiseSayings.reversed();
    }
}
