#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.bean;

public class TodoNotFoundException
		extends RuntimeException {
	public TodoNotFoundException(String msg) {
		super(msg);
	}

}