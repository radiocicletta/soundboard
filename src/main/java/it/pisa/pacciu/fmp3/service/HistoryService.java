/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.pisa.pacciu.fmp3.service;

import it.pisa.pacciu.fmp3.jaxb.History;
import it.pisa.pacciu.fmp3.jaxb.History.FileLoaded;
import it.pisa.pacciu.fmp3.jaxb.ObjectFactory;
import java.io.File;
import java.util.Set;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author m.luly
 */
@SuppressWarnings("CallToThreadDumpStack")
public class HistoryService {

    public final static String HOME_DIR = System.getProperty("user.home");
    private final static HistoryService HS = new HistoryService();
    public final static String HISTORY_FOLDER = ".fMp3";
    public final static String HISTORY_FILENAME = "history.xml";
    private File historyFile = null;
    private History history = null;

    private HistoryService() {
        File homeFolder = new File(new File(HOME_DIR), HISTORY_FOLDER);
        if (!homeFolder.exists()) {
            homeFolder.mkdir();
        }
        historyFile = new File(homeFolder, HISTORY_FILENAME);
        history = historyFile.exists() ? loadHistory() : new History(HOME_DIR);
    }

    public static HistoryService getInstance() {
        return HS;
    }

    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
    }

    private History loadHistory() {
        History ris = null;
        try {
            JAXBContext context = JAXBContext.newInstance(ObjectFactory.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            ris = (History) unmarshaller.unmarshal(historyFile);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ris;
    }

    private void saveHistory() {
        try {
            JAXBContext context = JAXBContext.newInstance(ObjectFactory.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(history, historyFile);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String getRootPath() {
        return history.getRootPath();
    }

    public void setRootPath(String rootPath) {
        history.setRootPath(rootPath);
        saveHistory();
    }

    public Set<FileLoaded> getFileLoadeds() {
        return history.getFileLoadeds();
    }

    public void addFileLoaded(String key, String path) {
        history.getFileLoadeds().add(new FileLoaded(key, path));
        saveHistory();
    }
    
    public void removeFileLoaded(String key){
        history.getFileLoadeds().remove(new FileLoaded(key, null));
        saveHistory();
    }
}
