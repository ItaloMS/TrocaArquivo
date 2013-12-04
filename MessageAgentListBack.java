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

public class MessageAgentListBack {
    
    private String protocol;
    private String command;
    private String status;
    private ArrayList<String> back;
    private String sender;
    private String receptor;

    public MessageAgentListBack(String protocol, String command, String status, ArrayList<String> back, String sender, String receptor) {
        this.protocol = protocol;
        this.command = command;
        this.status = status;
        this.back = back;
        this.sender = sender;
        this.receptor = receptor;
           
    }

    public MessageAgentListBack(){
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
    
    public String jsonToStringAgentListBack(Object p) {
        MessageAgentListBack aux = (MessageAgentListBack) p;
        Map objOrdered = new LinkedHashMap();
        objOrdered.put("protocol", aux.getProtocol());
        objOrdered.put("command", aux.getCommand());
        objOrdered.put("status", aux.getStatus());
        objOrdered.put("back", aux.getBack());
        objOrdered.put("sender", aux.getSender());
        objOrdered.put("receptor", aux.getReceptor());
        String jsonText = JSONValue.toJSONString(objOrdered);
        return jsonText;
    }

    public MessageAgentListBack stringToObjecAgentListBack(String p) throws org.json.simple.parser.ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(p);
        JSONObject jsonObject = (JSONObject) obj;
        MessageAgentListBack aux = new MessageAgentListBack((String) jsonObject.get("protocol"),
                                                            (String) jsonObject.get("command"),
                                                            (String) jsonObject.get("status"),
                                                            (ArrayList<String>) jsonObject.get("back"),
                                                            (String) jsonObject.get("sender"), 
                                                            (String) jsonObject.get("receptor"));
        return aux;
    }
}
