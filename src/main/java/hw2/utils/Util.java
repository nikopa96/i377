package hw2.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * Author: Mart Kälmo
 * Source: https://bitbucket.org/mkalmo/exservlet/src/master/src/main/java/exservlet/Util.java
 */
public class Util {

    public static String asString(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        return bufferedReader.lines().collect(Collectors.joining("\n"));
    }
}
