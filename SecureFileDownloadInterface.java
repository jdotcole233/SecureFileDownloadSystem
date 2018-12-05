import java.rmi.*;

public interface SecureFileDownloadInterface extends Remote {
  public byte [] getFileFromServer(String fileName) throws Exception;
}
