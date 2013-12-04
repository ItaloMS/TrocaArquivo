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

public class CommonMessage5ParamID {
    
    private String protocol;
    private String command;
    private String id;
    private String sender;
    private String receptor;

    public CommonMessage5ParamID(String protocol, String command, String id, String sender, String receptor) {
        this.protocol = protocol;
        this.command = command;
        this.id = id;
        this.sender = sender;
        this.receptor = receptor;
    }
    
    public CommonMessage5ParamID(){
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    
    public String jsonToString5ParamID(Object p) {
        CommonMessage5ParamID aux = (CommonMessage5ParamID) p;
        Map objOrdered = new LinkedHashMap();
        objOrdered.put("protocol", aux.getProtocol());
        objOrdered.put("command", aux.getCommand());
        objOrdered.put("id", aux.getId());
        objOrdered.put("sender", aux.getSender());
        objOrdered.put("receptor", aux.getReceptor());
        String jsonText = JSONValue.toJSONString(objOrdered);
        return jsonText;
    }

    public CommonMessage5ParamID stringToObjec5ParamID(String p) throws org.json.simple.parser.ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(p);
        JSONObject jsonObject = (JSONObject) obj;
        CommonMessage5ParamID aux = new CommonMessage5ParamID((String) jsonObject.get("protocol"),
                                                              (String) jsonObject.get("command"),
                                                              (String) jsonObject.get("id"),
                                                              (String) jsonObject.get("sender"), 
                                                              (String) jsonObject.get("receptor"));
        return aux;
    }
    
}
