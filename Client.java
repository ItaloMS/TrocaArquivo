//     Universidade Federal de Pelotas
//  Centro de Desenvolvimento Tecnológico
//   Bacharelado em Ciência da Computação
//         Redes de Computadores
//  Trabalho 1 - Troca Arquivos pela Rede
// Por Italo M. Silveira e Maicon S. Cardoso

import java.net.InetAddress;
import java.util.ArrayList;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Scanner;
import org.json.simple.parser.ParseException;

public class Client {

    private static String clientIp;
    private static String serverIp;
    private static ArrayList<String> peers, files, temp;
    private static int timeout = 200;

    // pega o ip do cliente
    public static String getIpClient() throws UnknownHostException, SocketException {

        String interfaceRede = "wlan0";
        String meuIp = null;
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            NetworkInterface face = interfaces.nextElement();
            if (face.isLoopback() || !face.isUp()) {
                continue;
            }
            Enumeration<InetAddress> endereco = face.getInetAddresses();
            while (endereco.hasMoreElements()) {
                InetAddress addr = endereco.nextElement();
                if (face.getDisplayName().equals(interfaceRede) && addr.getHostAddress().indexOf(".") > -1) {
                    meuIp = addr.getHostAddress();

                }

            }
        }
        return meuIp;

    }

    // cria mensagem de ping
    public static String ping(String ipAdress) throws UnknownHostException, ParseException, IOException {

        String msgString;
        CommonMessage4Parameters msgJson = new CommonMessage4Parameters("pcmj", "ping", getIpClient(), ipAdress);
        msgString = msgJson.jsonToString4Param(msgJson);
        return msgString;

    }

    // cria mensagem de authenticate
    public static String authenticate(String ipAdress) throws UnknownHostException, SocketException {

        String msgString;
        // definição da senha de acesso ao servidor e aos arquivos
        String passport = "DiJqWHqKtiDgZySAv7ZX";
        MessageAuthenticate msgJson = new MessageAuthenticate("pcmj", "authenticate", passport, getIpClient(), ipAdress);
        msgString = msgJson.jsonToStringAuthenticate(msgJson);
        return msgString;

    }

    // cria mensagem de agent-list
    public static String agentList(String ipAdress) throws UnknownHostException, ParseException, IOException {

        String msgString;
        CommonMessage4Parameters msgJson = new CommonMessage4Parameters("pcmj", "agent-list", getIpClient(), ipAdress);
        msgString = msgJson.jsonToString4Param(msgJson);
        return msgString;

    }

    // cria a mensagem de pong
    public static String pong(String ipAdress) throws UnknownHostException, ParseException, IOException {

        String msgString;
        // cria json da mensagem pong
        CommonMessage5ParamStatus msgJson = new CommonMessage5ParamStatus("pcmj", "pong", "100", serverIp, ipAdress);
        msgString = msgJson.jsonToString5ParamStatus(msgJson);
        return msgString;

    }

    // cria a mensagem de archive-list
    public static String archiveList(String ipAdress) throws UnknownHostException, ParseException, IOException {

        String msgString;
        // cria json da mensagem archive-list
        CommonMessage4Parameters msgJson = new CommonMessage4Parameters("pcmj", "archive-list", getIpClient(), ipAdress);
        msgString = msgJson.jsonToString4Param(msgJson);
        return msgString;

    }

    // método que implementa toda a comunicação entre cliente e servidor
    public static void talkWithServer() throws IOException, UnknownHostException, ParseException, InterruptedException {

        // aqui seta-se o endereço ip do servidor
        serverIp = "192.168.161.13";
        // variáveis locais
        Message localMsg = new Message();
        String msgIn;
        String msgOut;
        String temp;
        CommonMessage5ParamStatus msg5ParamStatus = new CommonMessage5ParamStatus();
        CommonMessage5ParamStatus msg5ParamStatusLocal = new CommonMessage5ParamStatus();
        MessageAuthenticate msgAuthenticate = new MessageAuthenticate();
        MessageAuthenticate msgAuthenticateLocal = new MessageAuthenticate();
        MessageAgentListBack msgAgentListBack = new MessageAgentListBack();
        MessageAgentListBack msgAgentListBackLocal = new MessageAgentListBack();

        try {

            // cria socket para comunicação
            Socket socketServer0 = new Socket(serverIp, 9876);
            /*  Socket socketToServer = new Socket();   
             // timeout para operacoes no socket
             socketToServer.connect(new InetSocketAddress(Server.getIpServer(),9876),timeout);
             // timeout para leituras no socket
             socketToServer.setSoTimeout(timeout); */

            // cria mensagem de ping
            msgOut = ping(serverIp);
            // envia a mensagem de ping para o servidor
            localMsg.sendMsg(socketServer0, msgOut);
            // recebe pong do servidor
            msgIn = localMsg.receiveMsg(socketServer0);
            // transforma para o formato json a mensagem
            msg5ParamStatusLocal = msg5ParamStatus.stringToObjec5ParamStatus(msgIn);

            Thread.sleep(200);

            // testa se a mensagem de retorno é um pong
            if (!(msg5ParamStatusLocal.getProtocol().equals("pcmj") && msg5ParamStatusLocal.getCommand().equals("pong") && msg5ParamStatusLocal.getStatus().equals("100"))) {
                System.out.println("O servidor não responde.");
                System.exit(1);
            }

            socketServer0.close();

            // recebeu pong
            System.out.println("Enviado: " + msgOut);
            System.out.println("Recebido: " + msgIn);
            System.out.println("-> Efetuado o \"ping\" e \"pong\" entre cliente e servidor.\n");
            
            
            Socket socketToServer1 = new Socket(serverIp, 9876);

            // cria mensagem de authenticate
            msgOut = authenticate(serverIp);
            // envia a mensagem de authenticate para o servidor
            localMsg.sendMsg(socketToServer1, msgOut);
            // recebe o authenticate-back do servidor
            msgIn = localMsg.receiveMsg(socketToServer1);
            // transforma para o formato json a mensagem
            msg5ParamStatusLocal = msg5ParamStatus.stringToObjec5ParamStatus(msgIn);

            // testa se a mensagem de retorno é authenticate-back
            while ((msg5ParamStatusLocal.getProtocol().equals("pcmj") && msg5ParamStatusLocal.getCommand().equals("authenticate-back"))) {
                // fica trancado esperando o authenticate-back retornar
                //  && msg5ParamStatusLocal.getStatus().equals("200")
                if (msg5ParamStatusLocal.getStatus().equals("203")) {
                    System.out.println("\nSenha incorreta. Acesso negado.");
                    System.exit(1);
                }
                if (msg5ParamStatusLocal.getStatus().equals("200")) {
                    break;
                }
            }

            // recebeu authenticate-back
            System.out.println("Enviado: " + msgOut);
            System.out.println("Recebido: " + msgIn);
            System.out.println("-> Cliente autenticado no servidor.\n");
            

            socketToServer1.close();

            Socket socketServer2 = new Socket(serverIp, 9876);

            // cria mensagem de agent-list
            msgOut = agentList(serverIp);
            // envia a mensagem de agent-list para o servidor
            localMsg.sendMsg(socketServer2, msgOut);
            // recebe a agent-list-back do servidor
            msgIn = localMsg.receiveMsg(socketServer2);
            // transforma para o formato json a mensagem
            msgAgentListBackLocal = msgAgentListBack.stringToObjecAgentListBack(msgIn);

            // testa se a mensagem de retorno é agent-list-back
            while (!(msgAgentListBackLocal.getProtocol().equals("pcmj") && msgAgentListBackLocal.getCommand().equals("agent-list-back") && msgAgentListBackLocal.getStatus().equals("200"))) {
                // fica trancado esperando o agent-list-back retornar
            }

            // recebeu o agent-list-back
            System.out.println("Enviado: " + msgOut);
            System.out.println("Recebido: " + msgIn);
            System.out.println("-> Lista de IPs recebida do servidor. Os IPs conectados são:");
            peers = msgAgentListBackLocal.getBack();
            for (int i = 0; i < peers.size(); i++) {
                System.out.println("    IP " + (i + 1) + ": " + peers.get(i));
            }
            System.out.println("\n");

            // fecha a conexão com o servidor
            socketServer2.close();

        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // T = new String()ODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    // método que implementa toda a comunicação entre cliente e cliente
    public static void talkWithPeers() throws IOException, UnknownHostException, ParseException, InterruptedException {

        // variáveis locais
        Message localMsg = new Message();
        String msgIn;
        String msgOut;
        String temp;
        CommonMessage5ParamStatus msg5ParamStatus = new CommonMessage5ParamStatus();
        CommonMessage5ParamStatus msg5ParamStatusLocal = new CommonMessage5ParamStatus();
        MessageAuthenticate msgAuthenticate = new MessageAuthenticate();
        MessageAuthenticate msgAuthenticateLocal = new MessageAuthenticate();
        MessageAgentListBack msgAgentListBack = new MessageAgentListBack();
        MessageAgentListBack msgAgentListBackLocal = new MessageAgentListBack();
        // MessageArchiveList msgArchiveList = new Messa

        try {

            // o cliente tem o endereço de todos conectados ao server nesse ponto do programa
            // a partir daqui o cliente pega a lista de arquivos de todos os ips
            for (int i = 0; i < peers.size(); i++) {

                // cria conexão com os ips conectados
                Socket socketToPeer0 = new Socket(peers.get(i), 9876);
                // cria mensagem de ping
                msgOut = ping(peers.get(i));
                // envia a mensagem de ping para o peer
                localMsg.sendMsg(socketToPeer0, msgOut);
                // recebe pong do peer
                msgIn = localMsg.receiveMsg(socketToPeer0);
                // transforma para o formato json a mensagem
                msg5ParamStatusLocal = msg5ParamStatus.stringToObjec5ParamStatus(msgIn);

                Thread.sleep(200);

                // testa se a mensagem de retorno é um pong
                if (!(msg5ParamStatusLocal.getProtocol().equals("pcmj") && msg5ParamStatusLocal.getCommand().equals("pong") && msg5ParamStatusLocal.getStatus().equals("100"))) {
                    System.out.println("O servidor não responde.\n");
                    System.exit(1);
                }

                socketToPeer0.close();

                System.out.println("Enviado: " + msgOut);
                System.out.println("Recebido: " + msgIn);
                System.out.println("-> Efetuado o \"ping\" e \"pong\" entre cliente e o peer de IP " + (i+1) + ".");
                
                
                Socket socketToPeer1 = new Socket(peers.get(i), 9876);

                // cria mensagem de authenticate
                msgOut = authenticate(peers.get(i));
                // envia a mensagem de authenticate para o peer
                localMsg.sendMsg(socketToPeer1, msgOut);
                // recebe o authenticate-back do peer
                msgIn = localMsg.receiveMsg(socketToPeer1);
                // transforma para o formato json a mensagem
                msg5ParamStatusLocal = msg5ParamStatus.stringToObjec5ParamStatus(msgIn);

                // testa se a mensagem de retorno do peer é authenticate-back
                while ((msg5ParamStatusLocal.getProtocol().equals("pcmj") && msg5ParamStatusLocal.getCommand().equals("authenticate-back"))) {
                    // fica trancado esperando o authenticate-back retornar
                    //  && msg5ParamStatusLocal.getStatus().equals("200")
                    if (msg5ParamStatusLocal.getStatus().equals("203")) {
                        System.out.println("\nSenha incorreta. Acesso negado.\n");
                        System.exit(1);
                    }
                    if (msg5ParamStatusLocal.getStatus().equals("200")) {
                        break;
                    }
                }

                System.out.println("\nEnviado: " + msgOut);
                System.out.println("Recebido: " + msgIn);
                System.out.println("-> Cliente autenticado no peer de IP " + (i+1) + ".");
                
                
                socketToPeer1.close();

                Socket socketToPeer2 = new Socket(peers.get(i), 9876);

                // cria mensagem de archive-list
                msgOut = archiveList(peers.get(i));
                // envia a mensagem de archive-list
                localMsg.sendMsg(socketToPeer2, msgOut);
                // recebe o archive-list-back do peer
                msgIn = localMsg.receiveMsg(socketToPeer2);
                // transforma para o formato json a mensagem

                msgAgentListBackLocal = msgAgentListBack.stringToObjecAgentListBack(msgIn);
                
                System.out.println("\nEnviado: " + msgOut);
                System.out.println("Recebido: " + msgIn);
                
                // testa se a mensagem de retorno é agent-list-back
                while (!(msgAgentListBackLocal.getProtocol().equals("pcmj") && msgAgentListBackLocal.getCommand().equals("archive-list-back") && msgAgentListBackLocal.getStatus().equals("200"))) {
                    // fica trancado esperando o agent-list-back retornar
                }
                
                //for (i = 0; i < msgAgentListBackLocal.getBack().size(); i++){
                    System.out.println("-> Arquivos: " + msgAgentListBackLocal.getBack().get(0));
                // }

                socketToPeer2.close(); /*
                
                 // recebeu o agent-list-back
                 // adiciona os arquivos de cada ip na lista de arquivos do cliente
                 for (i = 0; i < msgAgentListBackLocal.getBack().size(); i++) {
                 temp = msgAgentListBackLocal.getBack().get(i);
                 files.add(temp);
                 }

                 socketToPeer.close(); */

            }
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // T = new String()ODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    // método que baixa um arquivo de um ip
    public static void downloadAFile() {

    }

    // método que mantém o cliente online para disponibilizar arquivos para outros peers
    public static void beAPeer() throws IOException, ParseException {

        // variáveis locais
        Message localMsg = new Message();
        String msgIn;
        String msgOut;
        String temp;
        CommonMessage5ParamStatus msg5ParamStatus = new CommonMessage5ParamStatus();
        CommonMessage5ParamStatus msg5ParamStatusLocal = new CommonMessage5ParamStatus();
        MessageAuthenticate msgAuthenticate = new MessageAuthenticate();
        MessageAuthenticate msgAuthenticateLocal = new MessageAuthenticate();
        MessageAgentListBack msgAgentListBack = new MessageAgentListBack();
        MessageAgentListBack msgAgentListBackLocal = new MessageAgentListBack();
        CommonMessage4Parameters msg4param = new CommonMessage4Parameters();
        CommonMessage4Parameters msg4ParamLocal = new CommonMessage4Parameters();
        String local;

        // cria socket do peer como servidor na porta 9876
        ServerSocket socket = new ServerSocket(9876);

        try {

            Socket SocketPeerConnection = socket.accept();

            // lê o que está no socket e transforma para json
            msgIn = localMsg.receiveMsg(SocketPeerConnection);
            msg4ParamLocal = msg4param.stringToObjec4Param(msgIn);

            // testa se a mensagem é um ping de outro peer                
            if (msg4ParamLocal.getProtocol().equals("pcmj") && msg4ParamLocal.getCommand().equals("ping")) {

                // lê o ip de quem enviou a mensagem
                String senderIp = msg4ParamLocal.getSender();
                // cria a mensagem pong
                msgOut = pong(senderIp);
                // envia o pong para o cliente
                localMsg.sendMsg(SocketPeerConnection, msgOut);

            }

        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // T = new String()ODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws Exception {
        // talkWithServer();

        Scanner choice = new Scanner(System.in);
        int numberChoice;
        talkWithServer();
        talkWithPeers();
        /* System.out.println("Escolha uma opção digitando o valor correspondente:\n");
         System.out.println("1. Baixar um arquivo\n");
         System.out.println("2. Baixar lista de arquivos dos peers\n");
         System.out.println("3. Atualizar lista de peers e arquivos\n");
         System.out.println("4. Ficar disponível para envio de arquivos\n");
         System.out.println("5. Sair\n");
         numberChoice = choice.nextInt();
         if (numberChoice == 1) {
         downloadAFile();
         } else if (numberChoice == 2) {
         talkWithPeers();
         } else if (numberChoice == 3) {
         talkWithServer();
         talkWithPeers();
         } else if (numberChoice == 4) {
         beAPeer();
         } else if (numberChoice == 5) {
         System.exit(0);
         } else if (numberChoice < 1 || numberChoice > 5) {
         System.out.println("Erro: opção inválida. O programa será abortado.");
         System.exit(1); 
         } */

    }

}
