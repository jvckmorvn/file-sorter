package org.filesorter;

import java.nio.file.Path;
import java.nio.file.Paths;

public class VideoSorter extends FileSorter {
    private Path targetDir = Paths.get(System.getProperty("user.home"), "Movies");
    private final String[] fileExtensions = {"mp4", "mkv", "mov"};

    public VideoSorter() {}

    public VideoSorter(String targetDir) {
        this.targetDir = Paths.get(targetDir);
    }

    @Override
    protected Path getSourceDir() {
        return Sorter.SOURCE_DIR;
    }

    @Override
    protected String[] getFileExtensions() {
        return fileExtensions;
    }

    @Override
    protected Path getTargetDir() {
        return targetDir;
    }
}
