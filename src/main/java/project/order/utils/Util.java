package project.order.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * Copyright!!!
 *
 * Author: Mart Kälmo
 * Source #1: https://bitbucket.org/mkalmo/exservlet/src/master/src/main/java/exservlet/Util.java
 * Source #2: https://bitbucket.org/mkalmo/exjdbc/src/master/src/main/java/util/FileUtil.java
 */
public class Util {

    public static String asString(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        return bufferedReader.lines().collect(Collectors.joining("\n"));
    }

    public static String readFile(String path) {
        try (InputStream inputStream = Util.class.getClassLoader().getResourceAsStream(path)) {
            if (inputStream == null) {
                throw new IllegalStateException("cannot find and read file " + path);
            }

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            return bufferedReader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
