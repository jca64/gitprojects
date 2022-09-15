package passwordStrengthTester;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.*;

public class passwordGUI extends JFrame {
    String userInput = "";
    JPanel jp = new JPanel();
    JLabel jl = new JLabel();
    JTextField jt = new JTextField(30);
    JButton jb = new JButton("Enter");

public passwordGUI() {
    setTitle("Password Strength Tester");
    setSize(200,200);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    jp.add(jt);
        
    jt.addActionListener(new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {
            String input = jt.getText();
            userInput = input;
            jl.setText(passwordStrengthTester.passwordStrengthTester(userInput));
        }
    });

    jp.add(jb);
    jb.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            String input = jt.getText();
            userInput = input;
            jl.setText(passwordStrengthTester.passwordStrengthTester(userInput));
        }
    });


    jp.add(jl);
    add(jp);
}
    public static void main(String[] args) {
        passwordGUI p = new passwordGUI();
    }
}
