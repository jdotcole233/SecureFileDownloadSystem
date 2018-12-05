import java.rmi.*;
import java.rmi.registry.*;
import java.util.*;
import java.io.*;
import javax.rmi.ssl.*;

public class SecureFileDownloadClient {
    public static void main(String[] args) throws Exception {

    Scanner keyboardInput = new Scanner(System.in);
    String dir_location = "";
    Integer dir_number = null;

    Registry registry = LocateRegistry.getRegistry("172.20.10.7", 8844, new SslRMIClientSocketFactory());
    SecureFileDownloadInterface clientDownloadFile = (SecureFileDownloadInterface) registry.lookup("rmi://172.20.10.7/DownloadFile"); //Look up Registry.

    System.out.println("Enter the name of file to download:");
    String file_to_download_name = keyboardInput.next(); // Accepts file name

    System.out.println("Choose a location to save file");
    System.out.println("1. Desktop\n2. Documents\n3. Downloads");
    dir_number = keyboardInput.nextInt(); //specify location to for data to be downloaded

    switch (dir_number) {
      case 1:
      dir_location = "Desktop";
      break;
      case 2:
      dir_location = "Documents";
      break;
      case 3:
      dir_location = "Downloads";
      break;
    }
    processFile(clientDownloadFile.getFileFromServer(file_to_download_name), dir_location,file_to_download_name);
    }

    /* Arguments : Byte [] fileBytesReceived
    * String location
    * String fileName
    * Takes three argumnets as stated above
    * process the byte stream of data and have
    * the data written to a designated (Absolute path)
    * location.
    */
    public static void processFile(byte [] fileBytesReceived,String location,String fileName) throws Exception {
    // Absolute path to save downloaded file
    // MacOS - /Users/
    // Linx - /root/
    // Windown - C:\

    String saveFileTo = "C:\\Users\\Bhra Khoby\\";
    FileOutputStream fileoutputcreate = null;
    File file = new File(saveFileTo+location+"/"+fileName);

    file.createNewFile(); //create new file to the destination path specified
    try{

      fileoutputcreate = new FileOutputStream(file, true); //open fileoutputstream for writing into destination file
      System.out.println(fileBytesReceived.length);
      fileoutputcreate.write(fileBytesReceived, 0, fileBytesReceived.length); // Write the byte stream of data to new destination path
      System.out.println("File copied successful to " + file.getAbsolutePath()); // Log success message to the screen upon completion

    } catch(IOException e){

      System.out.println("File not found"); //Log Exception if file not found
    } finally {
      fileoutputcreate.flush(); // Ensure all byte stream of data are written to its intended location
      fileoutputcreate.close(); // Close the fileoutput stream for writing
    }
  }
}
