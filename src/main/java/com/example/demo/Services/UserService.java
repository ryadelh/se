package com.example.demo.Services;

import com.example.demo.Models.File;
import com.example.demo.Models.Folder;
import com.example.demo.Models.User;
import com.example.demo.Repositories.FileRepository;
import com.example.demo.Repositories.FolderRepository;
import com.example.demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    @Autowired
    FileRepository fileRepository;
    @Autowired
    FolderRepository folderRepository;
    @Autowired
    UserRepository userRepository;
    public void createFile(File file){
        Folder folder=new Folder("parent",1);
        folderRepository.save(folder);
       file.setParentfolder(folder);
        fileRepository.save(file);
    }
    public void createFolder(Folder folder){
        Folder pfolder=folderRepository.findById(0).get();
        folder.setParentFolder(pfolder);
        folderRepository.save(folder);

    }
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public List<File> getAllFiles(){
        return fileRepository.findAll();
    }

    public List<Folder> getAllFolders(){
        return folderRepository.findAll();
    }
    public List<Folder> getAllUserFolders(String username){
                return folderRepository.findByOwner(username);
    }

    //get folder content(folders) for a specifique user

    public List<Folder> getFolderForUser(String username,int folderId){
               return folderRepository.findByOwnerAndParentFolderId(username,(int)folderId);
    }
    //get folder content(files) for a specifique user
    public List<File> getFilesForUser(String username,int parentId){
        return fileRepository.findByOwnerAndParentFolderId(username,parentId);
    }
    //get a file for a specifique user
    public File getFileForUser(String username,int id){
        return fileRepository.findByOwnerAndId(username,id);
    }
    //get Users Home Directory or Workspace
    public List<Folder> getUsersHomeDirectory(int parentId){
        return folderRepository.findByParentFolderId(parentId);
    }
    //remove a file
    public void removeFile(int fileId){
        String AuthenticatedUser="User from security context";
        File file=fileRepository.findById(fileId).get();
        System.out.println(file.getOwner());
        if(AuthenticatedUser.equals(file.getOwner())){
            fileRepository.deleteById(fileId);
            System.out.println("File is removed");
        }else{
            System.out.println("Not Authorised");
        }
    }
    //remove a folder
    public void removeFolder(String AuthenticatedUser,int folderId){
        //String AuthenticatedUser="User from security context";
        Folder folder=folderRepository.findById(folderId).get();
        if(AuthenticatedUser.equals(folder.getOwner())) {
         //files
        List<File> childrenFiles=fileRepository.findByOwnerAndParentFolderId(AuthenticatedUser,folderId);
        for(File file:childrenFiles){
                fileRepository.deleteById(file.getId());
        }
        //folders
        List<Folder> childrenFolders=folderRepository.findByOwnerAndParentFolderId(AuthenticatedUser,folderId);
            for(Folder folderToRemove:childrenFolders) {
                folderRepository.deleteById(folderToRemove.getId());
            }
        folderRepository.deleteById(folderId);
            System.out.println("Folder is deleted");

        }else{
            System.out.println("Not Authorised");
        }
    }


}
