package rimenergyapi.userData.model;

import java.math.BigInteger;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


public class ArchiveTemperatureDto {

	   //private ArchiveTemperatureId id;
	     private Timestamp time;
	     private BigInteger sonde_id;
	     private Integer temperature;
	     private Integer do0;
	     private Integer do1;
	     private Integer do2;
	     private Integer do3;
	     private Integer humidity;
	     private Integer batteryPercentage;
	     private Integer di0;
	     private Integer di1;
	     private Integer di2;
	     private Integer di3;
	     
	     public ArchiveTemperatureDto() {
	     }
	     
	     public ArchiveTemperatureDto( Integer temperature, Integer do0, Integer do1, Integer do2, Integer do3, Integer humidity) {
	         this.temperature = temperature;
	         this.do0 = do0;
	         this.do1 = do1;
	         this.do2 = do2;
	         this.do3 = do3;
	         this.humidity = humidity;
	      }

		public Timestamp getTime() {
			return time;
		}

		public void setTime(Timestamp time) {
			this.time = time;
		}

		public BigInteger getSonde_id() {
			return sonde_id;
		}

		public void setSonde_id(BigInteger sonde_id) {
			this.sonde_id = sonde_id;
		}

		public Integer getTemperature() {
			return temperature;
		}

		public void setTemperature(Integer temperature) {
			this.temperature = temperature;
		}

		public Integer getDo0() {
			return do0;
		}

		public void setDo0(Integer do0) {
			this.do0 = do0;
		}

		public Integer getDo1() {
			return do1;
		}

		public void setDo1(Integer do1) {
			this.do1 = do1;
		}

		public Integer getDo2() {
			return do2;
		}

		public void setDo2(Integer do2) {
			this.do2 = do2;
		}

		public Integer getDo3() {
			return do3;
		}

		public void setDo3(Integer do3) {
			this.do3 = do3;
		}

		public Integer getHumidity() {
			return humidity;
		}

		public void setHumidity(Integer humidity) {
			this.humidity = humidity;
		}

		public Integer getBatteryPercentage() {
			return batteryPercentage;
		}

		public void setBatteryPercentage(Integer batteryPercentage) {
			this.batteryPercentage = batteryPercentage;
		}

		public Integer getDi0() {
			return di0;
		}

		public void setDi0(Integer di0) {
			this.di0 = di0;
		}

		public Integer getDi1() {
			return di1;
		}

		public void setDi1(Integer di1) {
			this.di1 = di1;
		}

		public Integer getDi2() {
			return di2;
		}

		public void setDi2(Integer di2) {
			this.di2 = di2;
		}

		public Integer getDi3() {
			return di3;
		}

		public void setDi3(Integer di3) {
			this.di3 = di3;
		}
	     

}

