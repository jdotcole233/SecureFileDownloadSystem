import java.rmi.*;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;
import javax.rmi.ssl.*;

public class SecureFileDownloadServer {

  public static void main(String[] args) throws Exception {
    SecureFileDownloadInterfaceImplementation download = new SecureFileDownloadInterfaceImplementation();//Object of type FileDownloadImplementation
    Registry registry = LocateRegistry.createRegistry(8844, new SslRMIClientSocketFactory(), new SslRMIServerSocketFactory(null, null, true)); //Open registry for available ports
    registry.bind("rmi://172.20.10.7/DownloadFile", download); // bind object in the registry
    System.out.println("Server started securely.. !!!");
  }
}
