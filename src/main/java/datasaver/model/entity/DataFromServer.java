package datasaver.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class DataFromServer {
    @Id
    private String id;
    private String hash;
    private String format;
    private String url;
    private String title;
    private String documentOf;
    private String datePublished;
    private String dateModified;
    private String relatedItem;

    public DataFromServer() {
    }

    public DataFromServer(String id, String hash, String format, String url, String title, String documentOf, String datePublished, String dateModified, String relatedItem) {
        this.id = id;
        this.hash = hash;
        this.format = format;
        this.url = url;
        this.title = title;
        this.documentOf = documentOf;
        this.datePublished = datePublished;
        this.dateModified = dateModified;
        this.relatedItem = relatedItem;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDocumentOf() {
        return documentOf;
    }

    public void setDocumentOf(String documentOf) {
        this.documentOf = documentOf;
    }

    public String getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(String datePublished) {
        this.datePublished = datePublished;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    public String getRelatedItem() {
        return relatedItem;
    }

    public void setRelatedItem(String relatedItem) {
        this.relatedItem = relatedItem;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "DataFromServer{" +
                "hash='" + hash + '\'' +
                ", format='" + format + '\'' +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", documentOf='" + documentOf + '\'' +
                ", datePublished=" + datePublished +
                ", dateModified=" + dateModified +
                ", relatedItem='" + relatedItem + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataFromServer that = (DataFromServer) o;

        if (hash != null ? !hash.equals(that.hash) : that.hash != null) return false;
        if (format != null ? !format.equals(that.format) : that.format != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (documentOf != null ? !documentOf.equals(that.documentOf) : that.documentOf != null) return false;
        if (datePublished != null ? !datePublished.equals(that.datePublished) : that.datePublished != null)
            return false;
        if (dateModified != null ? !dateModified.equals(that.dateModified) : that.dateModified != null) return false;
        if (relatedItem != null ? !relatedItem.equals(that.relatedItem) : that.relatedItem != null) return false;
        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        int result = hash != null ? hash.hashCode() : 0;
        result = 31 * result + (format != null ? format.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (documentOf != null ? documentOf.hashCode() : 0);
        result = 31 * result + (datePublished != null ? datePublished.hashCode() : 0);
        result = 31 * result + (dateModified != null ? dateModified.hashCode() : 0);
        result = 31 * result + (relatedItem != null ? relatedItem.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}
