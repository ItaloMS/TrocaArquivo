//     Universidade Federal de Pelotas
//  Centro de Desenvolvimento Tecnológico
//   Bacharelado em Ciência da Computação
//         Redes de Computadores
//  Trabalho 1 - Troca Arquivos pela Rede
// Por Italo M. Silveira e Maicon S. Cardoso

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

public class CommonMessage4Parameters implements Serializable {

    private String protocol;
    private String command;
    private String sender;
    private String receptor;

    public CommonMessage4Parameters(String protocol, String command, String sender, String receptor) {
        this.protocol = protocol;
        this.command = command;
        this.sender = sender;
        this.receptor = receptor;
    }
    
    public CommonMessage4Parameters(){
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

    public String jsonToString4Param(Object p) {
        CommonMessage4Parameters pAux = (CommonMessage4Parameters) p;
        Map objOrdered = new LinkedHashMap();
        objOrdered.put("protocol", pAux.getProtocol());
        objOrdered.put("command", pAux.getCommand());
        objOrdered.put("sender", pAux.getSender());
        objOrdered.put("receptor", pAux.getReceptor());
        String jsonText = JSONValue.toJSONString(objOrdered);
        return jsonText;
    }

    public CommonMessage4Parameters stringToObjec4Param(String p) throws org.json.simple.parser.ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(p);
        JSONObject jsonObject = (JSONObject) obj;
        CommonMessage4Parameters aux = new CommonMessage4Parameters((String) jsonObject.get("protocol"),
                                                                    (String) jsonObject.get("command"),
                                                                    (String) jsonObject.get("sender"), 
                                                                    (String) jsonObject.get("receptor"));
        return aux;
    }

}
