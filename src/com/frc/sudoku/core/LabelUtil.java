package com.frc.sudoku.core;

public class LabelUtil {
	public static String createLabel(int value, boolean arr[], boolean highlight[], boolean showTips) {
		String result = "<html>";
		if (value > 0) {
			String num = String.format("%d", value);
			if (highlight != null && highlight[value]) {
				result += "<font size='10' color='red'><b>" + num + "</b></font>";
			} else {
				result += "<font size='10'>" + num + "</font>";
			}
		} else if (showTips && arr != null){
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
						if (highlight != null && highlight[k]) {
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
}
