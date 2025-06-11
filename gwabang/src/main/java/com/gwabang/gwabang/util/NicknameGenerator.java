package com.gwabang.gwabang.util;

import java.util.List;

public class NicknameGenerator {
    private static final List<String> adjectives = List.of("기묘한", "졸린", "깃허브발", "추억의");
    private static final List<String> nouns = List.of("코딩러", "디버그", "세미콜론", "커밋로그");

    public static String generateNickname() {
        String adj = adjectives.get((int)(Math.random() * adjectives.size()));
        String noun = nouns.get((int)(Math.random() * nouns.size()));
        int number = (int)(Math.random() * 1000);
        return adj + " " + noun + " #" + String.format("%03d", number);
    }
}

