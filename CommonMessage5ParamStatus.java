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

public class CommonMessage5ParamStatus {
    
    private String protocol;
    private String command;
    private String status;
    private String sender;
    private String receptor;

    public CommonMessage5ParamStatus(String protocol, String command, String status, String sender, String receptor) {
        this.protocol = protocol;
        this.command = command;
        this.status = status;
        this.sender = sender;
        this.receptor = receptor;
    }
    
    public CommonMessage5ParamStatus(){
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
    
    public String jsonToString5ParamStatus(Object p) {
        CommonMessage5ParamStatus aux = (CommonMessage5ParamStatus) p;
        Map objOrdered = new LinkedHashMap();
        objOrdered.put("protocol", aux.getProtocol());
        objOrdered.put("command", aux.getCommand());
        objOrdered.put("status", aux.getStatus());
        objOrdered.put("sender", aux.getSender());
        objOrdered.put("receptor", aux.getReceptor());
        String jsonText = JSONValue.toJSONString(objOrdered);
        return jsonText;
    }

    public CommonMessage5ParamStatus stringToObjec5ParamStatus(String p) throws org.json.simple.parser.ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(p);
        JSONObject jsonObject = (JSONObject) obj;
        CommonMessage5ParamStatus aux = new CommonMessage5ParamStatus((String) jsonObject.get("protocol"),
                                                                      (String) jsonObject.get("command"),
                                                                      (String) jsonObject.get("status"),
                                                                      (String) jsonObject.get("sender"), 
                                                                      (String) jsonObject.get("receptor"));
        return aux;
    }
}
