/**
 * Created by rewaz on 06.12.14.
 *//**
 * Created by rewaz on 29.11.14.
 */

import java.io.*;
import java.net.Socket;


public class ClientBackup {

    static String root = "user.dir";
    FileInputStream file;
    private Socket socket;

    // File dir;// = new File(args);
    // File files[];// = dir.listFiles();

    public static void main(String [] args){

        ClientBackup bc = new ClientBackup();
        if(args.length == 0) {
            System.out.println("Не указана рабочая директория, будет использоваться текущая директория ");
            //root = "user.dir";
        }
        else {
            root = args[0];
        }

        bc.connection();
        bc.file_open(root);

    }

    public int connection () {

        try {
            socket = new Socket("localhost", 11222);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return -1;
        }

        return 1;
    }


    public int write_to_sock(Socket socket, byte bs[]) {

        try {
            OutputStream os = socket.getOutputStream();
            os.write(bs);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        return 1;

    }


    public int file_open (String  args) {

        File dir = new File(args);
        String files[] = dir.list();
        System.out.println(dir.getName());

        for(int i=0;i< files.length;i++ ){

            File file = new File(dir + File.separator + files[i]);
            System.out.println("Length of file is  - " + files.length + "  The i is  - " + i);

            if(file.isDirectory()) {
                System.out.println("The dir name is  - " + file.getAbsolutePath());
                file_open(file.getAbsolutePath());
            }
            System.out.println("The file name is  - " +  file.getAbsolutePath());

            if(file.getAbsolutePath().endsWith("doc")) {

                System.out.println("Word docs");
                String strr = file.getAbsolutePath().replaceFirst(root,"");
                strr = strr + '\n';
                byte buff[] = strr.getBytes();

                write_to_sock(socket,buff);
                long n= file.length();
                int nn = (int) n;
                byte buffer[] = new byte[nn];

                try{
                    FileInputStream fs = new FileInputStream(file.getAbsolutePath());
                    fs.read(buffer);
                    write_to_sock(socket,buffer);
                }
                catch(Exception e) {
                    System.out.println(e.getMessage());
                }


            }



        }
        return  1;
    }
}



class FileSystemClient {

}
