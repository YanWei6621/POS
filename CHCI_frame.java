
 //�פJ�ݭn���U���M��
 import javax.swing.*;
 import java.awt.event.*;
 import java.awt.*;
 import javax.swing.border.*;
 import javax.swing.table.*;
 import java.sql.*;


 
 //�H�����ʼh���O
 //CHCI_frame: Class HumanComputerInteraction_frame (�H������-�ج[���O)

 class CHCI_frame extends JFrame{      //�t�Ϊ�����

     CHCI_menu  myMenu = new CHCI_menu();                  //�D�\���檫��(��JPanel,���t5�ӫ��s)     
     CHCI_OP_panel  myOP_pane = new CHCI_OP_panel();       //�I�\���b�e������(��JPanel,���t����,��r���,���s��)
     CHCI_QR_panel  myQR_pane = new CHCI_QR_panel();       //�w�s�e������(��JPanel,���t����,��r���,���s��)
     CHCI_SR_panel  mySR_pane = new CHCI_SR_panel();       //�P��Z�e������(��JPanel,���t����,��r���,���s��)
     CHCI_CI_panel  myCI_pane = new CHCI_CI_panel();       //�W�U�Z�e������(��JPanel,���t����,��r���,���s��)   
           
     Color c1 = new Color(255,218,185);//�������
     //�غc�l:���OCHCI_frame
     public CHCI_frame(){

         add(myMenu);        //�N[�D�\���檫��]�[�즹������
         add(myOP_pane);     //�N[�I�\���b�e������]�[�즹������
         add(myQR_pane);     //�N[�w�s�e������]�[�즹������
         add(mySR_pane);     //�N[�w�s�e������]�[�즹������
	 add(myCI_pane);     //�N[�W�U�Z�e������]�[�즹������

         myOP_pane.setVisible(true);   //�w�][�I�\���b]�e�����
         myQR_pane.setVisible(false);  //�w�][�w�s]�e������
         mySR_pane.setVisible(false);  //�w�][�w�s]�e������
	 myCI_pane.setVisible(false);  //�w�][�W�U�Z]�e������

         //�t�ε������򥻳]�w
         setTitle("�m���M�H�{�޲z�t��");
	 getContentPane().setBackground(c1);
         setLocation(0,0);
         setSize(1300,750);
         setLayout(null);
         setVisible(false);
         setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

     }

 } //end for: class CHCI_frame

 
 