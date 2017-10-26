package org.yqj.disruptor.demo;

import org.springframework.boot.Banner;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.boot.ansi.AnsiStyle;
import org.springframework.core.env.Environment;

import java.io.PrintStream;

/**
 * Created by yaoqijun.
 * Date:2016-04-27
 * Email:yaoqj@terminus.io
 * Descirbe:
 */
public class DemoBanner implements Banner{

    private static final String[] BANNER = {
            "",
            "",
            "_____.___.  _____   ________   ________  .___     ____.____ __________   ",
            "\\__  |   | /  _  \\  \\_____  \\  \\_____  \\ |   |   |    |    |   \\      \\  ",
            " /   |   |/  /_\\  \\  /   |   \\  /  / \\  \\|   |   |    |    |   /   |   \\ ",
            " \\____   /    |    \\/    |    \\/   \\_/.  \\   /\\__|    |    |  /    |    \\",
            " / ______\\____|__  /\\_______  /\\_____\\ \\_/___\\________|______/\\____|__  /",
            " \\/              \\/         \\/        \\__>                            \\/ ",
            "",
            ""
    };

    private static final String SPRING_BOOT = " :: Powered by Personal.inc :: ";

    private static final int STRAP_LINE_SIZE = 42;

    public void printBanner(Environment environment, Class<?> sourceClass, PrintStream printStream) {
        for (String line : BANNER) {
            printStream.println(line);
        }
        String version = "(v1.0)";
        String padding = "";
        while (padding.length() < STRAP_LINE_SIZE
                - (version.length() + SPRING_BOOT.length())) {
            padding += " ";
        }

        printStream.println(AnsiOutput.toString(AnsiColor.GREEN, SPRING_BOOT,
                AnsiColor.DEFAULT, padding, AnsiStyle.FAINT, version));
        printStream.println();
    }
}
