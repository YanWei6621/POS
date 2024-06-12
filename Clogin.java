//登入
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class Clogin
{
    //類別中所需的物件宣告產生
    //宣告建立所需物件
    JFrame f = new JFrame();
    Container ct;//真實視窗底
    JPanel pbg = new JPanel();//背景色
    JPanel p = new JPanel();//帳號.密碼.登入.取消
    ImageIcon icon1 = new ImageIcon("bread.png");
    ImageIcon icon2 = new ImageIcon("logo.png");

    JLabel b0 = new JLabel("");//狀態欄
    JLabel b1 = new JLabel(icon1);
    JLabel b2 = new JLabel("帳號");
    JLabel b3 = new JLabel("密碼");
    JLabel b4 = new JLabel("科鼎資訊科技製作");
    JLabel b5 = new JLabel("鄉 香 烘 焙 坊 管 理 系 統");
    JLabel b6 = new JLabel(icon2);
    JTextField tf = new JTextField();//帳號欄位
    JPasswordField pf = new JPasswordField();//密碼欄位
    JButton btn1 = new JButton("登入");
    JButton btn2 = new JButton("取消");

    Font font1 = new Font("微軟正黑體",Font.BOLD + Font.ITALIC,35);
    Font font2 = new Font("微軟正黑體",Font.BOLD + Font.ITALIC,15);
    Font font3 = new Font("微軟正黑體",Font.BOLD ,30);
    Color c1 = new Color(255,218,185);//粉撲桃色

    //建構子:呼叫此建構子可以產生此類別的物件
    Clogin()
    {
	
	//取得螢幕寬(w)與高(h)
	Toolkit tk = Toolkit.getDefaultToolkit();
	Dimension dm = tk.getScreenSize();
	int w = (int)dm.getWidth();
	int h= (int)dm.getHeight();	

	b1.setBounds(355,5,250,200);
	f.add(b1);
	b6.setBounds(860,428,50,50);
	f.add(b6);
	
	//科鼎資訊科技製作
	b4.setBounds(820,474,150,20);
	b4.setFont(font2);
	f.add(b4);

	//鄉香烘焙坊管理系統
	b5.setBounds(280,200,400,35);
	b5.setFont(font1);
	f.add(b5);
	
	b0.setBounds(835,5,150,25);  //顯示登入結果用
	f.add(b0);
	
	//設定容器p帳號.密碼.登入.取消
	p.setLayout(null);
	p.setBounds(230,245,500,240);
	p.setBackground(Color.white);
	p.setBorder(BorderFactory.createLineBorder(Color.black,2));

	b2.setBounds(75,30,65,50);//帳號
	b2.setFont(font3);
	p.add(b2);

	b3.setBounds(75,95,65,50);//密碼
	b3.setFont(font3);
	p.add(b3);

	tf.setBounds(150,30,275,50);//帳號欄
	tf.setFont(font3);
	tf.setBorder(BorderFactory.createLineBorder(Color.black,2));
	p.add(tf);

	pf.setBounds(150,95,275,50);//密碼欄
	pf.setBorder(BorderFactory.createLineBorder(Color.black,2));
	pf.setFont(font3);
	pf.setBackground(Color.white);
	pf.setForeground(Color.black);
	p.add(pf);

	btn1.setBounds(75,165,155,50);//登入
	btn1.setBackground(Color.white);
	btn1.setBorder(BorderFactory.createLineBorder(Color.black,2));
	btn1.setFont(font3);
	p.add(btn1);

	btn2.setBounds(270,165,155,50);//取消
	btn2.setBackground(Color.white);
	btn2.setBorder(BorderFactory.createLineBorder(Color.black,2));
	btn2.setFont(font3);
	p.add(btn2);
	f.add(p);
	
	//設定視窗
	ct=f.getContentPane();
	pbg.setLayout(null);
	pbg.setBounds(0,0,960,540);
	pbg.setBackground(c1);
	ct.add(pbg);
	f.setLayout(null);
	f.setBounds((int)(0.25*w),(int)(0.25*h),960,540);
	f.setVisible(true);
	f.setTitle("鄉香烘焙坊管理系統");
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}