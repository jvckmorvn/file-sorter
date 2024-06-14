package org.filesorter;

import java.nio.file.*;

public interface Sorter {
    Path SOURCE_DIR = Paths.get(System.getProperty("user.home"), "Downloads");
}
