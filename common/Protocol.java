
package common;

public class Protocol {
	/* command codes, client -> server */
	public static final int COM_LOGIN_USER		= 0;
	public static final int COM_SEND_MESSAGE	= 1;
	public static final int COM_END				= 2;

	/* answer codes, server -> client */
	public static final int ANS_LOGIN_USER		= 20;
	public static final int ANS_SEND_MESSAGE	= 21;
	public static final int ANS_END				= 22;

	/* parameters */
	public static final int PAR_STRING			= 40;
	public static final int PAR_NUM				= 41;

}
