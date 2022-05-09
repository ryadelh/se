package com.example.demo.Controller;
import com.example.demo.Models.File;
import com.example.demo.Models.Folder;
import com.example.demo.Models.FolderChildren;
import com.example.demo.Models.User;
import com.example.demo.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping(value="/manager/v1/")
@CrossOrigin("http://localhost:8081/manager/v1/")
@RestController
public class MainController {
    @Autowired
    UserService userService;
    @GetMapping(value="/home")
    public String Home(){
        return "Hello in Home";
    }
    @GetMapping(value="/users")
    public List<User> users(){
        return userService.getAllUsers();

    }
    @GetMapping(value="/files")
    public List<File> files(){
        return userService.getAllFiles();

    }
    @GetMapping(value="/folders")
    public List<Folder> folders(){
        return userService.getAllFolders();

    }
    @GetMapping(value="/user")
    public List<Folder> userFolders( ){
        return userService.getAllUserFolders("riad");

    }
    @PostMapping(value="/createUser")
    public User createUser(@RequestBody User user){
        return null;

    }
    @PostMapping(value="/createFile")
    public void createFolders(@RequestBody File file){
         userService.createFile(file);

    }
    @PostMapping(value="/createFolder")
    public void createFolder(@RequestBody Folder folder){
        userService.createFolder(folder);

    }
    //get a specifique folder by id and username
    @GetMapping(value="/folder/{username}/{folderId}")
    public FolderChildren folderAndFilesOfThatFolderUser(@PathVariable String username, @PathVariable int folderId){
        return new FolderChildren(userService.getFolderForUser( username,folderId),userService.getFilesForUser( username,folderId));
    }
    //..........
    @GetMapping(value="/file/{username}/{id}")
    public File getFileForUser(@PathVariable String username, @PathVariable int id){
        return userService.getFileForUser(username,id);
    }
    //get Users home directories
    @GetMapping(value="/users_home_directories")
    public List<Folder> UsersHomeDirectories(){
        return userService.getUsersHomeDirectory(40);
    }
    //remove file
    @GetMapping(value="/remove_file/{fileId}")
    public void removeFile(@PathVariable int fileId){
         userService.removeFile(fileId);
    }
    //remove Folder
    @GetMapping(value="/{AuthenticatedUser}/remove_folder/{folderId}")
    public void removeFolder(@PathVariable String AuthenticatedUser,@PathVariable int folderId){
        userService.removeFolder(AuthenticatedUser,folderId);

    }

}
