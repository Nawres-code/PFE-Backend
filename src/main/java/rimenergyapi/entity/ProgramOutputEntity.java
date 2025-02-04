package rimenergyapi.entity;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "program_output", schema = "tricity", catalog = "")
@IdClass(ProgramOutputID.class)
public class ProgramOutputEntity implements Serializable{
  
	private static final long serialVersionUID = 1L;
	private int programId;
    private int outputId;

    @Id
    @Column(name = "program_id")
    public int getProgramId() {
        return programId;
    }

    public void setProgramId(int programId) {
        this.programId = programId;
    }

    @Id
    @Column(name = "output_id")
    public int getOutputId() {
        return outputId;
    }

    public void setOutputId(int outputId) {
        this.outputId = outputId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProgramOutputEntity that = (ProgramOutputEntity) o;
        return programId == that.programId &&
                outputId == that.outputId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(programId, outputId);
    }
}
