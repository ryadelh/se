package com.example.demo.Repositories;
import com.example.demo.Models.Folder;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
//folder repo
@Repository
public interface FolderRepository extends JpaRepository<Folder, Integer> {
        List<Folder> findByOwner(String owner);
        List <Folder> findByOwnerAndParentFolderId(String owner,int parentId);
        List <Folder> findByParentFolderId(int parentId);
        // void DeleteAllChildrenFolders();

}