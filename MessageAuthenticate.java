//     Universidade Federal de Pelotas
//  Centro de Desenvolvimento Tecnológico
//   Bacharelado em Ciência da Computação
//         Redes de Computadores
//  Trabalho 1 - Troca Arquivos pela Rede
// Por Italo M. Silveira e Maicon S. Cardoso

import java.util.LinkedHashMap;
import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

public class MessageAuthenticate {

    private String protocol;
    private String command;
    private String passport;
    private String sender;
    private String receptor;

    public MessageAuthenticate(String protocol, String command, String passport, String sender, String receptor) {
        this.protocol = protocol;
        this.command = command;
        this.passport = passport;
        this.sender = sender;
        this.receptor = receptor;
    }
    
    public MessageAuthenticate(){
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

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
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
    
    public String jsonToStringAuthenticate(Object p) {
        MessageAuthenticate aux = (MessageAuthenticate) p;
        Map objOrdered = new LinkedHashMap();
        objOrdered.put("protocol", aux.getProtocol());
        objOrdered.put("command", aux.getCommand());
        objOrdered.put("passport", aux.getPassport());
        objOrdered.put("sender", aux.getSender());
        objOrdered.put("receptor", aux.getReceptor());
        String jsonText = JSONValue.toJSONString(objOrdered);
        return jsonText;
    }

    public MessageAuthenticate stringToObjecAuthenticate(String p) throws org.json.simple.parser.ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(p);
        JSONObject jsonObject = (JSONObject) obj;
        MessageAuthenticate aux = new MessageAuthenticate((String) jsonObject.get("protocol"),
                                                          (String) jsonObject.get("command"),
                                                          (String) jsonObject.get("passport"),
                                                          (String) jsonObject.get("sender"), 
                                                          (String) jsonObject.get("receptor"));
        return aux;
    }  
}
