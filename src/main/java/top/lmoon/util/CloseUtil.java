package top.lmoon.util;

import java.io.Closeable;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.util.zip.ZipFile;

public final class CloseUtil {
	public static void closeSilently(Connection rsc) {
		if (null != rsc) {
			try {
				rsc.close();
			} catch (Exception ex) {
				// empty catch block
			}
			rsc = null;
		}
	}

	public static void closeSilently(Statement rsc) {
		if (null != rsc) {
			try {
				rsc.close();
			} catch (Exception ex) {
				// empty catch block
			}
			rsc = null;
		}
	}

	public static void closeSilently(ResultSet rsc) {
		if (null != rsc) {
			try {
				rsc.close();
			} catch (Exception ex) {
				// empty catch block
			}
			rsc = null;
		}
	}

	public static void closeSilently(InputStream rsc) {
		if (null != rsc) {
			try {
				rsc.close();
			} catch (Exception ex) {
				// empty catch block
			}
			rsc = null;
		}
	}

	public static void closeSilently(OutputStream rsc) {
		if (null != rsc) {
			try {
				rsc.close();
			} catch (Exception ex) {
				// empty catch block
			}
			rsc = null;
		}
	}

	public static void closeSilently(Closeable rsc) {
		if (null != rsc) {
			try {
				rsc.close();
			} catch (Exception ex) {
				// empty catch block
			}
			rsc = null;
		}
	}

	public static void closeSilently(ServerSocket rsc) {
		if (null != rsc) {
			try {
				rsc.close();
			} catch (Exception ex) {
				// empty catch block
			}
			rsc = null;
		}
	}

	public static void closeSilently(Socket rsc) {
		if (null != rsc) {
			try {
				rsc.close();
			} catch (Exception ex) {
				// empty catch block
			}
			rsc = null;
		}
	}

	public static void closeSilently(DatagramSocket rsc) {
		if (null != rsc) {
			try {
				rsc.close();
			} catch (Exception ex) {
				// empty catch block
			}
			rsc = null;
		}
	}

	public static void closeSilently(ZipFile rsc) {
		if (null != rsc) {
			try {
				rsc.close();
			} catch (Exception ex) {
				// empty catch block
			}
			rsc = null;
		}
	}

	public static void closeSilently(Scanner rsc) {
		if (null != rsc) {
			try {
				rsc.close();
			} catch (Exception ex) {
				// empty catch block
			}
			rsc = null;
		}
	}

}
