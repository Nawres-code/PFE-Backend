package rimenergyapi.security.entity;


import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "OPTION_E")
public class Option implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OPTION_ID")
	private long idOption;

	@Column(name = "DESCRIPTION", length = 80)
	private String description;

	public Option() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Option(String description) {
		super();
		this.description = description;
	}

	public long getIdOption() {
		return idOption;
	}

	public void setIdOption(long idOption) {
		this.idOption = idOption;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean equals(Option option) {
		if (this.getIdOption() == option.getIdOption() && this.getDescription().equals(option.getDescription())) {
			return true;

		} else {

			return false;
		}
	}

	public boolean contains(Set<Option> optionsw) {
		boolean found = false;
		for (Option ops : optionsw) {
			if (ops.equals(this)) {
				found = true;
				break;
			}
		}
		return found;
	}

}
