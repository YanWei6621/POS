//�פJ�ݭn���U���M��
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.sql.*;


 
//�H�����ʼh���O
//CHCI_backframe: Class HumanComputerInteraction_frame (�H������-�ج[���O)

class CHCI_backframe extends JFrame  //�t�Ϊ�����
{      
    CHCI_backmenu  mybackmenu = new CHCI_backmenu();           //�D�\���檫��(��JPanel,���t3�ӫ��s)     
    CHCI_back1_panel  myback1_pane = new CHCI_back1_panel();    //���e������(��JPanel,���t����,��r���,���s��)
    CHCI_back2_panel  myback2_pane = new CHCI_back2_panel();    //�s�W�e������(��JPanel,���t����,��r���,���s��)
    CHCI_back3_panel  myback3_pane = new CHCI_back3_panel();    //�H�Ƶe������(��JPanel,���t����,��r���,���s��)

    Color c1 = new Color(255,218,185);//�������
    //�غc�l:���OCHCI_frame
    public CHCI_backframe()
    {
	add(mybackmenu);        //�N[�D�\���檫��]�[�즹������
        add(myback1_pane);     //�N[�I�\���b�e������]�[�즹������
        add(myback2_pane);     //�N[�w�s�e������]�[�즹������
	add(myback3_pane);     //�N[�w�s�e������]�[�즹������

        myback1_pane.setVisible(true);   //�w�][���]�e�����
        myback2_pane.setVisible(false);  //�w�][�s�W]�e������
	myback3_pane.setVisible(false);  //�w�][�s�W]�e������

        //�t�ε������򥻳]�w
        setTitle("�m���M�H�{�޲z�t�Ϋ�x");
	getContentPane().setBackground(c1);
        setLocation(0,0);
        setSize(700,750);
        setLayout(null);
        setVisible(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

} //end for: class CHCI_backframe

 
 