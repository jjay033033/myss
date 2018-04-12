package top.lmoon.util;

import javax.servlet.http.HttpServletRequest;

public class ParamUtil {
	public static String[] getArrayParameter(HttpServletRequest request, String paramName) {
		return request.getParameterValues(paramName);
	}

	public static String getAttribute(HttpServletRequest request, String name) {
		return ParamUtil.getAttribute(request, name, false);
	}

	public static String getAttribute(HttpServletRequest request, String name, boolean emptyStringsOK) {
		String temp = (String) request.getAttribute(name);
		if (temp != null) {
			if (temp.equals("") && !emptyStringsOK) {
				return null;
			}
			return temp;
		}
		return null;
	}

	public static boolean getBooleanAttribute(HttpServletRequest request, String name) {
		String temp = (String) request.getAttribute(name);
		if (temp != null && temp.equals("true")) {
			return true;
		}
		return false;
	}

	public static boolean getBooleanParameter(HttpServletRequest request, String name) {
		return ParamUtil.getBooleanParameter(request, name, false);
	}

	public static boolean getBooleanParameter(HttpServletRequest request, String name, boolean defaultVal) {
		String temp = request.getParameter(name);
		if ("true".equals(temp) || "on".equals(temp)) {
			return true;
		}
		if ("false".equals(temp) || "off".equals(temp)) {
			return false;
		}
		return defaultVal;
	}

	public static double getDoubleParameter(HttpServletRequest request, String name, double defaultNum) {
		String temp = request.getParameter(name);
		if (temp != null && !temp.equals("")) {
			double num = defaultNum;
			try {
				num = Double.parseDouble(temp);
			} catch (Exception ignored) {
				// empty catch block
			}
			return num;
		}
		return defaultNum;
	}

	public static int getIntAttribute(HttpServletRequest request, String name, int defaultNum) {
		String temp = (String) request.getAttribute(name);
		if (temp != null && !temp.equals("")) {
			int num = defaultNum;
			try {
				num = Integer.parseInt(temp);
			} catch (Exception ignored) {
				// empty catch block
			}
			return num;
		}
		return defaultNum;
	}

	public static int getIntParameter(HttpServletRequest request, String name, int defaultNum) {
		String temp = request.getParameter(name);
		if (temp != null && !temp.equals("")) {
			int num = defaultNum;
			try {
				num = Integer.parseInt(temp);
			} catch (Exception ignored) {
				// empty catch block
			}
			return num;
		}
		return defaultNum;
	}

	public static int[] getIntParameters(HttpServletRequest request, String name, int defaultNum) {
		String[] paramValues = request.getParameterValues(name);
		if (paramValues == null) {
			return null;
		}
		if (paramValues.length < 1) {
			return new int[0];
		}
		int[] values = new int[paramValues.length];
		for (int i = 0; i < paramValues.length; ++i) {
			try {
				values[i] = Integer.parseInt(paramValues[i]);
				continue;
			} catch (Exception e) {
				values[i] = defaultNum;
			}
		}
		return values;
	}

	public static long getLongAttribute(HttpServletRequest request, String name, long defaultNum) {
		String temp = (String) request.getAttribute(name);
		if (temp != null && !temp.equals("")) {
			long num = defaultNum;
			try {
				num = Long.parseLong(temp);
			} catch (Exception ignored) {
				// empty catch block
			}
			return num;
		}
		return defaultNum;
	}

	public static long getLongParameter(HttpServletRequest request, String name, long defaultNum) {
		String temp = request.getParameter(name);
		if (temp != null && !temp.equals("")) {
			long num = defaultNum;
			try {
				num = Long.parseLong(temp);
			} catch (Exception ignored) {
				// empty catch block
			}
			return num;
		}
		return defaultNum;
	}

	public static long[] getLongParameters(HttpServletRequest request, String name, long defaultNum) {
		String[] paramValues = request.getParameterValues(name);
		if (paramValues == null) {
			return null;
		}
		if (paramValues.length < 1) {
			return new long[0];
		}
		long[] values = new long[paramValues.length];
		for (int i = 0; i < paramValues.length; ++i) {
			try {
				values[i] = Long.parseLong(paramValues[i]);
				continue;
			} catch (Exception e) {
				values[i] = defaultNum;
			}
		}
		return values;
	}

	public static String getParameter(HttpServletRequest request, String name) {
		return ParamUtil.getParameter(request, name, false);
	}

	public static String getParameter(HttpServletRequest request, String name, boolean emptyStringsOK) {
		String temp = request.getParameter(name);
		if (temp != null) {
			if (temp.equals("") && !emptyStringsOK) {
				return null;
			}
			return temp;
		}
		return null;
	}

	public static String getParameter(HttpServletRequest request, String paramName, String defaultValue) {
		String temp = request.getParameter(paramName);
		if (temp != null) {
			return temp;
		}
		return defaultValue;
	}
}
