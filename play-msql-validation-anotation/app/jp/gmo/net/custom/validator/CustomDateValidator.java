package jp.gmo.net.custom.validator;

import javax.validation.ConstraintValidator;

import play.data.validation.Constraints;
import play.libs.F;

public class CustomDateValidator extends Constraints.Validator<String>
		implements ConstraintValidator<ValidateCustomDate, String> {

	final static public String message = "my.error.custom.date";

	private final static String NUMBERS = "0123456789";

	public CustomDateValidator() {

	}

	public void initialize(ValidateCustomDate constraintAnnotation) {
	}

	// Neu la true thi khong hien thi loi
	// False thi hien thi loi
	public boolean isValid(String date) {

		if (date == null || date.trim().length() == 0) {
			return true;
		} else {
			return isDate(date);
		}

	}

	public F.Tuple<String, Object[]> getErrorMessageKey() {
		return F.Tuple(message, new Object[] {});
	}

	public static boolean isDate(String date) {

		try {
			// 譯∵焚繝√ぉ繝�け
			if (date.length() != 8) {
				return false;
			}

			// 謨ｰ蛟､繝√ぉ繝�け
			char[] cr = date.toCharArray();

			for (int i = 0; i < cr.length; i++) {
				if (NUMBERS.indexOf(cr[i]) < 0) {
					return false;
				}
			}

			//
			// 譌･莉伜梛繝√ぉ繝�け
			//

			int yyyy = Integer.parseInt(date.substring(0, 4));
			int mm = Integer.parseInt(date.substring(4, 6));
			int dd = Integer.parseInt(date.substring(6, 8));

			// 隘ｿ證ｦ0000蟷ｴ縺ｮ蝣ｴ蜷医� false
			if (yyyy == 0) {
				return false;
			}

			// 譛医′1��2縺ｧ縺ｪ縺��蜷医� false
			if (mm < 1 || mm > 12) {
				return false;
			}

			// 譌･莉倥′1��1蜃ｺ縺ｪ縺��蜷医� false
			if (dd < 1 || dd > 31) {
				return false;
			}

			// 譛亥挨縺ｮ譌･莉倥メ繧ｧ繝�け
			if (mm == 4 || mm == 6 || mm == 9 || mm == 11) {
				if (dd > 30) {
					return false;
				}
			}
			// 髢丞ｹｴ縺ｮ繝√ぉ繝�け
			else if (mm == 2) {
				if ((yyyy % 400 == 0) || ((yyyy % 4 == 0) && (yyyy % 100 != 0))) {
					if (dd > 29) {
						return false;
					}
				} else if (dd > 28) {
					return false;
				}
			}

			return true;

		} catch (Exception e) {
			return false;
		}
	}
}