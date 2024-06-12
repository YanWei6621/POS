//�פJ�ݭn���U���M��
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.border.*;
import java.sql.*;
import java.text.DateFormat;//�۩w�q����ɶ��榡
//�t�ΥD�����O
//CBMS: Class BakeryManagementSystem (�M�H�{�޲z�t��-BMS)
class CBMS
{                    
    //�إߥ��t�Ωһݪ��U�Ӫ���
    CHCI_frame  myFrame = new CHCI_frame();       //�H�����ʼh: �ϥΪ̤�������(myFrame,�̭��S�t��:myFrame.myMenu,myFrame.myOP_pane,myFrame.myQR_pane)
    Clogin myLogin = new Clogin();
    Cmoney money = new Cmoney();		  //���b�e��
    CPD_employee myemployee = new CPD_employee();    
    CDM_dbma myDBMA = new CDM_dbma();             //��ƺ޲z�h: ��Ʈw�ާ@�s������(myDBMA)
    CFD_check myCheck = new CFD_check();          //��¦�h: �ˬd����(myCheck)
    CHCI_backframe  mybackframe = new CHCI_backframe();
    int sid=0;

    //CBMS���غc�l:
    CBMS()
    {
	//�]�w�t�Τ���������O�ѭ��@��[�ƥ��ť�{��]�t�d�B�z��ʧ@
 	myLogin.btn1.addActionListener(loginbtn);
	myLogin.btn2.addActionListener(loginbtn);
	myFrame.myMenu.btna[0][0].addActionListener(ProcessFunSelection);              //�D�\���檺[�I�\���b]���s
	myFrame.myMenu.btna[0][1].addActionListener(ProcessFunSelection);              //�D�\���檺[�w�s]���s
	myFrame.myMenu.btna[0][2].addActionListener(ProcessFunSelection);              //�D�\���檺[�P��]���s
	myFrame.myMenu.btna[0][3].addActionListener(ProcessFunSelection);              //�D�\���檺[�W�U�Z]���s
	myFrame.myMenu.btna[0][4].addActionListener(ProcessFunSelection);              //�D�\���檺[�n�X]���s
	myFrame.myMenu.btna[0][5].addActionListener(ProcessFunSelection);              //�D�\���檺[����]���s
	myFrame.myOP_pane.btnb[0][3].addActionListener(ProcessFunSelection);	       //�I�\���b������[���b]���s
	money.btn.addActionListener(ProcessFunSelection);
	mybackframe.mybackmenu.btna[0][0].addActionListener(ProcessFunSelection);      //��x�\���檺[��s���~]���s
	mybackframe.mybackmenu.btna[0][1].addActionListener(ProcessFunSelection);      //��x�\���檺[���u�W��]���s
	mybackframe.mybackmenu.btna[0][2].addActionListener(ProcessFunSelection);      //��x�\���檺[�s�W���u]���s
	mybackframe.mybackmenu.btna[0][3].addActionListener(ProcessFunSelection);      //��x�\���檺[�n�X]���s
	mybackframe.myback2_pane.btnn.addActionListener(ProcessSaveEmployeeRecord);
    }
      
    //�ƥ��ť�{��: �B�z�D�\������
    public ActionListener ProcessFunSelection = new ActionListener()
    {
	public void actionPerformed(ActionEvent e)
	{
	    if( e.getSource() == myFrame.myMenu.btna[0][0] )//�I�\���b
	    {
		myFrame.myOP_pane.setVisible(true);        //���[�I�\���b]�ާ@�e��
		myFrame.myOP_pane.clearPane();             //�M��[�I�\���b]�ާ@�e��
		myFrame.myQR_pane.setVisible(false);       //����[�w�s]�d�ߵe�� 
		myFrame.mySR_pane.setVisible(false);        //����[�P��]�ާ@�e��
		myFrame.mySR_pane.clearPane();             //�M��[�P��]�ާ@�e��     
		myFrame.myCI_pane.setVisible(false);        //����[�W�U�Z]�ާ@�e��
		myFrame.myCI_pane.clearPane();             //�M��[�W�U�Z]�ާ@�e��
            }

            if( e.getSource() == myFrame.myMenu.btna[0][1] )//�w�s
	    {
		myFrame.myQR_pane.DFMO.getDataVector().removeAllElements();
		myFrame.myQR_pane.datachange();
		myFrame.myOP_pane.setVisible(false);        //����[�I�\���b]�ާ@�e��
		myFrame.myOP_pane.clearPane();             //�M��[�I�\���b]�ާ@�e��
		myFrame.myQR_pane.setVisible(true);       //���[�w�s]�d�ߵe��
		myFrame.mySR_pane.setVisible(false);        //����[�P��]�ާ@�e��
		myFrame.mySR_pane.clearPane();             //�M��[�P��]�ާ@�e��      
		myFrame.myCI_pane.setVisible(false);        //����[�W�U�Z]�ާ@�e��
		myFrame.myCI_pane.clearPane();             //�M��[�W�U�Z]�ާ@�e��
            }

	    if( e.getSource() == myFrame.myMenu.btna[0][2] )//�P��
	    {
		myFrame.mySR_pane.sellDFMO.getDataVector().removeAllElements();
		myFrame.mySR_pane.detailDFMO.setRowCount(0);
		myFrame.mySR_pane.selldatachange();
		myFrame.myOP_pane.setVisible(false);        //����[�I�\���b]�ާ@�e��
		myFrame.myOP_pane.clearPane();             //�M��[�I�\���b]�ާ@�e��
		myFrame.myQR_pane.setVisible(false);       //����[�w�s]�d�ߵe��
		myFrame.mySR_pane.setVisible(true);        //���[�P��]�ާ@�e��
		myFrame.mySR_pane.clearPane();             //�M��[�P��]�ާ@�e��      
		myFrame.myCI_pane.setVisible(false);        //����[�W�U�Z]�ާ@�e��
		myFrame.myCI_pane.clearPane();             //�M��[�W�U�Z]�ާ@�e��
            }

	    if( e.getSource() == myFrame.myMenu.btna[0][3] )//�W�U�Z
	    {
		myFrame.myOP_pane.setVisible(false);        //����[�I�\���b]�ާ@�e��
		myFrame.myOP_pane.clearPane();             //�M��[�I�\���b]�ާ@�e��
                myFrame.myQR_pane.setVisible(false);       //����[�w�s]�d�ߵe��
		myFrame.mySR_pane.setVisible(false);        //����[�P��]�ާ@�e��
		myFrame.mySR_pane.clearPane();             //�M��[�P��]�ާ@�e��      
		myFrame.myCI_pane.setVisible(true);        //���[�W�U�Z]�ާ@�e��
                myFrame.myCI_pane.clearPane();             //�M��[�W�U�Z]�ާ@�e��
            }

	    if(e.getSource() == myFrame.myMenu.btna[0][4] )//�n�X
	    {
		int ans = JOptionPane.showConfirmDialog(null,
			  "�O�_�n�n�X?",
			  "�T�{",JOptionPane.YES_NO_OPTION,
			  JOptionPane.QUESTION_MESSAGE);
	    	if(ans == JOptionPane.YES_OPTION)
	    	{
		    myLogin.f.setVisible(true);
		    myFrame.setVisible(false);
		    myFrame.myOP_pane.setVisible(false);
                    myFrame.myQR_pane.setVisible(false);       
		    myFrame.mySR_pane.setVisible(false);
		    myFrame.myCI_pane.setVisible(false);
		    myFrame.myOP_pane.clearPane();
			
		    
	   	}
	    }

            if( e.getSource() == myFrame.myMenu.btna[0][5] ) //����
            {
		int ans = JOptionPane.showConfirmDialog(null,
			  "�O�_�n����?",
			  "�T�{",JOptionPane.YES_NO_OPTION,
			  JOptionPane.QUESTION_MESSAGE);
	    	if(ans == JOptionPane.YES_OPTION)
	    	{
		    System.exit(0);
	   	}                            
            }

	    int sum;
	    int sid;
    	    String sumStr;
            if( e.getSource() == myFrame.myOP_pane.btnb[0][3] ) //���b
            {
		sum=0;		
		String[] detail=new String[5];
		String pname=new String();
		for(int i=0;i<=myFrame.myOP_pane.index;i++)
		{
		    sum=sum+Integer.parseInt((String)myFrame.myOP_pane.table.getValueAt(i,3)); 
		}
		sumStr=String.valueOf(sum);
		myFrame.myOP_pane.lb2.setText(sumStr);
		money.f.setVisible(true);
		money.lb2.setText(sumStr);
		String[] sell=new String[2];
		sell[0]=myFrame.myCI_pane.labd.getText();
		sell[1]=sumStr;
		myDBMA.insert_sell(sell);
		sid=myDBMA.findsid();

		for(int i=0;i<=myFrame.myOP_pane.index;i++)
		{
		    detail[0]=String.valueOf(sid);
		    detail[1]=(String)myFrame.myOP_pane.table.getValueAt(i,0); 
		    detail[2]=(String)myFrame.myOP_pane.table.getValueAt(i,1); 
		    detail[3]=(String)myFrame.myOP_pane.table.getValueAt(i,2); 
		    detail[4]=(String)myFrame.myOP_pane.table.getValueAt(i,3);
		    myDBMA.insert_detail(detail);
		    myDBMA.update_product(detail[0],detail[1]);	
		}

	    }

	    //���b��s
	    if(e.getSource() == money.btn)//�T�w
	    {
		myFrame.myOP_pane.lb2.setText("");
		money.lb2.setText("");
		money.tf.setText("");
		money.lb5.setText("");
		money.lbt.setText("");
	    	money.f.setVisible(false);
		for(int i=0 ; i<=myFrame.myOP_pane.index ; i++)
		{
		    for(int j=0;j<4;j++)
		    {
			myFrame.myOP_pane.table.setValueAt(null,i,j);
		    }
		}
		myFrame.myOP_pane.index=0;

		
	    }	    

	    if( e.getSource() == mybackframe.mybackmenu.btna[0][0] )//��s���~
	    {
		mybackframe.myback1_pane.setVisible(true);        //���[��s���~]�ާ@�e��
		mybackframe.myback3_pane.setVisible(false);       //����[���u�W��]�ާ@�e��   
		mybackframe.myback2_pane.setVisible(false);       //����[�s�W���u]�ާ@�e��
		mybackframe.myback2_pane.clearPane();
            }

	    if( e.getSource() == mybackframe.mybackmenu.btna[0][1] )//���u�W��
	    {

		mybackframe.myback3_pane.DFMO.getDataVector().removeAllElements();
		mybackframe.myback3_pane.datachange();
		mybackframe.myback1_pane.setVisible(false);       //����[��s���~]�ާ@�e�� 
		mybackframe.myback3_pane.setVisible(true);        //���[���u�W��]�ާ@�e��  
		mybackframe.myback2_pane.setVisible(false);       //����[�s�W���u]�ާ@�e��
		mybackframe.myback2_pane.clearPane();
            }

	    if( e.getSource() == mybackframe.mybackmenu.btna[0][2] )//�s�W���u
	    {
		mybackframe.myback1_pane.setVisible(false);       //����[��s���~]�ާ@�e��
		mybackframe.myback3_pane.setVisible(false);       //����[���u�W��]�ާ@�e��    
		mybackframe.myback2_pane.setVisible(true);        //���[�s�W���u]�ާ@�e��
		
            }

	    if( e.getSource() == mybackframe.mybackmenu.btna[0][3] )//�n�X
	    {
		int ans = JOptionPane.showConfirmDialog(null,
			  "�O�_�n�n�X?",
			  "�T�{",JOptionPane.YES_NO_OPTION,
			  JOptionPane.QUESTION_MESSAGE);
	    	if(ans == JOptionPane.YES_OPTION)
	    	{
		    myLogin.f.setVisible(true);
		    mybackframe.setVisible(false);
	   	}
	    }
 	}    
    };//end ProcessFunSelection

    //�n�J
    public ActionListener loginbtn = new ActionListener()
    {
	public void actionPerformed(ActionEvent e)
	{
	    if(e.getSource() == myLogin.btn1)//�n�J
	    {
		String s1 = myLogin.tf.getText().trim();
		String s2 = myLogin.pf.getText().trim();
		if(s1.equals("front") && s2.equals("front"))//�e�x
		{  
		    myLogin.f.setVisible(false);
		    myFrame.setVisible(true);
		    myFrame.myOP_pane.setVisible(true);
		    myLogin.pf.setText("");
		    myLogin.b0.setText("");
		}
		else if(s1.equals("back") && s2.equals("back"))//��x
		{  
		    myLogin.f.setVisible(false);
		    mybackframe.setVisible(true);
		    myLogin.pf.setText("");
		    myLogin.b0.setText("");
		}
		else if (s1.length() == 0 && s2.length() == 0)
		    myLogin.b0.setText("����J�b����K�X");
		else if (s1.length() == 0)
		    myLogin.b0.setText("����J�b��");
		else if (s2.length() == 0)
		    myLogin.b0.setText("����J�K�X"); 
		else 
		    myLogin.b0.setText("�b���αK�X���~"); 
		
	    }
	    if(e.getSource() == myLogin.btn2)//����
	    {
		int ans = JOptionPane.showConfirmDialog(null,
			  "�O�_�n����?",
			  "�T�{",JOptionPane.YES_NO_OPTION,
			  JOptionPane.QUESTION_MESSAGE);
	    	if(ans == JOptionPane.YES_OPTION)
	    	{
		    System.exit(0);
	   	}
	    }
	}
    }; //end for : loginbtn

    //�ƥ��ť�{��: �B�z���u����x�s
    public ActionListener ProcessSaveEmployeeRecord = new ActionListener()
    {
    	public void actionPerformed(ActionEvent e)
	{
            boolean checkPass = true;            //�ΨӰO��[��J�����]�ˬd���G
            String eidString = mybackframe.myback2_pane.tf[0].getText().trim();  //���o[��J�����]����[���u�s���r��] (��:trim()��k�|��r��᭱�ťհ���)
            String enameString = mybackframe.myback2_pane.tf[1].getText();    //���o[��J�����]����[���u�m�W�r��]
            String positionString = mybackframe.myback2_pane.tf[2].getText();    //���o[��J�����]����[���u¾��r��]
            String passwordString = mybackframe.myback2_pane.tf[3].getText().trim();  //���o[��J�����]����[���u�K�X�r��]

            if( myCheck.checkNumber( eidString ) == false )//�Q���ˬd����(myCheck)��checkNumber()��k,�ˬdchineseString�O�_�����T���ƭȮ榡,�p:98,80,...��
            {  
            	checkPass = false;
                JOptionPane.showMessageDialog(null,"[���u�s��] ��J��ƿ��~!","�ާ@ĵ�T",JOptionPane.ERROR_MESSAGE);
            }

            if( myCheck.checkNumber( passwordString ) == false )
            {  //�Q���ˬd����(myCheck)��checkNumber()��k,�ˬdenglishString�O�_�����T���ƭȮ榡,�p:98,80,...��
            	checkPass = false;
                JOptionPane.showMessageDialog(null,"[���u�K�X] ��J��ƿ��~!","�ާ@ĵ�T",JOptionPane.ERROR_MESSAGE);
            }

            if(  enameString.length() == 0 )
            {    //�ˬdenameString�O�_����J����r��,length()���p��r����פ�k,�Y���׬�0�h����J���
            	checkPass = false;
                JOptionPane.showMessageDialog(null,"[���u�m�W] �ťե���J���!","�ާ@ĵ�T",JOptionPane.ERROR_MESSAGE);
            }

	    if(  positionString.length() == 0 )
            {    //�ˬdpositionString�O�_����J����r��,length()���p��r����פ�k,�Y���׬�0�h����J���
            	checkPass = false;
                JOptionPane.showMessageDialog(null,"[���u¾��] �ťե���J���!","�ާ@ĵ�T",JOptionPane.ERROR_MESSAGE);
            }

            //�p�G�W�z�T���ˬd���S�o�{���~,�hcheckPass�|����true,�Y�q�L�ˬd,�]���N���u����Ƴ]�w��myemployee���󤤹����ݩʽ�  
            if( checkPass == true )
	    {
                myemployee.seteid( Integer.parseInt(eidString) ); //Integer.parseInt()�O�N�r���ন��ƪ���k
            	myemployee.setename(enameString);
                myemployee.setposition(positionString);    
                myemployee.setpassword( Integer.parseInt(passwordString) );
		myDBMA.insertRD_into_TB_employee(myemployee);   //�N����ǤJ�x�s���u������k(insertRD_into_TB_employee())�h�x�s���u�������Ʈw
		mybackframe.myback2_pane.clearPane();
            }
	}    
    };//end for :ProcessSaveEmployeeRecord

} //end for: class CBMS
    

 