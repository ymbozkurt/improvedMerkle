package upgradedMerkle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.json.*;
import java.security.NoSuchAlgorithmException;


public class Functions {


    public static String[] hashesArray = new String[16];
    public static String fileData;
    public static void saveOfficialHashes() throws NoSuchAlgorithmException{
        for(int i=0; i<=0;i++){
            if(merkleTree.jtxtField[i].getText().equals("")){
                int lineCounter=0;
                Scanner fileReader;
                try {
                    File file = merkleTree.file;
                    fileReader = new Scanner(file);
                    while(fileReader.hasNextLine()){
                        lineCounter++;
                        fileReader.nextLine();
                        fileData += fileReader.nextLine();
                    }
                    fileReader.close();
                    System.out.println(fileData);
                    System.out.println(fileData.length());
                } catch (FileNotFoundException e) {
                    System.out.println("An error occured.");
                    e.printStackTrace();
                }
                
                merkleTree.toHexString(merkleTree.getSHA256(fileData.substring(0, fileData.length()/2+1)));
                merkleTree.toHexString(merkleTree.getSHA256(fileData.substring(fileData.length()/2)));
 
                
            }
            else{
                hashesArray[2*i-1] = merkleTree.toHexString(merkleTree.getSHA256(merkleTree.jtxtField[i].getText().substring(0, merkleTree.jtxtField[i].getText().length()/2)));
                hashesArray[2*i] = merkleTree.toHexString(merkleTree.getSHA256(merkleTree.jtxtField[i].getText().substring(merkleTree.jtxtField[i].getText().length()/2 , merkleTree.jtxtField[i].getText().length())));
            }
        }
    }


    public static void devideData(){

    }

}
/* 

8 TANE ENTRY AL
ENTRYLERİN 2 EŞİT PARÇASINI HASH OLARAK SAKLA
CHECK BUTONU BU SAKLANAN VERİLERLE YENİ DOSYANINKİNİ KIYASLAYACAK

*/