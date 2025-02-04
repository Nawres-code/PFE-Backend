package rimenergyapi.dto.generic;

public class ValueDto<T> {

	private T value;

	public ValueDto(T value) {
		super();
		this.value = value;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

}
