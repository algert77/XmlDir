/*
* <?xml version>
*     <directories>
*         <directory>
*             <dir
 *         <files>
 *             <file>
 *                 test.doc
 *              </file>
 *          </files>
*     </directory>
*
*
* */
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.nio.*;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class XmlDir {

    String root;
    private File file;

    public static void main(String [] args) {

        XmlDir dir = new XmlDir();
        dir.getFiles(args[0]);

    }


    int getFiles(String args){

        String root;

        if(args==""){
            System.out.println("Не указана директория, будет использоваться пользовательская директория: " + System.getProperty("user.dir"));
            root="user.dir";
        }
        else root = args;

        file = new File(root);
        File files[] = file.listFiles();

        for (int i =0; i< files.length; i++) {
            if (files[i].isDirectory()) getFiles(files[i].getAbsolutePath());
            createXml(getHash(files[i].getAbsolutePath(), (int) files[i].length()));
        }
        return 1;
    }


        private String getHash(String fileName,int length){

            MessageDigest md;
            FileInputStream fs;
            int c;
            byte sizeBytes[] = new byte[length];
            String sha = "";

            try {
                md = MessageDigest.getInstance("MD5");
            }
            catch (NoSuchAlgorithmException e){
                System.out.println(e.getMessage());
                return sha;
            }

            try {
                 fs = new FileInputStream(fileName);
            }
            catch(FileNotFoundException e){
                System.out.println(e.getMessage());
                return sha;
            }

            try {
                fs.read(sizeBytes);
                md.update(sizeBytes);
                byte hash[] = md.digest();
                for(int i=0;i<hash.length;i++){
                    int v = hash[i] & 0xFF;
                    if(v < 16) sha +="0";
                    sha +=Integer.toString(v,16).toUpperCase()+ " ";
                }

            }
            catch(IOException e){
                System.out.println(e.getMessage());
            }
            return sha;
        }


        public int createXml(String sha){
            DocumentBuilder builder;
            File xmlFile = new File(root + "/filesystem.xml" );
            Document doc;

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance() ;
            try {
                builder = factory.newDocumentBuilder();
            }
            catch (ParserConfigurationException e) {
                System.out.println(e.getMessage());
                return -1;
            }

            doc = builder.newDocument();
            
            return 1;
        }

}


