package generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import model.ContactData;
import model.ContactNameData;
import model.ContactPhoneData;
import model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter (names = "-f", description = "Target file")
    public String file;

    public static void main(String[] args) throws IOException {

        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        }catch (ParameterException ex){
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        String fileType = getFileFormat();
        if (fileType.equals("csv")) {
            saveAsCSV(contacts, new File(file));
        } else if (fileType.equals("xml")){
            saveAsXML(contacts, new File(file));
        } else if (fileType.equals("json")){
            saveAsJSON(contacts, new File(file));
        } else {
            System.out.println("Unrecognized format " + fileType);
        }
    }

    private void saveAsJSON(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting()
                .excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        try(Writer writer = new FileWriter(file)) {
            writer.write(json);
        }
    }

    private void saveAsXML(List<ContactData> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);
        String xml = xstream.toXML(contacts);
        try(Writer writer = new FileWriter(file)) {
            writer.write(xml);
        }
    }

    private String getFileFormat(){
        int indexOfExtention = file.lastIndexOf(".");
        return file.substring(indexOfExtention+1, file.length());
    }

    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for(int i = 0; i < count; i++) {
            String sNum = Integer.toString(i);
            ContactNameData contactName = new ContactNameData("sergey"+sNum,
                    "ivanov"+sNum, "Java Corporation"+sNum);
            ContactPhoneData contactPhone = new ContactPhoneData ("+7903111111"+sNum, "+7904222222"+sNum,
                    "+7905333333"+sNum, sNum+"ya@yandex.ru", "Moscow, Red square, building №"+sNum);
            contacts.add(new ContactData().withContactName(contactName).withContactPhone(contactPhone));
        }
        return contacts;
    }

    private void saveAsCSV(List<ContactData> contacts, File file) throws IOException {
        try(Writer writer = new FileWriter(file)) {
            for (ContactData contact : contacts) {
                writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s\n",
                        contact.getContactName().getFirstName(),
                        contact.getContactName().getLastName(),
                        contact.getContactName().getCompany(),
                        contact.getContactPhone().getPhoneHome(),
                        contact.getContactPhone().getPhoneMobile(),
                        contact.getContactPhone().getWorkMobile(),
                        contact.getContactPhone().getEmail(),
                        contact.getContactPhone().getAddress()
                ));
            }
        }
    }
}
