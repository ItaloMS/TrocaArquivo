//     Universidade Federal de Pelotas
//  Centro de Desenvolvimento Tecnológico
//   Bacharelado em Ciência da Computação
//         Redes de Computadores
//  Trabalho 1 - Troca Arquivos pela Rede
// Por Italo M. Silveira e Maicon S. Cardoso

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

public class MessageArchiveListBack {

    private String protocol;
    private String command;
    private String status;
    private ArrayList<String> back;
    private ArrayList<String> folder;
    private ArrayList<String> file;
    private String sender;
    private String receptor;

    public MessageArchiveListBack(String protocol, String command, String status, ArrayList<String> back, ArrayList<String> folder, ArrayList<String> file, String sender, String receptor) {
        this.protocol = protocol;
        this.command = command;
        this.status = status;
        this.back = back;
        this.folder = folder;
        this.file = file;
        this.sender = sender;
        this.receptor = receptor;
    }
    
    public MessageArchiveListBack(){
        // para possibilitar inicializar sem os parâmetros
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<String> getBack() {
        return back;
    }

    public void setBack(ArrayList<String> back) {
        this.back = back;
    }

    public ArrayList<String> getFolder() {
        return folder;
    }

    public void setFolder(ArrayList<String> folder) {
        this.folder = folder;
    }

    public ArrayList<String> getFile() {
        return file;
    }

    public void setFile(ArrayList<String> file) {
        this.file = file;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceptor() {
        return receptor;
    }

    public void setReceptor(String receptor) {
        this.receptor = receptor;
    }

    public String jsonToStringArchiveListBack(Object p) {
        MessageArchiveListBack aux = (MessageArchiveListBack) p;
        Map objOrdered = new LinkedHashMap();
        objOrdered.put("protocol", aux.getProtocol());
        objOrdered.put("command", aux.getCommand());
        objOrdered.put("status", aux.getStatus());
        objOrdered.put("back", aux.getBack());
        objOrdered.put("folder", aux.getFolder());
        objOrdered.put("file", aux.getFile());
        objOrdered.put("sender", aux.getSender());
        objOrdered.put("receptor", aux.getReceptor());
        String jsonText = JSONValue.toJSONString(objOrdered);
        return jsonText;
    }

    public MessageArchiveListBack stringToObjecArchiveListBack(String p) throws org.json.simple.parser.ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(p);
        JSONObject jsonObject = (JSONObject) obj;
        MessageArchiveListBack aux = new MessageArchiveListBack((String) jsonObject.get("protocol"),
                                                                (String) jsonObject.get("command"),
                                                                (String) jsonObject.get("status"),
                                                                (ArrayList<String>) jsonObject.get("back"),
                                                                (ArrayList<String>) jsonObject.get("folder"),
                                                                (ArrayList<String>) jsonObject.get("file"),
                                                                (String) jsonObject.get("sender"), 
                                                                (String) jsonObject.get("receptor"));
        return aux;
    }
}
