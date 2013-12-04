//     Universidade Federal de Pelotas
//  Centro de Desenvolvimento Tecnológico
//   Bacharelado em Ciência da Computação
//         Redes de Computadores
//  Trabalho 1 - Troca Arquivos pela Rede
// Por Italo M. Silveira e Maicon S. Cardoso

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Message {

    public Message() {
        // construtor vazio
    }
    

    public void sendMsg(Socket socket, String msg) throws IOException{
        
        DataOutputStream outMsg = new DataOutputStream(socket.getOutputStream());
        outMsg.writeBytes(msg + "\n");
        
    }
    
    public String receiveMsg(Socket socket) throws IOException{
        
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        return in.readLine();
        
    }
        
}
