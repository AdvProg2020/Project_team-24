package Coding;

import org.jetbrains.annotations.NotNull;

public class CodeMessage {

    private static final int MODE = 13;

    @NotNull
    public static String code(@NotNull String message) {
        char[] chars = message.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] += i % MODE;
        }
        return String.valueOf(chars);
    }

    @NotNull
    public static String decode(@NotNull String message) {
        char[] chars = message.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] -= i % MODE;
        }
        return String.valueOf(chars);
    }
}
