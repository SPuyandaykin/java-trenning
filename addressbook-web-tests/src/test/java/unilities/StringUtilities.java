package unilities;

public class StringUtilities {

    public String cleanPhone (String phone){
        return phone.replaceAll("\\s", "").replaceAll("[-()]","");
    }
}
