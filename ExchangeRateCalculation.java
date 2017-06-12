/**
 * Created by chentao on 17/4/7.
 */
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Color;
import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.String;
public class ExchangeRateCalculation extends JFrame implements ActionListener{
    //定义组件
    JPanel jp1,jp2,jp3,jp4,jp5,jp6;//面板
    JLabel jlb1,jlb2,jlb3,jlb4,jlb5;//标签
    JButton jb1,jb2,jb3,jb4;//按钮
    JTextArea t1,t2,t3;
    JTextField jtf1;//文本
    double rate;
    String time;
    boolean ee;
    //构造函数
    public ExchangeRateCalculation(){
        //创建面板
        jp1=new JPanel();
        jp2=new JPanel();
        jp3=new JPanel();
        jp4=new JPanel();
        jp5=new JPanel();
        jp6=new JPanel();
        //创建标签
        jlb1=new JLabel("人民币金额:￥");
        jlb1.setFont(new   java.awt.Font("Dialog",   1,   15));
        jlb2=new JLabel("美 元金 额:$");
        jlb2.setFont(new   java.awt.Font("Dialog",   1,   15));
        jlb4=new JLabel("汇          率:");
        jlb4.setFont(new   java.awt.Font("Dialog",   1,   15));
        jlb5=new JLabel("汇率更新时间:");
        jlb5.setFont(new   java.awt.Font("Dialog",   1,   15));
        jlb3=new JLabel("Design by B14040723 陈涛");
        jlb3.setFont(new   java.awt.Font("Dialog",   0,   10));
        jlb3.setForeground(java.awt.Color.BLUE);
        //创建按钮
        jb1=new JButton("计算");
        jb2=new JButton("重置");
        jb3=new JButton("关闭");
        jb4=new JButton("获得汇率");
        //设置监听
        jb1.addActionListener(this);
        jb2.addActionListener(this);
        jb3.addActionListener(this);
        jb4.addActionListener(this);
        //创建文本框
        jtf1=new JTextField(10);
        t1 = new JTextArea("  点击获得最新汇率",1,10);
        t2 = new JTextArea("  请输入人民币金额",1,10);
        t3 = new JTextArea("  汇率更新时间",1,10);
        //设置布局管理
        this.setLayout(new GridLayout(6, 1));//网格式布局
        //加入各个组件
        jp1.add(jlb1);
        jp1.add(jtf1);
        jp2.add(jlb4);
        jp2.add(t1);
        jp6.add(jlb5);
        jp6.add(t3);
        jp3.add(jlb2);
        jp3.add(t2);
        jp4.add(jb1);
        jp4.add(jb2);
        jp4.add(jb4);
        jp4.add(jb3);
        jp5.add(jlb3);
        //加入到JFrame
        this.add(jp1);
        this.add(jp2);
        this.add(jp6);
        this.add(jp3);
        this.add(jp4);
        this.add(jp5);
        //设置窗体
        this.setTitle("汇率计算");//窗体标签
        this.setSize(400, 300);//窗体大小
        this.setLocationRelativeTo(null);//在屏幕中间显示(居中显示)
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//退出关闭JFrame
        this.setVisible(true);//显示窗体
        //锁定窗体
        this.setVisible(true);
        this.setResizable(false);
    }
    public void clear()
    {
        jtf1.setText("");
        t1.setText("  点击获得最新汇率");
        t2.setText("  请输入人民币金额");
        t3.setText("  汇率更新时间");
    }
    public double Caluate(double x,double y)
    {
        return x/y;
    }
    public boolean isNumeric(String str){
        try{
            double x = Double.parseDouble(str);
        }
        catch (NumberFormatException f)
        {
            return false;
        }
        return true;
    }
    public double getlatestrate(){
        return rate;
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "计算") {
            while (!isNumeric(t1.getText())) {
                JOptionPane.showMessageDialog(null, "请输写汇率或者点击自动获取最新汇率！" , "提示消息" , JOptionPane.WARNING_MESSAGE);
                break;
            }
            while(!isNumeric(jtf1.getText()))
            {
                JOptionPane.showMessageDialog(null, "请输入正确数字！" , "提示消息" , JOptionPane.WARNING_MESSAGE);
                break;
            }
            if (isNumeric(t1.getText())&&isNumeric(jtf1.getText()))
            {
                double huilv = Double.parseDouble(t1.getText());
                double renmingbi = Double.parseDouble(jtf1.getText());
                String y = Caluate(renmingbi, huilv) + "";
                t2.setText(y);
            }
        }
        if (e.getActionCommand() == "重置") {
            clear();
        }
        if (e.getActionCommand() == "获得汇率") {
            while(!this.ee)
            {
                JOptionPane.showMessageDialog(null, "获取汇率失败！\n再次尝试或者输入汇率" , "提示消息" , JOptionPane.WARNING_MESSAGE);
                break;
            }
            if (this.ee) {
                t1.setText(getlatestrate() + "");
                t3.setText(time);
            }

        }
        if (e.getActionCommand() == "关闭") {
            System.exit(1);
        }
    }
    public static void main(String[] args) {
        ExchangeRateCalculation win=new ExchangeRateCalculation();
        GetLatestRate m = new GetLatestRate();
        m.getrate();
        win.rate=Double.parseDouble(m.ratehuilv);
        win.time=m.ratetime;
        win.ee=m.warn;
    }
}


