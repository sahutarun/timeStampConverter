package properties;

public class getProperties {

    private static final PropertiesReader propertiesReader = new PropertiesReader();
    public static final String host = propertiesReader.getHost();

}
