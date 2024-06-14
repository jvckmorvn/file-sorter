package org.filesorter;

import java.nio.file.*;

public class ImageSorter extends FileSorter {
    private Path targetDir = Paths.get(System.getProperty("user.home"), "Pictures");
    private final String[] fileExtensions = {"jpeg", "png"};

    public ImageSorter() {}

    public ImageSorter(String targetDir) {
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
