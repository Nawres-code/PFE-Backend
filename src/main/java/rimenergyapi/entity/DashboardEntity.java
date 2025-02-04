package rimenergyapi.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "dashboard", schema = "tricity", catalog = "")
public class DashboardEntity {
    private int id;
    private int widgetId;
    private int idUser;
    private int line;
    private String colMd;
    private String colLg;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "widget_id")
    public int getWidgetId() {
        return widgetId;
    }

    public void setWidgetId(int widgetId) {
        this.widgetId = widgetId;
    }

    @Basic
    @Column(name = "id_user")
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "line")
    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    @Basic
    @Column(name = "col_md")
    public String getColMd() {
        return colMd;
    }

    public void setColMd(String colMd) {
        this.colMd = colMd;
    }

    @Basic
    @Column(name = "col_lg")
    public String getColLg() {
        return colLg;
    }

    public void setColLg(String colLg) {
        this.colLg = colLg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DashboardEntity that = (DashboardEntity) o;
        return id == that.id &&
                widgetId == that.widgetId &&
                idUser == that.idUser &&
                line == that.line &&
                Objects.equals(colMd, that.colMd) &&
                Objects.equals(colLg, that.colLg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, widgetId, idUser, line, colMd, colLg);
    }
}
