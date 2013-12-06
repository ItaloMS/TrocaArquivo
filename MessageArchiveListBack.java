//     Universidade Federal de Pelotas
//  Centro de Desenvolvimento Tecnológico
//   Bacharelado em Ciência da Computação
//         Redes de Computadores
//  Trabalho 1 - Troca Arquivos pela Rede
// Por Italo M. Silveira e Maicon S. Cardoso

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

//essa lib do array nao tinhamos e eh importante pra funcionar
import net.sf.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author italo
 */

// tem q substituir nossa MessageArchiveListBack
public class MessageArchiveListBack {

    private String protocol;
    private String status;
    private String receptor;
    private String command;
    private ArrayList<JSONArray> back;
    private String sender;

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReceptor() {
        return receptor;
    }

    public void setReceptor(String receptor) {
        this.receptor = receptor;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public ArrayList<JSONArray> getBack() {
        return back;
    }

    public void setBack(ArrayList<JSONArray> back) {
        this.back = back;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public MessageArchiveListBack(){}
    
    public MessageArchiveListBack(String protocol, String status, String receptor, String command, ArrayList<JSONArray> back, String sender) {
        this.protocol = protocol;
        this.status = status;
        this.receptor = receptor;
        this.command = command;
        this.back = back;
        this.sender = sender;
    }
    


    public String jsonToStringArchiveListBack(Object p) {
        MessageArchiveListBack aux = (MessageArchiveListBack) p;
        Map objOrdered = new LinkedHashMap();
        objOrdered.put("protocol", aux.getProtocol());
        objOrdered.put("status", aux.getStatus());
        objOrdered.put("receptor", aux.getReceptor());
        objOrdered.put("command", aux.getCommand());
        objOrdered.put("back", aux.getBack());
        objOrdered.put("sender", aux.getSender());
        String jsonText = JSONValue.toJSONString(objOrdered);
        return jsonText;
    }

    public MessageArchiveListBack stringToObjecArchiveListBack(String p) throws ParseException {
        
        
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(p);
       
        JSONObject jsonObject = (JSONObject) obj;

        MessageArchiveListBack aux = new MessageArchiveListBack((String) jsonObject.get("protocol"),
                (String) jsonObject.get("status"),
                (String) jsonObject.get("receptor"),
                (String) jsonObject.get("command"),
                (ArrayList<JSONArray>) jsonObject.get("back"),
                (String) jsonObject.get("sender"));
               
        return aux;
    }
   
}
