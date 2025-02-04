package rimenergyapi.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "output", schema = "tricity", catalog = "")
public class OutputEntity {
    private int id;
    private int deviceId;
    private int outputIdInDevice;
    private String name;
    private Byte inverted;
   // private int installationId;
    private String command;
    private InstallationEntity installation;
    
    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "device_id")
    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    @Basic
    @Column(name = "idoutputindevice")
    public int getOutputIdInDevice() {
        return outputIdInDevice;
    }

    public void setOutputIdInDevice(int outputIdInDevice) {
        this.outputIdInDevice = outputIdInDevice;
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
    @Column(name = "inverted")
    public Byte getInverted() {
        return inverted;
    }

    public void setInverted(Byte inverted) {
        this.inverted = inverted;
    }

//    @Basic
//    @Column(name = "installation_id")
//    public int getInstallationId() {
//        return installationId;
//    }
//
//    public void setInstallationId(int installationId) {
//        this.installationId = installationId;
//    }
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="installation_id")
    public InstallationEntity getInstallation() {
		return installation;
	}
    
    public void setInstallation(InstallationEntity installation) {
		this.installation = installation;
	}

    @Basic
    @Column(name = "command")
    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OutputEntity that = (OutputEntity) o;
        return id == that.id &&
                deviceId == that.deviceId &&
                outputIdInDevice == that.outputIdInDevice &&
               // installationId == that.installationId &&
                Objects.equals(name, that.name) &&
                Objects.equals(inverted, that.inverted) &&
                Objects.equals(command, that.command);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, deviceId, outputIdInDevice, name, inverted, command);
    }
}
