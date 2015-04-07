/**
 * Created by rewaz on 06.12.14.
 */
/**
 * Created by rewaz on 29.11.14.
 */
import java.net.*;
import java.io.*;
import java.util.regex.*;

public class ServerBackup {

    private ServerSocket server;
    private Socket socket;

    public static void main(String [] args) {

        System.out.println("Terwe~!");
        ServerBackup bs = new ServerBackup();

        try {
            bs.connection(args[0]);
        }
        catch (Exception e) {
            System.out.println("The server  error! " + e.getMessage() );
        }
    }

    public int connection   (String root) throws Exception {

        server = new ServerSocket(11222);
        while (true) {
            socket = server.accept();
            //   Thread task = new Thread()
            InputStream iStream;
            InputStreamReader iReader;
            System.out.println("К нам подключились  -  " + socket.getRemoteSocketAddress());
            iStream = socket.getInputStream();
            //iReader  = new InputStreamReader (iStream,"UTF-8");
            char filename[]  = new char[255];
            System.out.println("До работы клиента массив равен - " + filename);
            int c;
            int i =0;

            //Получение имени файла или директории
            while((( c = iStream.read()) != -1) && ((char)c != '\n') ) {
                filename[i]=(char)c;
                i=i+1;
                System.out.println("Счетчик равен " + i+ " Значение с равно  - " + c + "   В ASCII - "  + (char) c );
                String str = new String(filename);
                System.out.println("А сейчас  - столько " + str);
            }
            //Получение содержимого файла
            char filename_osn[] = new char[i];
            for(int n=0;n< i;n++) {
                filename_osn[n] = filename[n];
            }
            String str = new String(filename_osn);
            int  y= str.lastIndexOf( '/');
            String docfile  = str.replace("/","");
            File file = new File(docfile);
            file.createNewFile();
            while((c=iStream.read()) != -1){
                //file.

            }
            return 1;
        }

    }


}

class FileSystem {

    private String root;

    public  FileSystem(String str){

        if(str =="")
            root = "~";


    }

}
