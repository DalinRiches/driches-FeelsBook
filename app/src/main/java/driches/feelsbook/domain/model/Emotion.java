package driches.feelsbook.domain.model;

import java.util.Date;

public class Emotion {
    private Date timeStamp;
    private String comment;
    private String type;

    public Emotion(String type) {
        this.setType(type);
        this.setTimeStamp(new Date());
        this.setComment(null);
    }

    public Emotion(String type, Date timeStamp) {
        this.setType(type);
        this.setTimeStamp(timeStamp);
        this.setComment(null);
    }

    public Emotion(String type, Date timeStamp, String comment) {
        this.setType(type);
        this.setTimeStamp(timeStamp);
        this.setComment(comment);
    }

    public Emotion(String type, String comment) {
        this.setType(type);
        this.setTimeStamp(new Date());
        this.setComment(comment);
    }

    public Date getTimeStamp() {
        return this.timeStamp;
    }

    public String getComment() {
        return this.comment;
    }

    public String getType() { return this.type; }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object object) {
        Emotion other;

        if (object == null || object.getClass() != getClass()) { 
            return false; 
        }

        other = (Emotion) object;

        if (this.getTimeStamp() == null ? (other.getTimeStamp() != null) : !this.getTimeStamp().equals(other.getTimeStamp())) { return false; }
        if (this.getComment() == null ? (other.getComment() != null) : !this.getComment().equals(other.getComment())) { return false; }
        if (this.getType() == null ? (other.getType() != null) : !this.getType().equals(other.getType())) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        int result = 17;
        
        result = 31 * result + getTimeStamp().hashCode();
        result = 31 * result + getComment().hashCode();
        result = 31 * result + getType().hashCode();

        return result;
    }
}