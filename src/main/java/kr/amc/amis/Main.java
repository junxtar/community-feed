package kr.amc.amis;

import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
        String[] s = {"AZWQY", "CAABX", "BBDDA", "ACACA"};
        String[] r = {"A", "BB", "A"};
        solution(s, r);
    }


    static class Node {

        public int x;
        public int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static char[][] map;
    public static boolean[][] stateMap;
    public static int[] dxy = {1, 0, -1, 0, 1};

    public static int solution(String[] storage, String[] requests) {
        init(storage);

        for (String req : requests) {
            if (checkRequest(req)) {
                removeValue(req.charAt(0));
                print();
                System.out.println();
                continue;
            }
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    outSideCheck(i, j);
                }
            }
            checkStateOfTransformMap(req.charAt(0));

            print();
            System.out.println();
        }
        return getResult();
    }

    public static void checkStateOfTransformMap(char ch) {
        for (int i = 0; i < stateMap.length; i++) {
            for (int j = 0; j < stateMap[0].length; j++) {
                if (map[i][j] == ch && stateMap[i][j]) {
                    map[i][j] = '0';
                }
            }
        }
    }

    public static void outSideCheck(int row, int col) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(row, col));

        while (!q.isEmpty()) {
            Node node = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = node.x + dxy[i];
                int ny = node.y + dxy[i + 1];

                if (nx >= map.length || nx < 0 || ny >= map[0].length || ny < 0) {
                    stateMap[row][col] = true;
                    break;
                }
                if (map[nx][ny] == '0') {
                    q.offer(new Node(nx, ny));
                }
            }
        }
    }

    public static boolean checkRequest(String ch) {
        if (ch.length() == 1) {
            return false;
        }
        return true;
    }

    public static void removeValue(char ch) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == ch) {
                    map[i][j] = '0';
                    stateMap[i][j] = true;
                }
            }
        }
    }

    public static int getResult() {
        int result = 0;
        for (char[] ma : map) {
            for (char c : ma) {
                if (c != '0') {
                    result++;
                }
            }
        }
        return result;
    }

    public static void init(String[] storage) {
        int rowLength = storage.length;
        int colLength = storage[0].length();
        map = new char[rowLength][colLength];
        stateMap = new boolean[rowLength][colLength];
        for (int i = 0; i < storage.length; i++) {
            char[] chars = storage[i].toCharArray();
            for (int j = 0; j < chars.length; j++) {
                map[i][j] = chars[j];
            }
        }
    }

    public static void print() {
        for (char[] ma : map) {
            for (char c : ma) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }

    
}
