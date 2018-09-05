package survey;

import java.awt.*;
import static java.awt.Event.LEFT;
import javax.swing.*;
import java.text.NumberFormat;
import java.io.IOException;
import java.nio.file.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Survey extends JFrame {

    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JLabel countryLabel;
    private JComboBox countryComboBox;
    private JRadioButton brownRadioButton;
    private JRadioButton greenRadioButton;
    private JRadioButton blueRadioButton;
    private JRadioButton otherRadioButton;
    private JCheckBox htmlCheckBox;
    private JCheckBox sqlCheckBox;
    private JCheckBox javaCheckBox;
    private JCheckBox androidCheckBox;
    private JCheckBox pythonCheckBox;
    private JTextField priceTextField;
    private JButton submitButton;
    private JButton exitButton;

    public Survey() {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException |
                 IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.out.println(e);
        }
        initComponents();
    }

    private void initComponents() {
        setTitle("Student Survey");
        setLocationByPlatform(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    
        
                firstNameLabel = new JLabel("First Name:");
                lastNameLabel = new JLabel ("Last Name:");
                firstNameField = new JTextField();
                lastNameField = new JTextField();
                
                Dimension dim = new Dimension(150, 20);
                firstNameField.setPreferredSize(dim);
                firstNameField.setMinimumSize(dim);
                lastNameField.setPreferredSize(dim);
                lastNameField.setMinimumSize(dim);
                String[] countries = null;
               try{
                   countries = readCountries();
               }
               catch(IOException e)
               {
                  
               }
                countryLabel = new JLabel("Country:");
                countryComboBox = new JComboBox(countries);
        
                JPanel nameAndCountryPanel = new JPanel();
                nameAndCountryPanel.setLayout(new GridBagLayout());
                nameAndCountryPanel.add(firstNameLabel, getConstraints(0,0));
                nameAndCountryPanel.add(firstNameField, getConstraints(1,0));
                nameAndCountryPanel.add(lastNameLabel, getConstraints(0,1));
                nameAndCountryPanel.add(lastNameField, getConstraints(1,1));
                nameAndCountryPanel.add(countryLabel, getConstraints(0,2));
                nameAndCountryPanel.add(countryComboBox, getConstraints(1,2));
                
		brownRadioButton = new JRadioButton("Brown");
		greenRadioButton = new JRadioButton("Green");
		blueRadioButton = new JRadioButton("Blue");
                otherRadioButton = new JRadioButton("Other");
		
		ButtonGroup eyeColorGroup = new ButtonGroup();
		eyeColorGroup.add(brownRadioButton);
		eyeColorGroup.add(greenRadioButton);
		eyeColorGroup.add(blueRadioButton);
                eyeColorGroup.add(otherRadioButton);
                
                JPanel eyeColorPanel = new JPanel();
                eyeColorPanel.setBorder(BorderFactory.createTitledBorder("Eye Color:"));
		eyeColorPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
                eyeColorPanel.setSize(100, 100);
                eyeColorPanel.add(brownRadioButton);
                eyeColorPanel.add(greenRadioButton);
                eyeColorPanel.add(blueRadioButton);
                eyeColorPanel.add(otherRadioButton);
                
                htmlCheckBox = new JCheckBox("HTML");
                sqlCheckBox = new JCheckBox("SQL");
                javaCheckBox = new JCheckBox("Java");
                androidCheckBox = new JCheckBox("Android");
                pythonCheckBox = new JCheckBox("Python");
                
                JPanel skillsPanel = new JPanel();
                skillsPanel.setBorder(BorderFactory.createTitledBorder("Programming Skills:"));
                skillsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
                skillsPanel.add(htmlCheckBox);
                skillsPanel.add(sqlCheckBox);
                skillsPanel.add(javaCheckBox);
                skillsPanel.add(androidCheckBox);
                skillsPanel.add(pythonCheckBox);
                
                JPanel eyeAndSkillsPanel = new JPanel();
                eyeAndSkillsPanel.setLayout(new GridBagLayout());
                eyeAndSkillsPanel.add(eyeColorPanel, getConstraints(0,0));
                eyeAndSkillsPanel.add(skillsPanel, getConstraints(0,1));
               
                submitButton = new JButton("Submit");
                submitButton.addActionListener(event-> submitButtonClicked());
                
                exitButton = new JButton("Exit");
                exitButton.addActionListener(event-> exitButtonClicked());
                
                JPanel buttonPanel = new JPanel();
                buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
                buttonPanel.add(submitButton);
                buttonPanel.add(exitButton);
                
		add(nameAndCountryPanel, BorderLayout.NORTH);
                add(eyeAndSkillsPanel, BorderLayout.CENTER);
                add(buttonPanel, BorderLayout.SOUTH);
                setSize(350,250);
                setVisible(true);
    }

     // helper method for getting a GridBagConstraints object
    private GridBagConstraints getConstraints(int x, int y) {
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(5, 5, 0, 5);
        c.gridx = x;
        c.gridy = y;
        return c;
    }
    
    private void submitButtonClicked()
    {
        boolean good = true;
        String message = "Thank you for taking our survey!\n\nHere's the data you entered:\nName: ";
        
        if(!firstNameField.getText().isEmpty())
        {
            message += firstNameField.getText() + " ";
        }
        else
        {
            good = false;
        }
        
        if(!lastNameField.getText().isEmpty())
        {
            message += lastNameField.getText();
        }
        else
        {
            good = false;
        }
        
        message += "\nCountry: ";
        
        message += countryComboBox.getSelectedItem();
        
        message += "\nEye Color: ";
        
        if(brownRadioButton.isSelected())
        {
            message += "Brown\n";
        }
        else if(greenRadioButton.isSelected())
        {
            message += "Green\n";
        }
        else if(blueRadioButton.isSelected())
        {
            message += "Blue\n";
        }
        else if(otherRadioButton.isSelected())
        {
            message += "Other\n";
        }
        else
        {
            good = false;
        }
        
        message += "Skills: ";
        
        if(htmlCheckBox.isSelected())
        {
            message += "HTML, ";
        }
        
        if(sqlCheckBox.isSelected())
        {
            message += "SQL, ";
        }
        
        if(javaCheckBox.isSelected())
        {
            message += "Java, ";
        }
        
        if(pythonCheckBox.isSelected())
        {
            message += "Python, ";
        }
        
        if(androidCheckBox.isSelected())
        {
            message += "Android, ";
        }
        
        message = message.substring(0, message.length() - 2);
        
        if(!good)
        {
            message = "Please finish entering all fields before submitting.";
        }
        
        JOptionPane.showMessageDialog(this, message);
        
        try{
            writeFile(message);
        }
        catch(IOException e)
        {
            
        }
    }
    
    private void exitButtonClicked()
    {
        System.exit(0);
    }
    
    private String[] readCountries() throws IOException
    {
        String dirString = "C:\\Users\\kmofp\\Documents\\NetBeansProjects\\Survey";
                Path dirPath = Paths.get(dirString);
      
                if(Files.notExists(dirPath))
                {
                    Files.createDirectories(dirPath);
                }
      
                String fileString = "countries.txt";
                Path filePath = Paths.get(dirString, fileString);
      
                if(Files.notExists(filePath))
                {
                    Files.createFile(filePath);
                }
                
                File readFile = filePath.toFile();
                
                BufferedReader bufferedReader = new BufferedReader(new FileReader(readFile));
                List<String> countriesList = new ArrayList<String>();
                String country = null;
                
                while((country = bufferedReader.readLine()) != null)
                {
                    countriesList.add(country);
                }
                
                bufferedReader.close();
                
                return countriesList.toArray(new String[]{});
    }
    
    private void writeFile(String message) throws IOException
    {
        String dirString = "C:\\Users\\kmofp\\Documents\\NetBeansProjects\\Survey";
        
        Path dirPath = Paths.get(dirString); 
        
        if(Files.notExists(dirPath))
        {
            Files.createDirectories(dirPath);
        }
        
      String fileString = "surveyResults.csv";
      Path filePath = Paths.get(dirString, fileString);
      
      if(Files.notExists(filePath))
      {
          Files.createFile(filePath);
      }
        
      PrintWriter out = new PrintWriter(
                        new BufferedWriter(
                        new FileWriter("SurveyResults.txt", true)));
      
      out.print(message);
      
      out.close();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            Survey frame = new Survey();
            frame.setVisible(true);
        });
    }
}
