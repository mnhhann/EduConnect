/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UTILS;

import ENTITY.QuanTriVien;



/**
 *
 * @author Hân Mai
 */
public class Auth {
    public static QuanTriVien user = null;
    
    public static void clear(){
        Auth.user = null;
    }
    
    public static boolean isLogin(){
        return Auth.user != null;
    }
    
//    public static boolean isManager(){
//        return Auth.isLogin() && user.getVaiTro();
//    }
    
    
}
