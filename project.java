//�פJ�ݭn���U���M��
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.border.*;
import java.sql.*;

//�D�{��: project.java

public class project
{
    public static void main(String[] args)
    {
	if( args.length == 2 && args[0].equals("-p") && args[1].equals("setupdb") )
	{
	    CDM_dbma myDBMA = new CDM_dbma();
            myDBMA.createDB();             //�إ߸�Ʈwbmsdb,�u���b�U����Ѽ� -p setupdb �ɰ���
            myDBMA.createTB_product();     //�b��Ʈwbmsdb�إ߸�ƪ�product
            myDBMA.createTB_employee();     //�b��Ʈwbmsdb�إ߸�ƪ�employee
            myDBMA.createTB_clockin();     //�b��Ʈwbmsdb�إ߸�ƪ�clockin
            myDBMA.createTB_sell();     //�b��Ʈwbmsdb�إ߸�ƪ�sell
            myDBMA.createTB_detail();     //�b��Ʈwbmsdb�إ߸�ƪ�detail
	    loadDATA loaddata = new loadDATA();
	    
	    String[][] aproduct = {{"1","�ѥ]","���F�ѥ]","35","100"},{"2","�ѥ]","�����ѥ]","30","150"},
				   {"3","�ѥ]","�����ѥ]","25","100"},{"4","�ѥ]","�A���R�q","40","150"},
				   {"5","�ѥ]","������","30","150"},{"6","�ѥ]","�\�]","25","150"},
				   {"7","�ѥ]","�_�p�ѥ]","25","150"},{"8","�ѥ]","�����ѥ]","36","100"},
				   {"9","�ѥ]","���G","40","80"},{"10","�ѥ]","�զR�q","30","200"},
				   {"11","�ѥ]","���P�ѥ]","30","125"},{"12","�ѥ]","���[���j","36","100"},
				   {"13","�ѥ]","�k���ѥ]","45","100"},{"14","�ѥ]","��������","70","50"},
				   {"15","�ѥ]","�M���_�q","38","50"},{"16","�ѥ]","��������","40","50"},
				   {"17","�J�|","�´˪L�J�|","55","20"},{"18","�J�|","�ŹT�J�|","60","30"},
				   {"19","�J�|","�i�h�y�J�|","30","30"},{"20","�J�|","����J�|","50","30"},
				   {"21","�J�|","�����J�|","35","20"},{"22","�J�|","���Ԧ�Ĭ","100","20"},
				   {"23","�J�|","�_�q�J�|","55","30"},{"24","�J�|","�S�J�|","50","20"},
				   {"25","�J�|","���d�J�|","55","30"},
                                  };

  	    String[][] aemployee = {{"1111","�����","��~���g�z","1111"},{"2222","���j�w","����","2222"},
				    {"3333","������","����","3333"},{"4444","�¤p��","����","4444"},
				    {"5555","�L�ب�","����","5555"},{"6666","��p�C","����","6666"},
				    {"7777","������","����","7777"},{"8888","Ĭ�p��","����","8888"},
				    {"9999","�L����","����","9999"},{"1010","����l","����","1010"},
				   };
	
	    

	    for(int x=0; x<25; x++)
	    {	
                loaddata.insertRD_into_TB_product(aproduct[x]);
		if(x==24)
		    JOptionPane.showMessageDialog(null,"�b��Ʈw��, ���\�g�J[���~�O��]��product��ƪ�!");
	    }

	    for(int y=0; y<10; y++)
	    {	
                loaddata.insertRD_into_TB_employee(aemployee[y]);
		if(y==9)
		    JOptionPane.showMessageDialog(null,"�b��Ʈw��, ���\�g�J[���u���]��employee��ƪ�!");
	    }
	    
	}
	CBMS myBMS = new CBMS();//CBMS: Class Bakery Management System �M�H�{�޲z�t�����O 
    }
}
             

     