package org.filesorter;

import java.nio.file.*;

public class DocumentSorter extends FileSorter {
    private Path targetDir = Paths.get(System.getProperty("user.home"), "Documents");

    public DocumentSorter() {}

    public DocumentSorter(String targetDir) {
        this.targetDir = Paths.get(targetDir);
    }

    @Override
    protected Path getSourceDir() {
        return Paths.get(System.getProperty("user.home"), "Downloads");
    }

    @Override
    protected String[] getFileExtensions() {
        return new String[]{"txt", "pdf", "docx"};
    }

    @Override
    protected Path getTargetDir() {
        return targetDir;
    }
}
