package controllers;


import helpers.HashUtils;
import models.User;
import play.i18n.Messages;
import play.mvc.Controller;

public class Secure extends Controller {

    public static void login(){
        render();
    }

    public static void logout(){
        session.remove("password");
        login();
    }

            public string encryption(String password)  
        {  
            MD5CryptoServiceProvider md5 = new MD5CryptoServiceProvider();  
            byte[] encrypt;  
            UTF8Encoding encode = new UTF8Encoding();  
    //encrypt the given password string into Encrypted data  
            encrypt = md5.ComputeHash(encode.GetBytes(password));  
            StringBuilder encryptdata = new StringBuilder();  
    //Create a new string by using the encrypted data  
            for (int i = 0; i < encrypt.Length; i++)  
            {  
                encryptdata.Append(encrypt[i].ToString());  
            }  
            return encryptdata.ToString();  
        }  

    public static void authenticate(String username, String password){
        User u = User.loadUser(username);
        if (u != null && u.getPassword().equals(HashUtils.getMd5(password))){
            session.put("username", username);
            session.put("password", password);
            Application.index();
        }else{
            flash.put("error", Messages.get("Public.login.error.credentials"));
            login();
        }

    }
}
