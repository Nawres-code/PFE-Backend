package rimenergyapi.dto;

import java.sql.Timestamp;

public class DateIntervalDto {

	private Timestamp startDate;
	private Timestamp endDate;
	private int timeDiff;

	public DateIntervalDto() {
		super();
	}

	public DateIntervalDto(Timestamp startDate, Timestamp endDate) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public DateIntervalDto(Timestamp startDate, Timestamp endDate, int timeDiff) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.timeDiff = timeDiff;
	}

	/**
	 * @return the startDate
	 */
	public Timestamp getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Timestamp getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	public int getTimeDiff() {
		return timeDiff;
	}

	public void setTimeDiff(int timeDiff) {
		this.timeDiff = timeDiff;
	}

}
