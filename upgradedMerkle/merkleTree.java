package upgradedMerkle;
import javax.swing.JFrame;
import javax.swing.event.DocumentEvent;
//import javax.swing.border.Border;
//import javax.swing.plaf.DimensionUIResource;
import javax.swing.event.DocumentListener;

import java.awt.*;
import java.awt.event.ActionEvent;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.awt.event.ActionListener;

//import javax.print.DocFlavor.STRING;
import javax.swing.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
//import java.util.function.IntFunction;

//import javax.swing.JButton;
//import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileInputStream;
//import java.util.Scanner;
import java.io.IOException;

public class merkleTree extends JFrame {
    final private Font mainFont = new Font("Segoe print", Font.BOLD, 14);
    final private Font littleFont = new Font("Segoe print", Font.BOLD, 10);
    Dimension textDimension = new Dimension(450,50);
    
    
    
    // FILE HASHING

    public static String getFileChechksum(MessageDigest digest, File file) throws IOException {
        // Get file input stream for reading the file content
        FileInputStream fis = new FileInputStream(file);

        // Create byte array to read data in chunks
        byte[] byteArray = new byte[1024];
        int bytesCount = 0;

        // Read file data and update in message digest
        while ((bytesCount = fis.read(byteArray)) != -1) {
            digest.update(byteArray, 0, bytesCount);
        }
        ;

        // close the stream; We don't need it now.
        fis.close();

        // Get the hash's bytes
        byte[] bytes = digest.digest();

        // This bytes[] has bytes in decimal format;
        // Convert it to hexadecimal format
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }

        // return complete hash
        System.out.println(sb.toString());
        return sb.toString();

    }

    // SHA-512 ENCRYPTOR

    public static String encryptThisString512(String input) {
        try {
            // getInstance() method is called with algorithm SHA-512
            MessageDigest md = MessageDigest.getInstance("SHA-512");

            // digest() method is called
            // to calculate message digest of the input string
            // returned as array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);

            // Add preceding 0s to make it 32 bit
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            // return the HashText
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    // SHA-256 ENCRYPTOR

    public static byte[] getSHA256(String input) throws NoSuchAlgorithmException {
        // Static getInstance method is called with hashing SHA
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // digest() method called
        // to calculate message digest of an input
        // and return array of byte
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String toHexString(byte[] hash) {
        // Convert byte array into signum representation
        BigInteger number = new BigInteger(1, hash);

        // Convert message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // Pad with leading zeros
        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }

    // MD5 ENCRYPTOR

    public static String getMd5(String input) {
        try {

            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            // of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }










    public static JTextField entryText1 = new JTextField();
    public static JTextField entryText2 = new JTextField();
    public static JTextField entryText3 = new JTextField();
    public static JTextField entryText4 = new JTextField();
    public static JTextField entryText5 = new JTextField();
    public static JTextField entryText6 = new JTextField();
    public static JTextField entryText7 = new JTextField();
    public static JTextField entryText8 = new JTextField();
    public static File file;
    JLabel firstHashLabel1 = new JLabel("Hash of entry");
    JLabel firstHashLabel2 = new JLabel("Hash of entry");
    JLabel firstHashLabel3 = new JLabel("Hash of entry");
    JLabel firstHashLabel4 = new JLabel("Hash of entry");
    JLabel firstHashLabel5 = new JLabel("Hash of entry");
    JLabel firstHashLabel6 = new JLabel("Hash of entry");
    JLabel firstHashLabel7 = new JLabel("Hash of entry");
    JLabel firstHashLabel8 = new JLabel("Hash of entry");
    JTextField topHashText = new JTextField(SwingConstants.CENTER);
    JTextField secondTopText1 = new JTextField("");
    JTextField secondTopText2 = new JTextField("");        
    JTextField thirdTopText1 = new JTextField("");
    JTextField thirdTopText2 = new JTextField("");
    JTextField thirdTopText3 = new JTextField("");
    JTextField thirdTopText4 = new JTextField("");

            



    public void sum(){
        try {
            topHashText.setText(toHexString(getSHA256(toHexString(getSHA256((toHexString(getSHA256(firstHashLabel1.getText()+firstHashLabel2.getText())))+(toHexString(getSHA256(firstHashLabel3.getText()+firstHashLabel4.getText())))))+toHexString(getSHA256((toHexString(getSHA256(firstHashLabel5.getText()+firstHashLabel6.getText())))+(toHexString(getSHA256(firstHashLabel7.getText()+firstHashLabel8.getText()))))))));
            secondTopText1.setText(toHexString(getSHA256(toHexString(getSHA256(firstHashLabel1.getText()+firstHashLabel2.getText()))+toHexString(getSHA256(firstHashLabel3.getText()+firstHashLabel4.getText())))));
            secondTopText2.setText(toHexString(getSHA256(toHexString(getSHA256(firstHashLabel5.getText()+firstHashLabel6.getText()))+toHexString(getSHA256(firstHashLabel7.getText()+firstHashLabel8.getText())))));
            thirdTopText1.setText(toHexString(getSHA256(firstHashLabel1.getText()+firstHashLabel2.getText())));
            thirdTopText2.setText(toHexString(getSHA256(firstHashLabel3.getText()+firstHashLabel4.getText())));
            thirdTopText3.setText(toHexString(getSHA256(firstHashLabel5.getText()+firstHashLabel6.getText())));
            thirdTopText4.setText(toHexString(getSHA256(firstHashLabel7.getText()+firstHashLabel8.getText())));
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        
        
        
    }
    
    public void initialize() throws NoSuchAlgorithmException {

        /********* Form Panel *********/
        JLabel lbWelcome = new JLabel();
        lbWelcome.setFont(mainFont);

        


        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(1, 1, 5, 5)); // row, coloumn, marginX, marginY
        formPanel.setOpaque(false);
        formPanel.add(lbWelcome);

        /******** Results Panel ********/
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(1, 1, 5, 5));
        centerPanel.setFont(mainFont);
        centerPanel.setOpaque(false);
        System.out.println(centerPanel.getWidth());
        
            JPanel resultsPanel = new JPanel();
            resultsPanel.setLayout(new GridLayout(6, 1, 5, 5));
            resultsPanel.setOpaque(false);
            
            /*
            
            JPanel stepsPanel = new JPanel();
            stepsPanel.setFont(mainFont);
            stepsPanel.setLayout(new GridLayout(4, 1, 5, 5));
            stepsPanel.setOpaque(false);
            stepsPanel.setBorder(BorderFactory.createLineBorder(Color.black));
            JLabel firstStep = new JLabel(), secondStep = new JLabel(), thirdStep = new JLabel(), entryStep = new JLabel();
            entryStep.setText("ROOT:");
            thirdStep.setText("Second Hashes:");
            secondStep.setText("First Hashes:");
            firstStep.setText("Entries:");
            stepsPanel.add(entryStep);
            stepsPanel.add(thirdStep);
            stepsPanel.add(secondStep);
            stepsPanel.add(firstStep);
            centerPanel.add(stepsPanel);

            */

            
            centerPanel.add(resultsPanel);
        JButton newPageBtn = new JButton("Check data");
        newPageBtn.setSize(100, 40);
        newPageBtn.setFocusable(false);
        newPageBtn.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==newPageBtn){
                    //dispose();
                    ErrorPage errorPage = new ErrorPage();
                    Functions.devideData();
                }
            }

        });
        JButton saveFilesBtn = new JButton("Save Original Files");
        saveFilesBtn.setSize(100,40);
        saveFilesBtn.setFocusable(false);
        saveFilesBtn.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                try {
                    Functions.saveOfficialHashes();
                } catch (NoSuchAlgorithmException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
            
        });
        
        JPanel firstPanel = new JPanel();
            firstPanel.setBorder(BorderFactory.createLineBorder(Color.black));
            firstPanel.setFont(mainFont);
            topHashText.setFont(mainFont);
            //topHashText.setMinimumSize(textDimension);
            topHashText.setPreferredSize(new Dimension(800,60));
            topHashText.setHorizontalAlignment(SwingConstants.CENTER);
            firstPanel.add(topHashText);
            resultsPanel.add(firstPanel);
        JPanel araPanel2 = new JPanel();
            araPanel2.add(saveFilesBtn);
            resultsPanel.add(araPanel2);
        JPanel secondPanel = new JPanel();
            secondPanel.setBorder(BorderFactory.createLineBorder(Color.black));
            secondPanel.setFont(mainFont);
            secondTopText1.setPreferredSize(new Dimension(800,60));
            secondTopText2.setPreferredSize(new Dimension(800,60));
            secondTopText1.setHorizontalAlignment(SwingConstants.CENTER);
            secondTopText2.setHorizontalAlignment(SwingConstants.CENTER);
            secondTopText1.setFont(mainFont);
            secondTopText2.setFont(mainFont);
            secondPanel.add(secondTopText1);
            secondPanel.add(secondTopText2);
            resultsPanel.add(secondPanel);
        JPanel araPanel = new JPanel();
            araPanel.add(saveFilesBtn);
            resultsPanel.add(araPanel);
        JPanel thirdPanel = new JPanel();
            thirdPanel.setOpaque(false);
            thirdPanel.setBorder(BorderFactory.createLineBorder(Color.black));
            thirdPanel.setFont(mainFont);
            thirdPanel.setOpaque(false);
            thirdTopText1.setPreferredSize(textDimension);
            thirdTopText2.setPreferredSize(textDimension);
            thirdTopText3.setPreferredSize(textDimension);
            thirdTopText4.setPreferredSize(textDimension);
            thirdTopText1.setFont(littleFont);
            thirdTopText2.setFont(littleFont);
            thirdTopText3.setFont(littleFont);
            thirdTopText4.setFont(littleFont);
            thirdPanel.add(thirdTopText1);
            thirdPanel.add(thirdTopText2);
            thirdPanel.add(thirdTopText3);
            thirdPanel.add(thirdTopText4);
            resultsPanel.add(thirdPanel);

        JPanel bottomPanel = new JPanel();
            bottomPanel.setOpaque(false);
            bottomPanel.setBorder(BorderFactory.createLineBorder(Color.black));
            bottomPanel.setFont(mainFont);
            bottomPanel.setLayout(new GridLayout(1, 8, 2, 2));
            
            
            firstHashLabel1.setFont(mainFont);
            firstHashLabel2.setFont(mainFont);
            firstHashLabel3.setFont(mainFont);
            firstHashLabel4.setFont(mainFont);
            firstHashLabel5.setFont(mainFont);
            firstHashLabel6.setFont(mainFont);
            firstHashLabel7.setFont(mainFont);
            firstHashLabel8.setFont(mainFont);

            

            JPanel bttm1 = new JPanel();
            bttm1.setLayout(new GridLayout(2,1,3,3));
            bttm1.add(firstHashLabel1);
            JLabel lbhashing1 = new JLabel();
            bttm1.add(lbhashing1);
            
            JPanel bttm2 = new JPanel();
            bttm2.setLayout(new GridLayout(2,1,3,3));
            bttm2.add(firstHashLabel2);
            JLabel lbhashing2 = new JLabel();
            bttm2.add(lbhashing2);

            JPanel bttm3 = new JPanel();
            bttm3.setLayout(new GridLayout(2,1,3,3));
            bttm3.add(firstHashLabel3);
            JLabel lbhashing3 = new JLabel();
            bttm3.add(lbhashing3);

            JPanel bttm4 = new JPanel();
            bttm4.setLayout(new GridLayout(2,1,3,3));
            bttm4.add(firstHashLabel4);
            JLabel lbhashing4 = new JLabel();
            bttm4.add(lbhashing4);

            JPanel bttm5 = new JPanel();
            bttm5.setLayout(new GridLayout(2,1,3,3));
            bttm5.add(firstHashLabel5);
            JLabel lbhashing5 = new JLabel();
            bttm5.add(lbhashing5);

            JPanel bttm6 = new JPanel();
            bttm6.setLayout(new GridLayout(2,1,3,3));
            bttm6.add(firstHashLabel6);
            JLabel lbhashing6 = new JLabel();
            bttm6.add(lbhashing6);

            JPanel bttm7 = new JPanel();
            bttm7.setLayout(new GridLayout(2,1,3,3));
            bttm7.add(firstHashLabel7);
            JLabel lbhashing7 = new JLabel();
            bttm7.add(lbhashing7);

            JPanel bttm8 = new JPanel();
            bttm8.setLayout(new GridLayout(2,1,3,3));
            bttm8.add(firstHashLabel8);
            JLabel lbhashing8 = new JLabel();
            bttm8.add(lbhashing8);

            


            bttm1.setBorder(BorderFactory.createLineBorder(Color.black));
            bttm2.setBorder(BorderFactory.createLineBorder(Color.black));
            bttm3.setBorder(BorderFactory.createLineBorder(Color.black));
            bttm4.setBorder(BorderFactory.createLineBorder(Color.black));
            bttm5.setBorder(BorderFactory.createLineBorder(Color.black));
            bttm6.setBorder(BorderFactory.createLineBorder(Color.black));
            bttm7.setBorder(BorderFactory.createLineBorder(Color.black));
            bttm8.setBorder(BorderFactory.createLineBorder(Color.black));

            bottomPanel.add(bttm1);
            bottomPanel.add(bttm2);
            bottomPanel.add(bttm3);
            bottomPanel.add(bttm4);
            bottomPanel.add(bttm5);
            bottomPanel.add(bttm6);
            bottomPanel.add(bttm7);
            bottomPanel.add(bttm8);
            
            resultsPanel.add(bottomPanel);
            


            


        /******* South Entry Panel *******/

        JPanel entryPanel = new JPanel();
        entryPanel.setLayout(new GridLayout(1,8,15,15));           
        JPanel entry1 = new JPanel();
        entry1.setLayout(new GridLayout(2,1,2,2));
        JPanel entry2 = new JPanel();
        entry2.setLayout(new GridLayout(2,1,2,2));
        JPanel entry3 = new JPanel();
        entry3.setLayout(new GridLayout(2,1,2,2));
        JPanel entry4 = new JPanel();
        entry4.setLayout(new GridLayout(2,1,2,2));
        JPanel entry5 = new JPanel();
        entry5.setLayout(new GridLayout(2,1,2,2));
        JPanel entry6 = new JPanel();
        entry6.setLayout(new GridLayout(2,1,2,2));
        JPanel entry7 = new JPanel();
        entry7.setLayout(new GridLayout(2,1,2,2));
        JPanel entry8 = new JPanel();
        entry8.setLayout(new GridLayout(2,1,2,2));
        
        entry1.add(entryText1);
        entry2.add(entryText2);
        entry3.add(entryText3);
        entry4.add(entryText4);
        entry5.add(entryText5);
        entry6.add(entryText6);
        entry7.add(entryText7);
        entry8.add(entryText8);

        

        entryText1.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent e) {
                warn1();                
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                warn1();                
                
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                warn1();                
                
            }
            
            public void warn1(){
                if(entryText1.getText().equals("")){
                    firstHashLabel1.setText("Hash of entry");
                }
                else{
                    try {         
                        lbhashing1.setText("");           
                        firstHashLabel1.setText("<html><p style=\"width:150px\">"+toHexString(getSHA256(entryText1.getText()))+"</p></html>");
                        sum();
                } catch (NoSuchAlgorithmException e) {

                    e.printStackTrace();
                }
                }
                
            }
            
        });
        
        entryText2.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent e) {
                warn2();                
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                warn2();
                
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                warn2();
                
            }
            
            public void warn2(){
                
                if(entryText2.getText().equals("")){
                    firstHashLabel2.setText("Hash of entry");
                }
                else{
                    try {    
                        lbhashing2.setText("");                
                        firstHashLabel2.setText("<html><p style=\"width:150px\">"+toHexString(getSHA256(entryText2.getText()))+"</p></html>");
                        sum();
                } catch (Exception e) {
                    //TODO: handle exception
                }
                }
                
            }
            
        });

        entryText3.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent e) {
                warn3();                
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                warn3();                
                
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                warn3();                
                
            }
            
            public void warn3(){
                if(entryText3.getText().equals("")){
                    firstHashLabel3.setText("Hash of entry");
                }
                else{
                    try {
                        lbhashing3.setText("");
                        firstHashLabel3.setText("<html><p style=\"width:150px\">"+toHexString(getSHA256(entryText3.getText()))+"</p></html>");
                        sum();
                } catch (Exception e) {
                    //TODO: handle exception
                }
                }
                
            }
            
        });

        entryText4.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent e) {
                warn4();                
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                warn4();                
                
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                warn4();                
                
            }
            
            public void warn4(){
                if(entryText4.getText().equals("")){
                    firstHashLabel4.setText("Hash of entry");
                }
                else{
                    try {   
                        lbhashing4.setText("");                 
                        firstHashLabel4.setText("<html><p style=\"width:150px\">"+toHexString(getSHA256(entryText4.getText()))+"</p></html>");
                        sum();
                } catch (Exception e) {
                    //TODO: handle exception
                }
                }
                
            }
            
        });

        entryText5.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent e) {
                warn5();                
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                warn5();                
                
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                warn5();                
                
            }
            
            public void warn5(){
                if(entryText5.getText().equals("")){
                    firstHashLabel5.setText("Hash of entry");
                }
                else{
                    try {
                        lbhashing5.setText("");
                        firstHashLabel5.setText("<html><p style=\"width:150px\">"+toHexString(getSHA256(entryText5.getText()))+"</p></html>");
                        sum();
                } catch (Exception e) {
                    //TODO: handle exception
                }}
                
            }
            
        });

        entryText6.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent e) {
                warn6();                
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                warn6();                
                
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                warn6();                
                
            }
            
            public void warn6(){
                if(entryText6.getText().equals("")){
                    firstHashLabel6.setText("Hash of entry");
                }
                else{
                    try {
                        lbhashing6.setText("");
                        firstHashLabel6.setText("<html><p style=\"width:150px\">"+toHexString(getSHA256(entryText6.getText()))+"</p></html>");
                        sum();
                } catch (Exception e) {
                    //TODO: handle exception
                }}
                
            }
            
        });

        entryText7.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent e) {
                warn7();                
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                warn7();                
                
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                warn7();                
                
            }
            
            public void warn7(){
                if(entryText7.getText().equals("")){
                    firstHashLabel7.setText("Hash of entry");
                }
                else{
                    try {
                        lbhashing7.setText("");
                        firstHashLabel7.setText("<html><p style=\"width:150px\">"+toHexString(getSHA256(entryText7.getText()))+"</p></html>");
                        sum();
                } catch (Exception e) {
                    //TODO: handle exception
                }}
            }
            
        });

        entryText8.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent e) {
                warn8();                
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                warn8();                
                
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                warn8();               
                
            }
            
            public void warn8(){
                if(entryText8.getText().equals("")){
                    firstHashLabel8.setText("Hash of entry");
                }
                else{
                    try {
                        lbhashing8.setText("");
                        firstHashLabel8.setText("<html><p style=\"width:150px\">"+toHexString(getSHA256(entryText8.getText()))+"</p></html>");
                        sum();
                } catch (Exception e) {
                    //TODO: handle exception
                }}
            }
            
        });
        
        MessageDigest sha256Digest = MessageDigest.getInstance("SHA-256");

        JButton btn1 = new JButton("Select file");
        
        btn1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new java.io.File("C:/Users/ymboz/Desktop"));
                int response = fileChooser.showOpenDialog(null);

                if (response == JFileChooser.APPROVE_OPTION) {

                    
                    file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                    lbhashing1.setText(file.getAbsolutePath());
                    entryText1.setText("");
                    try {
                        firstHashLabel1.setText("<html><p style=\"width:150px\">"+getFileChechksum(sha256Digest, file)+"</p></html>");
                        sum();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                
            }

        });

        JButton btn2 = new JButton("Select file");
        btn2.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new java.io.File("C:/Users/ymboz/Desktop"));
                int response = fileChooser.showOpenDialog(null);

                if (response == JFileChooser.APPROVE_OPTION) {

                    file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                    lbhashing2.setText(file.getAbsolutePath());
                    entryText2.setText("");
                    try {
                        firstHashLabel2.setText("<html><p style=\"width:150px\">"+getFileChechksum(sha256Digest, file)+"</p></html>");
                        sum();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                
            }

        });
        JButton btn3 = new JButton("Select file");
        btn3.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new java.io.File("C:/Users/ymboz/Desktop"));
                int response = fileChooser.showOpenDialog(null);

                if (response == JFileChooser.APPROVE_OPTION) {

                    file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                    lbhashing3.setText(file.getAbsolutePath());
                    entryText3.setText("");
                    try {
                        firstHashLabel3.setText("<html><p style=\"width:150px\">"+getFileChechksum(sha256Digest, file)+"</p></html>");
                        sum();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                
            }

        });
        JButton btn4 = new JButton("Select file");
        btn4.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new java.io.File("C:/Users/ymboz/Desktop"));
                int response = fileChooser.showOpenDialog(null);

                if (response == JFileChooser.APPROVE_OPTION) {

                    file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                    lbhashing4.setText(file.getAbsolutePath());
                    entryText4.setText("");
                    try {
                        firstHashLabel4.setText("<html><p style=\"width:150px\">"+getFileChechksum(sha256Digest, file)+"</p></html>");
                        sum();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                
            }

        });
        JButton btn5 = new JButton("Select file");
        btn5.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new java.io.File("C:/Users/ymboz/Desktop"));
                int response = fileChooser.showOpenDialog(null);

                if (response == JFileChooser.APPROVE_OPTION) {

                    file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                    lbhashing5.setText(file.getAbsolutePath());
                    entryText5.setText("");
                    try {
                        firstHashLabel5.setText("<html><p style=\"width:150px\">"+getFileChechksum(sha256Digest, file)+"</p></html>");
                        sum();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                
            }

        });
        JButton btn6 = new JButton("Select file");
        btn6.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new java.io.File("C:/Users/ymboz/Desktop"));
                int response = fileChooser.showOpenDialog(null);

                if (response == JFileChooser.APPROVE_OPTION) {

                    file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                    lbhashing6.setText(file.getAbsolutePath());
                    entryText6.setText("");
                    try {
                        firstHashLabel6.setText("<html><p style=\"width:150px\">"+getFileChechksum(sha256Digest, file)+"</p></html>");
                        sum();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                
            }

        });
        JButton btn7 = new JButton("Select file");
        btn7.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new java.io.File("C:/Users/ymboz/Desktop"));
                int response = fileChooser.showOpenDialog(null);

                if (response == JFileChooser.APPROVE_OPTION) {

                    file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                    lbhashing7.setText(file.getAbsolutePath());
                    entryText7.setText("");
                    try {
                        firstHashLabel7.setText("<html><p style=\"width:150px\">"+getFileChechksum(sha256Digest, file)+"</p></html>");
                        sum();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                
            }

        });
        JButton btn8 = new JButton("Select file");
        btn8.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new java.io.File("C:/Users/ymboz/Desktop"));
                int response = fileChooser.showOpenDialog(null);

                if (response == JFileChooser.APPROVE_OPTION) {

                    file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                    lbhashing8.setText(file.getAbsolutePath());
                    entryText8.setText("");
                    try {
                        firstHashLabel8.setText("<html><p style=\"width:150px\">"+getFileChechksum(sha256Digest, file)+"</p></html>");
                        sum();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                
            }

        });


        entry1.add(btn1);
        entry2.add(btn2);
        entry3.add(btn3);
        entry4.add(btn4);
        entry5.add(btn5);
        entry6.add(btn6);
        entry7.add(btn7);
        entry8.add(btn8);
        
        
        entryPanel.add(entry1);
        entryPanel.add(entry2);
        entryPanel.add(entry3);
        entryPanel.add(entry4);
        entryPanel.add(entry5);
        entryPanel.add(entry6);
        entryPanel.add(entry7);
        entryPanel.add(entry8);



        //resultsPanel.add(sha256);
        //resultsPanel.add(sha512);
        ///resultsPanel.add(md5);

        /****** Buttons Panel ******/


        /*
        
        MessageDigest sha256Digest = MessageDigest.getInstance("SHA-256");
        MessageDigest md5Digest = MessageDigest.getInstance("MD5");
        MessageDigest sha512Digest = MessageDigest.getInstance("SHA-512");
        
        */
        

        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(230, 230, 230));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(entryPanel, BorderLayout.SOUTH);

        mainPanel.setFont(mainFont);
        mainPanel.setOpaque(false);
        

        add(mainPanel);

        setTitle("Merkle Tree");
        setSize(1920, 1080);
        setMinimumSize(new Dimension(300, 400));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setUndecorated(true);
    }
    public static JTextField[] jtxtField= {entryText1,entryText2,entryText3,entryText4,entryText5,entryText6
    ,entryText7,entryText8};
    public static void main(String[] args) throws NoSuchAlgorithmException {
        merkleTree merkleTree = new merkleTree();
        merkleTree.initialize();
    }

}