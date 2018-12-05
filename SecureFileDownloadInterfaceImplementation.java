import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import javax.rmi.ssl.*;
import java.io.*;

public class SecureFileDownloadInterfaceImplementation extends UnicastRemoteObject implements SecureFileDownloadInterface {

    //String filePath = "/Users/block/Desktop/Third Year/Distributed Systems - Dr. Govindha/RMIfolder/courseWork/courseWork1"; // File path to server location
    String filePath = "C:\\Users\\Bhra Khoby\\Desktop\\AdvancedFileRetrievalSystem";

    protected SecureFileDownloadInterfaceImplementation () throws RemoteException{
    super(0, new SslRMIClientSocketFactory(), new SslRMIServerSocketFactory(null, null, true));
    }
    /*
    * argumnets : String fileName
    * return type : byte []
    * Reads the file name, opens the
    * file input stream, copies the data into
    * a byte array
    */
    public byte [] getFileFromServer(String fileName) throws Exception {

    File createFile = new File(filePath + "/" + fileName);
    FileInputStream fileinputcheck = new FileInputStream(createFile);

    byte[] filebytes = new byte[fileinputcheck.available()]; //set byte array size to the bytes available in the file
    int size_read;

    while((size_read = fileinputcheck.read()) != -1){ //keep reading bytes of data until there is no more data to be read

      System.out.println("Transfering " + fileName + " From " + filePath);
      fileinputcheck.read(filebytes); //read bytes from input channel into the bytes array

    }

    System.out.println("Transfer completed " + filebytes.length);
    return filebytes;
  }
}
