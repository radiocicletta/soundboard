/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.pisa.pacciu.fmp3.jaxb;

import java.util.HashSet;
import java.util.Set;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author m.luly
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "rootPath", "fileLoadeds"
})
@XmlRootElement(name="history")
public class History {

    @XmlElement(name = "rootPath", required = true)
    private String rootPath;
    @XmlElement(name = "file", required = true)
    private Set<History.FileLoaded> fileLoadeds;

    public History() {
    }

    public History(String rootPath) {
        this.rootPath = rootPath;
    }

    public Set<FileLoaded> getFileLoadeds() {
        if (fileLoadeds == null) {
            fileLoadeds = new HashSet<FileLoaded>();
        }
        return fileLoadeds;
    }

    public void setFileLoadeds(Set<FileLoaded> fileLoadeds) {
        this.fileLoadeds = fileLoadeds;
    }

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "file", propOrder = {
        "key", "path"
    })
    public static class FileLoaded {

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final FileLoaded other = (FileLoaded) obj;
            if ((this.key == null) ? (other.key != null) : !this.key.equals(other.key)) {
                return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 59 * hash + (this.key != null ? this.key.hashCode() : 0);
            return hash;
        }

        @XmlElement(name = "key", required = true)
        private String key;
        @XmlElement(name = "path", required = true)
        private String path;

        public FileLoaded(String key, String path) {
            this.key = key;
            this.path = path;
        }

        public FileLoaded() {
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }
}
