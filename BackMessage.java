//     Universidade Federal de Pelotas
//  Centro de Desenvolvimento Tecnológico
//   Bacharelado em Ciência da Computação
//         Redes de Computadores
//  Trabalho 1 - Troca Arquivos pela Rede
// Por Italo M. Silveira e Maicon S. Cardoso

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import net.sf.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;


class BackMessage {

    private String id;
    private String name;
    private String size;

    public BackMessage() {
    }

    public BackMessage(String id, String name, String size) {
        this.id = id;
        this.name = name;
        this.size = size;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String jsonToStringBackMessage(Object p) {
        BackMessage aux = (BackMessage) p;
        Map objOrdered = new LinkedHashMap();
        objOrdered.put("id", aux.getId());
        objOrdered.put("name", aux.getName());
        objOrdered.put("size", aux.getSize());
        String jsonText = JSONValue.toJSONString(objOrdered);
        return jsonText;
    }

    public BackMessage stringToObjectBackMessage(String p) throws org.json.simple.parser.ParseException {

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(p);

        JSONObject jsonObject = (JSONObject) obj;

        BackMessage aux = new BackMessage((String) jsonObject.get("id"),
                (String) jsonObject.get("name"),
                (String) jsonObject.get("size"));

        return aux;
    }
}
