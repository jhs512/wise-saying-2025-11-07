package com.back;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;


public class App {
    private final Scanner scanner = new Scanner(System.in);
    private int lastId = 0;
    private final List<WiseSaying> wiseSayings = new ArrayList<>();

    public void run() {
        System.out.println("== 명언 앱 ==");

        while1:
        while (true) {
            System.out.print("명언) ");
            String cmd = scanner.nextLine().trim();

            if (cmd.isBlank()) continue;

            Rq rq = new Rq(cmd);

            switch (rq.getActionName()) {
                case "종료" -> {
                    System.out.println("프로그램이 종료됩니다.");
                    break while1;
                }
                case "등록" -> actionWrite();
                case "목록" -> actionList();
                case "삭제" -> actionDelete(rq);
                case "수정" -> actionModify(rq);
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

    private void actionDelete(Rq rq) {
        int id = rq.getParamAsInt("id", -1);

        if (id == -1) {
            System.out.println("id를 (숫자로) 입력해주세요.");
            return;
        }

        boolean deleted = delete(id);

        if (!deleted) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
            return;
        }

        System.out.printf("%d번 명언이 삭제되었습니다.\n", id);
    }

    private void actionModify(Rq rq) {
        int id = rq.getParamAsInt("id", -1);

        if (id == -1) {
            System.out.println("id를 (숫자로) 입력해주세요.");
            return;
        }

        WiseSaying wiseSaying = findById(id);

        if (wiseSaying == null) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
            return;
        }

        System.out.printf("명언(기존) : %s\n", wiseSaying.getContent());
        System.out.print("명언 : ");
        String content = scanner.nextLine().trim();

        System.out.printf("작가(기존) : %s\n", wiseSaying.getAuthor());
        System.out.print("작가 : ");
        String author = scanner.nextLine().trim();

        modify(wiseSaying, content, author);

        System.out.printf("%d번 명언이 수정되었습니다.\n", id);
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

    private boolean delete(int id) {
        int index = findIndexById(id);

        if (index == -1) return false;

        wiseSayings.remove(index);

        return true;
    }

    private int findIndexById(int id) {
        return IntStream.range(0, wiseSayings.size())
                .filter(i -> wiseSayings.get(i).getId() == id)
                .findFirst()
                .orElse(-1);
    }

    private WiseSaying findById(int id) {
        return wiseSayings.stream()
                .filter(w -> w.getId() == id)
                .findFirst()
                .orElse(null);
    }

    private void modify(WiseSaying wiseSaying, String content, String author) {
        wiseSaying.setContent(content);
        wiseSaying.setAuthor(author);
    }
}
