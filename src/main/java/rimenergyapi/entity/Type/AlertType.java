package rimenergyapi.entity.Type;

public enum AlertType {
    AUTOMATIQUE("automatique"), PERSONNALISE("personnalise");

    private String value ;

    private AlertType(String state){
        this.value = state;
    }

    public String getValue(){
        return value;
    }

    public void setValue(String value){
        this.value = value;
    }

    public static AlertType fromValue(String x){
        if(x == null){
            return null;
        }
        switch (x){
            case "automatique":
                return AUTOMATIQUE;
            case "personnalise" :
                return PERSONNALISE;
        }
        return null;
    }
}
