package com.back;

import java.util.Scanner;

public class App {
    private final Scanner scanner = new Scanner(System.in);

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
            }
        }

        scanner.close();
    }
}
