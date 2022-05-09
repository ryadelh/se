package com.example.demo.Models;
import javax.persistence.*;
import java.util.List;
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    @Column
    String username;
    @Column
    String password;

    public User() {
    }
    public User(String username,String password) {
        this.username = username;
        this.password = password;
        this.id=0;

    }
    public User(String username,String password,long id) {
        this.username = username;
        this.password = password;
        this.id=id;

    }



    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void createFile(){
    }
    public void modifyFile(){

    }
    public long calculateSize(File file){
        return 4;
    }
    public List<File> afficherElement(){
        return null;
    }

    public File chercherElement(File file){
        return new File();
    }






}
