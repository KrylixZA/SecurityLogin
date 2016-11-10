package Logic;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AntiRobot {
    private final String [][] NUMBERS = {{"Resources/number1.jpg", "4076"}, {"Resources/number2.jpg", "275"}, {"Resources/number3.jpg", "318"}, {"Resources/number4.jpg", "136"}, {"Resources/number5.jpg", "49"}};

    public AntiRobot(){
        
    }
    
    public boolean displayAntiRobot(){
        int randomIndex = (int)(Math.random()*5);
        ImageIcon numberImage = new ImageIcon(getClass().getClassLoader().getResource(NUMBERS[randomIndex][0]));
        Image image = numberImage.getImage();
        Image newImage = image.getScaledInstance(80, 50, java.awt.Image.SCALE_SMOOTH);
        numberImage = new ImageIcon(newImage);
        
        JTextField txtNumber = new JTextField();
        JLabel lblNumber = new JLabel("");
        lblNumber.setIcon(numberImage);

        final JComponent[] loginComponents = {
            lblNumber, txtNumber, new JLabel("Are you sure?")
        };

        int response = JOptionPane.showConfirmDialog(null, loginComponents, "I'm Not A Robot", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        
        if(response == JOptionPane.YES_OPTION){
            if(txtNumber.getText().equals(NUMBERS[randomIndex][1]))
                return true;
        }else if (response == JOptionPane.NO_OPTION){
            return false;
        }
        
        return false;
    }
}
