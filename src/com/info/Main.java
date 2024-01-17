package com.info;

import java.io.FileInputStream;

public class Main {
    public static void main(String[] args) {
        try {
            new Main().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void start() throws Exception {
        FileInputStream stream = new FileInputStream("C:\\Users\\dgabr\\IdeaProjects\\buffer\\docs\\alfa.txt");
        XmlTagFilterStream filtered = new XmlTagFilterStream(stream, 1024);
        byte[] bytes = filtered.readAllBytes();
        String current = new String(bytes);
        System.out.println(current);
    }
}