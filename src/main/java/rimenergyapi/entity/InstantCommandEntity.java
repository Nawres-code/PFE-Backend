package rimenergyapi.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "instant_command", schema = "tricity", catalog = "")
public class InstantCommandEntity {
    private int id;
    private int outputId;
    private String cmd;
    private Date date;
    private String dateAck;
    private int status;
    private String regex;
    private Integer type;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "output_id")
    public int getOutputId() {
        return outputId;
    }

    public void setOutputId(int outputId) {
        this.outputId = outputId;
    }

    @Basic
    @Column(name = "cmd")
    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    @Basic
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "date_ack")
    public String getDateAck() {
        return dateAck;
    }

    public void setDateAck(String dateAck) {
        this.dateAck = dateAck;
    }

    @Basic
    @Column(name = "status")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Basic
    @Column(name = "regex")
    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    @Basic
    @Column(name = "type")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InstantCommandEntity that = (InstantCommandEntity) o;
        return id == that.id &&
                outputId == that.outputId &&
                status == that.status &&
                Objects.equals(cmd, that.cmd) &&
                Objects.equals(date, that.date) &&
                Objects.equals(dateAck, that.dateAck) &&
                Objects.equals(regex, that.regex) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, outputId, cmd, date, dateAck, status, regex, type);
    }
}
