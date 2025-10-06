package com.sharvan.quoraapp.utils;

import java.time.LocalDateTime;

public class CursorUtils {

    public static boolean isValidCursor(String cursor) {
        if (cursor == null || cursor.isEmpty())
            return true;

        try {
            LocalDateTime.parse(cursor);
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    public static LocalDateTime parseCursor(String cursor) {
        if (isValidCursor(cursor))
            throw new IllegalArgumentException("Invalid Cursor");
        return LocalDateTime.parse(cursor);
    }
}
