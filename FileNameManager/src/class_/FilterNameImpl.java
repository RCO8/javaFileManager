package class_;

import inter.FillterName;
import main.Data;

import java.io.File;
import java.util.Vector;

public class FilterNameImpl implements FillterName {
    @Override
    public void nameOr(Data db, String temp) {
        Vector<File> newFiles = new Vector<>(db.fileList.size());
        for (File file : db.fileList) {
            boolean contains = file.getName().contains(temp);
            if (contains) {
                newFiles.add(file);
            }
        }
        db.fileList = newFiles;
    }

    @Override
    public void nameOrElse(Data db, String temp) {
        Vector<File> newFiles = new Vector<>(db.fileList.size());
        for (File file : db.fileList) {
            boolean contains = file.getName().contains(temp);
            if (!contains) {
                newFiles.add(file);
            }
        }
        db.fileList = newFiles;
    }

    @Override
    public void nameEqualElse(Data db, String temp) {
        Vector<File> newFiles = new Vector<>(db.fileList.size());
        for (File file : db.fileList) {
            boolean contains = file.getName().equals(temp);
            if (!contains) {
                newFiles.add(file);
            }
        }
        db.fileList = newFiles;
    }

    @Override
    public void nameEqual(Data db, String temp) {
        Vector<File> newFiles = new Vector<>(db.fileList.size());
        for (File file : db.fileList) {
            boolean contains = file.getName().equals(temp);
            if (contains) {
                newFiles.add(file);
            }
        }
        db.fileList = newFiles;
    }
}
