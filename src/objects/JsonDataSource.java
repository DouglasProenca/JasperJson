package objects;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import org.json.JSONArray;
import org.json.JSONObject;

public class JsonDataSource implements JRDataSource {
    private JSONArray jsonArray;
    private int index;

    public JsonDataSource(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
        this.index = -1;
    }

    @Override
    public boolean next() throws JRException {
        index++;
        return index < jsonArray.length();
    }

    @Override
    public Object getFieldValue(JRField field) throws JRException {
        JSONObject jsonObject = jsonArray.getJSONObject(index);
        return jsonObject.get(field.getName());
    }
}
