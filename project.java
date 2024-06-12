//匯入需要的各類套件
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.border.*;
import java.sql.*;

//主程式: project.java

public class project
{
    public static void main(String[] args)
    {
	if( args.length == 2 && args[0].equals("-p") && args[1].equals("setupdb") )
	{
	    CDM_dbma myDBMA = new CDM_dbma();
            myDBMA.createDB();             //建立資料庫bmsdb,只有在下執行參數 -p setupdb 時執行
            myDBMA.createTB_product();     //在資料庫bmsdb建立資料表product
            myDBMA.createTB_employee();     //在資料庫bmsdb建立資料表employee
            myDBMA.createTB_clockin();     //在資料庫bmsdb建立資料表clockin
            myDBMA.createTB_sell();     //在資料庫bmsdb建立資料表sell
            myDBMA.createTB_detail();     //在資料庫bmsdb建立資料表detail
	    loadDATA loaddata = new loadDATA();
	    
	    String[][] aproduct = {{"1","麵包","豆沙麵包","35","100"},{"2","麵包","牛角麵包","30","150"},
				   {"3","麵包","菠蘿麵包","25","100"},{"4","麵包","鮮奶吐司","40","150"},
				   {"5","麵包","甜甜圈","30","150"},{"6","麵包","餐包","25","150"},
				   {"7","麵包","起酥麵包","25","150"},{"8","麵包","椰香麵包","36","100"},
				   {"9","麵包","貝果","40","80"},{"10","麵包","白吐司","30","200"},
				   {"11","麵包","肉鬆麵包","30","125"},{"12","麵包","香蒜金磚","36","100"},
				   {"13","麵包","法國麵包","45","100"},{"14","麵包","丹麥玫瑰","70","50"},
				   {"15","麵包","和風起司","38","50"},{"16","麵包","丹麥飛碟","40","50"},
				   {"17","蛋糕","黑森林蛋糕","55","20"},{"18","蛋糕","乳酪蛋糕","60","30"},
				   {"19","蛋糕","波士頓蛋糕","30","30"},{"20","蛋糕","草莓蛋糕","50","30"},
				   {"21","蛋糕","海綿蛋糕","35","20"},{"22","蛋糕","提拉米蘇","100","20"},
				   {"23","蛋糕","起司蛋糕","55","30"},{"24","蛋糕","磅蛋糕","50","20"},
				   {"25","蛋糕","芋泥蛋糕","55","30"},
                                  };

  	    String[][] aemployee = {{"1111","黃曉明","營業部經理","1111"},{"2222","王大安","店長","2222"},
				    {"3333","陳美美","店員","3333"},{"4444","謝小明","店員","4444"},
				    {"5555","林佩佩","店員","5555"},{"6666","潘小七","店員","6666"},
				    {"7777","黃阿翔","店員","7777"},{"8888","蘇小平","店員","8888"},
				    {"9999","汪阿翰","店員","9999"},{"1010","蔡橘子","店員","1010"},
				   };
	
	    

	    for(int x=0; x<25; x++)
	    {	
                loaddata.insertRD_into_TB_product(aproduct[x]);
		if(x==24)
		    JOptionPane.showMessageDialog(null,"在資料庫中, 成功寫入[產品記錄]到product資料表中!");
	    }

	    for(int y=0; y<10; y++)
	    {	
                loaddata.insertRD_into_TB_employee(aemployee[y]);
		if(y==9)
		    JOptionPane.showMessageDialog(null,"在資料庫中, 成功寫入[員工資料]到employee資料表中!");
	    }
	    
	}
	CBMS myBMS = new CBMS();//CBMS: Class Bakery Management System 烘焙坊管理系統類別 
    }
}
             

     