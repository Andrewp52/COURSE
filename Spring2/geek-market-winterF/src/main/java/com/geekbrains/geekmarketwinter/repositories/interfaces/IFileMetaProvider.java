package com.geekbrains.geekmarketwinter.repositories.interfaces;

import com.geekbrains.geekmarketwinter.entites.FileMetaDTO;

import java.util.Collection;
import java.util.UUID;

public interface IFileMetaProvider {
    String checkFileExists(UUID fileHash);
    String checkFileExists(UUID fileHash, String fileName, int subType);

    void saveFileMeta(UUID Hash, String fileName, int subType);

    Collection<FileMetaDTO> getMetaFiles(int subType);
}
