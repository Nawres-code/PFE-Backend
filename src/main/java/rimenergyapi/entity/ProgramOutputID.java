package rimenergyapi.entity;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class ProgramOutputID implements Serializable {

	private static final long serialVersionUID = 1L;

	private int programId;
	private int outputId;

	public ProgramOutputID() {
	}

	public ProgramOutputID(int programId, int outputId) {
		this.programId = programId;
		this.outputId = outputId;
	}

	public int getProgramId() {
		return programId;
	}

	public void setProgramId(int programId) {
		this.programId = programId;
	}

	public int getOutputId() {
		return outputId;
	}

	public void setOutputId(int outputId) {
		this.outputId = outputId;
	}

}
