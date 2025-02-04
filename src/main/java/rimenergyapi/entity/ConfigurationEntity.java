package rimenergyapi.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "configuration", schema = "tricity", catalog = "")
public class ConfigurationEntity {
    private int id;
    private double ilsb;
    private double vlsb;
    private double phlsb;
    private double flsb;
    private double elsb;
    private String name;
    private int divisor;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ilsb")
    public double getIlsb() {
        return ilsb;
    }

    public void setIlsb(double ilsb) {
        this.ilsb = ilsb;
    }

    @Basic
    @Column(name = "vlsb")
    public double getVlsb() {
        return vlsb;
    }

    public void setVlsb(double vlsb) {
        this.vlsb = vlsb;
    }

    @Basic
    @Column(name = "phlsb")
    public double getPhlsb() {
        return phlsb;
    }

    public void setPhlsb(double phlsb) {
        this.phlsb = phlsb;
    }

    @Basic
    @Column(name = "flsb")
    public double getFlsb() {
        return flsb;
    }

    public void setFlsb(double flsb) {
        this.flsb = flsb;
    }

    @Basic
    @Column(name = "elsb")
    public double getElsb() {
        return elsb;
    }

    public void setElsb(double elsb) {
        this.elsb = elsb;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "divisor")
    public int getDivisor() {
        return divisor;
    }

    public void setDivisor(int divisor) {
        this.divisor = divisor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConfigurationEntity that = (ConfigurationEntity) o;
        return id == that.id &&
                Double.compare(that.ilsb, ilsb) == 0 &&
                Double.compare(that.vlsb, vlsb) == 0 &&
                Double.compare(that.phlsb, phlsb) == 0 &&
                Double.compare(that.flsb, flsb) == 0 &&
                Double.compare(that.elsb, elsb) == 0 &&
                divisor == that.divisor &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ilsb, vlsb, phlsb, flsb, elsb, name, divisor);
    }
}
