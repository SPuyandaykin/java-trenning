package generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import model.GroupData;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class GroupDataGenerator {

    private Properties properties;

    @Parameter (names = "-c", description = "Group count")
    public int count;

    @Parameter (names = "-f", description = "Target file")
    public String file;

    public static void main(String[] args) throws IOException {
        GroupDataGenerator generator = new GroupDataGenerator();
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
        properties = new Properties();
        properties.load(new FileReader(new File("src/test/resources/local.properties")));
        List<GroupData> groups = generateGroups(count);
        String fileType = getFileFormat();
        if (fileType.equals("csv")) {
            saveAsCSV(groups, new File(file));
        } else if (fileType.equals("xml")){
            saveAsXML(groups, new File(file));
        } else if (fileType.equals("json")){
            saveAsJSON(groups, new File(file));
        } else {
            System.out.println("Unrecognized format " + fileType);
        }
    }

    private String getFileFormat(){
        int indexOfExtention = file.lastIndexOf(".");
        return file.substring(indexOfExtention+1, file.length());
    }

    private void saveAsJSON(List<GroupData> groups, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting()
                .excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(groups);
        try(Writer writer = new FileWriter(file)) {
            writer.write(json);
        }
    }

    private void saveAsXML(List<GroupData> groups, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(GroupData.class);
        String xml = xstream.toXML(groups);
        try(Writer writer = new FileWriter(file)) {
            writer.write(xml);
        }
    }

    private void saveAsCSV(List<GroupData> groups, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        try(Writer writer = new FileWriter(file)) {
            for (GroupData group : groups) {
                writer.write(String.format("%s;%s;%s\n",
                        group.getName(), group.getHeader(), group.getFooter()));
            }
        }
    }

    private List<GroupData> generateGroups(int count) {
        List<GroupData> groups = new ArrayList<GroupData>();

        for(int i = 0; i < count; i++) {
            String num = Integer.toString(i);
            groups.add(new GroupData()
                    .withName(properties.getProperty("group.name") + num)
                    .withHeader(properties.getProperty("group.header") + num)
                    .withFooter(properties.getProperty("group.footer") + num));
        }
        return groups;
    }
}
