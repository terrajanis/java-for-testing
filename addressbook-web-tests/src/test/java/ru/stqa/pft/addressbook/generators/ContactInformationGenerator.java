package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.models.ContactInformation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactInformationGenerator {

    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter (names = "-f", description = "Target file")
    public String file;

    @Parameter (names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        ContactInformationGenerator generator = new ContactInformationGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        }
        catch (ParameterException e) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactInformation> contacts = generateContacts(count);
        if (format.equals("csv")) {
            saveAsCsv(contacts, new File(file));
        } else if (format.equals("xml")){
            saveAsXml(contacts, new File(file));
        } else if (format.equals("json")){
            saveAsJson(contacts, new File(file));
        } else {
            System.out.println("Unrecognized format " + format);
        }

    }

    private List<ContactInformation> generateContacts(int count) {
        List<ContactInformation> contacts = new ArrayList<ContactInformation>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactInformation().
                    withFirstname(String.format("Gintoki%s", i)).
                    withLastname(String.format("Sakata%s", i)).
                    withNickname(String.format("Gin-san%s", i)).
                    withTitle(String.format("Chief%s", i)).
                    withCompany(String.format("Yorozuya%s", i)).
                    withAddress(String.format("Japan\n Tokyo\n Kabuki-cho\n %s", i)).
                    withHome(String.format("111%s", i)).
                    withMobile(String.format("222%s", i)).
                    withWork(String.format("333%s", i)).
                    withFax(String.format("4444%s", i)).
                    withEmail(String.format("gin-san%s@yandex.ru", i)).
                    withEmail2(String.format("kagura-chan%s@yandex.ru", i)).
                    withEmail3(String.format("shinpachi%s@yandex.ru", i)));
        }
        return contacts;
    }


    private void saveAsJson(List<ContactInformation> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        try (Writer writer = new FileWriter(file)) {
            writer.write(json);
        }
    }

    private void saveAsXml(List<ContactInformation> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactInformation.class);
        String xml = xstream.toXML(contacts);
        try(Writer writer = new FileWriter(file)) {
            writer.write(xml);
        }
    }

    private void saveAsCsv(List<ContactInformation> contacts, File file) throws IOException {
        try (Writer writer = new FileWriter(file)) {
            for (ContactInformation contact : contacts) {
                writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s",
                        contact.getFirstname(),
                        contact.getLastname(),
                        contact.getNickname(),
                        contact.getTitle(),
                        contact.getCompany(),
                        contact.getAddress(),
                        contact.getHome(),
                        contact.getMobile(),
                        contact.getWork(),
                        contact.getFax(),
                        contact.getEmail(),
                        contact.getEmail2(),
                        contact.getEmail3()));
            }
        }

    }
}
