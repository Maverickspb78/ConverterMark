package com.andreev.properties;

public class PathProperties implements Prop{

    private String pathToWriteFile;
    private String pathToWriteFileO;
    private String fileKMPath;
    private String fileName;
    private String pathInOborot;
    private String pathOutOborot;

    public String getPathToWriteFile() {
        return pathToWriteFile;
    }

    public void setPathToWriteFile(String pathToWriteFile) {
        this.pathToWriteFile = pathToWriteFile;
    }

    public String getPathToWriteFileO() {
        return pathToWriteFileO;
    }

    public void setPathToWriteFileO(String pathToWriteFileO) {
        this.pathToWriteFileO = pathToWriteFileO;
    }

    public String getFileKMPath() {
        return fileKMPath;
    }

    public void setFileKMPath(String fileKMPath) {
        this.fileKMPath = fileKMPath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPathInOborot() {
        return pathInOborot;
    }

    public void setPathInOborot(String pathInOborot) {
        this.pathInOborot = pathInOborot;
    }

    public String getPathOutOborot() {
        return pathOutOborot;
    }

    public void setPathOutOborot(String pathOutOborot) {
        this.pathOutOborot = pathOutOborot;
    }
}
