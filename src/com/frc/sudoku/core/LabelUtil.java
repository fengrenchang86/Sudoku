package com.frc.sudoku.core;

public class LabelUtil {
	public static String createLabel(int value, boolean arr[], boolean highlight[]) {
		if (highlight == null) {
			return createLabel(value, arr);
		}
		String result = "<html>";
		if (value > 0) {
			String num = String.format("%d", value);
			if (highlight[value]) {
				result += "<font size='10' color='red'><b>" + num + "</b></font>";
			} else {
				result += "<font size='10'>" + num + "</font>";
			}
			
		} else {
			result += "<font size='3'>";
			int n = 3;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					int k = i * n + j + 1;
					if (arr[k]) {
						String num = "";
						if (k <= 9) {
							num = String.format("%d", k);
						} else {
							num = String.format("%c", (char)(k-10+'A'));
						}
						if (highlight[k]) {
							result += "<font color='red'><b>";
							result += num;
							result += "</b></font>";
						} else {
							result += num;
						}
					} else {
						String num = String.format("&#160;");
						result += num;
					}
				}
				result += "<br/>";
			}
			result += "</font>";
		}
		result += "</html>";
		return result;
	}
	public static String createLabel(int value, boolean arr[]) {
		String result = "<html>";
		if (value > 0) {
			String num = String.format("%d", value);
			result += "<font size='10'>" + num + "</font>";
		} else {
			result += "<font size='3'>";
			int n = 3;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					int k = i * n + j + 1;
					if (arr[k]) {
						String num = "";
						if (k <= 9) {
							num = String.format("%d", k);
						} else {
							num = String.format("%c", (char)(k-10+'A'));
						}
						result += num;
					} else {
						String num = String.format("&#160;");
						result += num;
					}
				}
				result += "<br/>";
			}
			result += "</font>";
		}
		result += "</html>";
		return result;
	}
}
