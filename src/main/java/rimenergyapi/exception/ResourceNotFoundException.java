package rimenergyapi.exception;

public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long resourceId;

	public ResourceNotFoundException(String message) {
		super(message);
	}

	public ResourceNotFoundException(Long resourceId, String message) {
		super(message);
		this.resourceId = resourceId;
	}

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

}