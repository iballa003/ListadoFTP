
package listarftpserver;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class ListarFTPServer {

    public static void main(String[] args) {
            FTPConnector ftpConnector = new FTPConnector();
            ftpConnector.connect("anonymous", "", "ftp.rediris.es");
            ftpConnector.listarDirectoriosRaiz();
            ftpConnector.disconnect();

    }
    
}
// Clase encargada de establecer una conexión y ejecutar comandos FTP.
class FTPConnector {
    private FTPClient clienteFtp;
    public void connect(String username, String password, String hostname){
        if (this.clienteFtp == null || !this.clienteFtp.isConnected()) {
            clienteFtp = new FTPClient();
            try {
                clienteFtp.connect(hostname);
                clienteFtp.enterLocalPassiveMode();
                clienteFtp.login(username, password);
            }
            catch(IOException io) {
                io.printStackTrace();
            }
        }
    }
    public final void listarDirectoriosRaiz(){
        if (this.clienteFtp != null && this.clienteFtp.isConnected()) {
            try {
                FTPFile[] files = clienteFtp.listFiles();
                for (FTPFile file : files) {
                    System.out.println(file.getName());
                }
            }
            catch(IOException io) {
                io.printStackTrace();
            }
        }
    }
    
    public final void disconnect() {
        try {
            this.clienteFtp.disconnect();
        } catch (IOException ex) {
            Logger.getLogger(FTPConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            
}
