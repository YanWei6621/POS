//�n�J
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class Clogin
{
    //���O���һݪ�����ŧi����
    //�ŧi�إߩһݪ���
    JFrame f = new JFrame();
    Container ct;//�u�������
    JPanel pbg = new JPanel();//�I����
    JPanel p = new JPanel();//�b��.�K�X.�n�J.����
    ImageIcon icon1 = new ImageIcon("bread.png");
    ImageIcon icon2 = new ImageIcon("logo.png");

    JLabel b0 = new JLabel("");//���A��
    JLabel b1 = new JLabel(icon1);
    JLabel b2 = new JLabel("�b��");
    JLabel b3 = new JLabel("�K�X");
    JLabel b4 = new JLabel("�칩��T��޻s�@");
    JLabel b5 = new JLabel("�m �� �M �H �{ �� �z �t ��");
    JLabel b6 = new JLabel(icon2);
    JTextField tf = new JTextField();//�b�����
    JPasswordField pf = new JPasswordField();//�K�X���
    JButton btn1 = new JButton("�n�J");
    JButton btn2 = new JButton("����");

    Font font1 = new Font("�L�n������",Font.BOLD + Font.ITALIC,35);
    Font font2 = new Font("�L�n������",Font.BOLD + Font.ITALIC,15);
    Font font3 = new Font("�L�n������",Font.BOLD ,30);
    Color c1 = new Color(255,218,185);//�������

    //�غc�l:�I�s���غc�l�i�H���ͦ����O������
    Clogin()
    {
	
	//���o�ù��e(w)�P��(h)
	Toolkit tk = Toolkit.getDefaultToolkit();
	Dimension dm = tk.getScreenSize();
	int w = (int)dm.getWidth();
	int h= (int)dm.getHeight();	

	b1.setBounds(355,5,250,200);
	f.add(b1);
	b6.setBounds(860,428,50,50);
	f.add(b6);
	
	//�칩��T��޻s�@
	b4.setBounds(820,474,150,20);
	b4.setFont(font2);
	f.add(b4);

	//�m���M�H�{�޲z�t��
	b5.setBounds(280,200,400,35);
	b5.setFont(font1);
	f.add(b5);
	
	b0.setBounds(835,5,150,25);  //��ܵn�J���G��
	f.add(b0);
	
	//�]�w�e��p�b��.�K�X.�n�J.����
	p.setLayout(null);
	p.setBounds(230,245,500,240);
	p.setBackground(Color.white);
	p.setBorder(BorderFactory.createLineBorder(Color.black,2));

	b2.setBounds(75,30,65,50);//�b��
	b2.setFont(font3);
	p.add(b2);

	b3.setBounds(75,95,65,50);//�K�X
	b3.setFont(font3);
	p.add(b3);

	tf.setBounds(150,30,275,50);//�b����
	tf.setFont(font3);
	tf.setBorder(BorderFactory.createLineBorder(Color.black,2));
	p.add(tf);

	pf.setBounds(150,95,275,50);//�K�X��
	pf.setBorder(BorderFactory.createLineBorder(Color.black,2));
	pf.setFont(font3);
	pf.setBackground(Color.white);
	pf.setForeground(Color.black);
	p.add(pf);

	btn1.setBounds(75,165,155,50);//�n�J
	btn1.setBackground(Color.white);
	btn1.setBorder(BorderFactory.createLineBorder(Color.black,2));
	btn1.setFont(font3);
	p.add(btn1);

	btn2.setBounds(270,165,155,50);//����
	btn2.setBackground(Color.white);
	btn2.setBorder(BorderFactory.createLineBorder(Color.black,2));
	btn2.setFont(font3);
	p.add(btn2);
	f.add(p);
	
	//�]�w����
	ct=f.getContentPane();
	pbg.setLayout(null);
	pbg.setBounds(0,0,960,540);
	pbg.setBackground(c1);
	ct.add(pbg);
	f.setLayout(null);
	f.setBounds((int)(0.25*w),(int)(0.25*h),960,540);
	f.setVisible(true);
	f.setTitle("�m���M�H�{�޲z�t��");
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}