package rimenergyapi.userData.model;

import java.sql.Timestamp;

public class RtEnergyDto {
    private int id;
    private Timestamp time;
    private double actEnergyDay;
    private double reactEnergyDay;
    private double fundEnergyDay;
    private double appEnergyDay;
    private double iPower;
    private boolean isGeneral=false;
    
    public RtEnergyDto(int id, Timestamp time, double actEnergyDay, double reactEnergyDay, double fundEnergyDay, double appEnergyDay) {
        this.id = id;
        this.time = time;
        this.actEnergyDay = actEnergyDay;
        this.reactEnergyDay = reactEnergyDay;
        this.fundEnergyDay = fundEnergyDay;
        this.appEnergyDay = appEnergyDay;
     }
    
    
     public RtEnergyDto(int id, Timestamp time, double actEnergyDay, double reactEnergyDay, double fundEnergyDay,
			double appEnergyDay, boolean isGeneral) {
		super();
		this.id = id;
		this.time = time;
		this.actEnergyDay = actEnergyDay;
		this.reactEnergyDay = reactEnergyDay;
		this.fundEnergyDay = fundEnergyDay;
		this.appEnergyDay = appEnergyDay;
		this.isGeneral = isGeneral;
	}


	public int getId() {
         return this.id;
     }
     
     public void setId(int id) {
         this.id = id;
     }

     public Timestamp getTime() {
         return this.time;
     }
     
     public void setTime(Timestamp time) {
         this.time = time;
     }

     public double getActEnergyDay() {
         return this.actEnergyDay;
     }
     
     public void setActEnergyDay(double actEnergyDay) {
         this.actEnergyDay = actEnergyDay;
     }

     public double getReactEnergyDay() {
         return this.reactEnergyDay;
     }
     
     public void setReactEnergyDay(double reactEnergyDay) {
         this.reactEnergyDay = reactEnergyDay;
     }

     public double getFundEnergyDay() {
         return this.fundEnergyDay;
     }
     
     public void setFundEnergyDay(double fundEnergyDay) {
         this.fundEnergyDay = fundEnergyDay;
     }

     public double getAppEnergyDay() {
         return this.appEnergyDay;
     }
     
     public void setAppEnergyDay(double appEnergyDay) {
         this.appEnergyDay = appEnergyDay;
     }


	public boolean isGeneral() {
		return isGeneral;
	}


	public void setGeneral(boolean isGeneral) {
		this.isGeneral = isGeneral;
	}


	public double getiPower() {
		return iPower;
	}


	public void setiPower(double iPower) {
		this.iPower = iPower;
	}
	
	
}
