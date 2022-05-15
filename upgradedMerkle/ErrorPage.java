package upgradedMerkle;

import javax.swing.JFrame;
import javax.swing.JLabel;


public class ErrorPage {
    JFrame frame = new JFrame();
    JLabel label = new JLabel();
    
    

    ErrorPage(){
        label.setBounds(0,0,100,50);
        frame.add(label);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frame.setUndecorated(true);
    }
}
