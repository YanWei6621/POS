
 //匯入需要的各類套件
 import javax.swing.*;
 import java.awt.event.*;
 import java.awt.*;
 import javax.swing.border.*;
 import java.sql.*;


 //基礎層類別
 //CFD_check: Class FunDation_check (基礎層-檢查類別)

 class CFD_check{

     //檢查傳入的字串是否都由數字組成
     public boolean checkNumber(String s){
  
         int checkResult = 1;   //設定檢查結果為整數1
         int len = s.length();  //取得傳入字串長度(len)
         String[] sList = new String[len];   //建立一個長度為len的字串陣列,用來儲存字串拆解後各個字元用
         
         //將字串拆解成一個個字元,並儲存到陣列sList中,例如: s="abc98", sList={"a","b","c","9","8"};
         for(int x=0; x<len-1; x++)
             sList[x] = s.substring(x,x+1);

         sList[len-1] = s.substring(len-1);

         //逐一的對sList中每一元素檢查是否屬於0~9的數字字元,注意:第一個字元只能1~9.
         //只要有任一個字元不屬於數字字元,則 isNumber = 0, checkResult只要乘到一個0就會變為0
         for(int x=0; x<len; x++){

             int isNumber = 0;
             int startIndex = 0;

             if( x == 0 )
                   startIndex = 1;
             else 
                   startIndex = 0;

             for(int y=startIndex; y<10; y++){
                   if( sList[x].equals( String.valueOf(y) )  ) isNumber = 1;
             }
             checkResult = checkResult * isNumber;
         }
    
         //如果checkResult維持1代表每個字元都是數字字元,且第一個只能1~9,此為正確的整數格式
         if( checkResult == 1 )
               return(true);
         else
               return(false);

     }

 } //end for: class CFD_check

 