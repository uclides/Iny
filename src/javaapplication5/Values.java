/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication5;

/**
 *
 * @author inv.Desarrollo2
 */
public class Values {
    String host,databasename,user,password;
    
    Values(String path,String dbname,String usuario,String pass){
        host=path;
        databasename=dbname;
        user=usuario;
        password=pass;
    }
    public void setHost(String path){
    host=path;
    }
    public void setDbname(String dbname){
    databasename=dbname;
    }
     public void setUser(String usuario){
    user=usuario;
    }
    public void setPassword(String pass){
    password=pass;
    }
    public String getHost(){
    return host;
    }
    public String getDbname(){
    return databasename;
    }
    public String getUser(){
    return user;
    }
    public String getPassword(){
    return password;
    }
    
}
