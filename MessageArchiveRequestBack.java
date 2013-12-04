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

public class MessageArchiveRequestBack {

    private String protocol;
    private String command;
    private String status;
    private String id;
    private String http_address;
    private String size;
    private String md5;
    private String sender;
    private String receptor;

    public MessageArchiveRequestBack(String protocol, String command, String status, String id, String http_address, String size, String md5, String sender, String receptor) {
        this.protocol = protocol;
        this.command = command;
        this.status = status;
        this.id = id;
        this.http_address = http_address;
        this.size = size;
        this.md5 = md5;
        this.sender = sender;
        this.receptor = receptor;
    }
    
    public MessageArchiveRequestBack(){
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHttpAdress() {
        return http_address;
    }

    public void setHttpAdress(String httpadress) {
        this.http_address = httpadress;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
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

    public String jsonToStringArchiveRequestBack(Object p) {
        MessageArchiveRequestBack aux = (MessageArchiveRequestBack) p;
        Map objOrdered = new LinkedHashMap();
        objOrdered.put("protocol", aux.getProtocol());
        objOrdered.put("command", aux.getCommand());
        objOrdered.put("status", aux.getStatus());
        objOrdered.put("id", aux.getId());
        objOrdered.put("http_adress", aux.getHttpAdress());
        objOrdered.put("size", aux.getSize());
        objOrdered.put("md5", aux.getMd5());
        objOrdered.put("sender", aux.getSender());
        objOrdered.put("receptor", aux.getReceptor());
        String jsonText = JSONValue.toJSONString(objOrdered);
        return jsonText;
    }

    public MessageArchiveRequestBack stringToObjecArchiveRequestBack(String p) throws org.json.simple.parser.ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(p);
        JSONObject jsonObject = (JSONObject) obj;
        MessageArchiveRequestBack aux = new MessageArchiveRequestBack((String) jsonObject.get("protocol"),
                                                                      (String) jsonObject.get("command"),
                                                                      (String) jsonObject.get("status"),
                                                                      (String) jsonObject.get("id"),
                                                                      (String) jsonObject.get("http_address"),
                                                                      (String) jsonObject.get("size"),
                                                                      (String) jsonObject.get("md5"),
                                                                      (String) jsonObject.get("sender"), 
                                                                      (String) jsonObject.get("receptor"));
        return aux;
    }
}
