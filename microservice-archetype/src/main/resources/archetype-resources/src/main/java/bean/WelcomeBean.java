#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.bean;

public class WelcomeBean {
	private String message;

	public WelcomeBean(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}