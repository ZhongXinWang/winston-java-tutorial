package com.winston.spring.banner;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.boot.ansi.AnsiStyle;
import org.springframework.core.env.Environment;

import java.io.PrintStream;
/**
* x修改spring boot Banner
* @author Winston.Wang
* @date 2019/7/22
* @version 1.0
**/
public class MyBanner implements Banner {

    private static final String[] BANNER = { "",
            "  .   ____          _            __ _ _",
            " /\\\\ / ___'_ __ _ _(_)_ __  __ _ \\ \\ \\ \\",
            "( ( )\\___ | '_ | '_| | '_ \\/ _` | \\ \\ \\ \\",
            " \\\\/  ___)| |_)| | | | | || (_| |  ) ) ) )",
            "  '  |____| .__|_| |_|_| |_\\__, | / / / /",
            " =========|_|==============|___/=/_/_/_/" };

    private static final String[] MY_BANNER = {"",
  "          /// ___|   _ __    _ __  (_)  _ __     __ _    | __ )    ___     ___   | |_    /// ___|   /// _ \\    | ____|   __ _   ___   _   _ ",
 "\\___ \\  | '_ \\  | '__| | | | '_ \\   // _` |   |  _ \\   // _ \\   // _ \\  | __|   \\___ \\  | | | |   |  _|    / _` | / __| | | | |",
  "  ___) | | |_) | | |    | | | | | | | (_| |   | |_) | | (_) | | (_) | | |_     ___) | | |_| |   | |___  | (_| | \\__ \\ | |_| |",
  "          |____//  | .__//  |_|    |_| |_| |_|  \\__, |   |____//   \\___//   \\___//   \\__|   |____//   \\___//    |_____|  \\__,_| |___/  \\__, |",
  "          |_|                         |___/                                                                             |___/"
};

    private static final String SPRING_BOOT = " :: Spring Boot SO Easy :: ";

    private static final int STRAP_LINE_SIZE = 42;

    @Override
    public void printBanner(Environment environment, Class<?> sourceClass,
                            PrintStream printStream) {
        for (String line : MY_BANNER) {
            printStream.println(line);
        }
        String version = SpringBootVersion.getVersion();
        version = (version != null) ? " (v" + version + ")" : "";
        StringBuilder padding = new StringBuilder();
        while (padding.length() < STRAP_LINE_SIZE
                - (version.length() + SPRING_BOOT.length())) {
            padding.append(" ");
        }

        printStream.println(AnsiOutput.toString(AnsiColor.GREEN, SPRING_BOOT,
                AnsiColor.DEFAULT, padding.toString(), AnsiStyle.FAINT, version));
        printStream.println();
    }

}
