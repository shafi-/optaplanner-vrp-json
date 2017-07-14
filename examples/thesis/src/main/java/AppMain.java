/**
 * Created by shafi on 7/8/17.
 */
public class AppMain {
    static String xml;
    static String json;

    public static void main(String[] args)
    {
        json = FileOperation.readJson();
        xml = MyConverter.jsonToXml(json);
        //xml = solver.solver(xml);
        json = MyConverter.xmlToJson(xml);
        FileOperation.writeJson(json);
    }
}
