package jp.gmo.net.validators;

import play.data.validation.Constraints;
import play.libs.F.Tuple;

public class MyValidator extends Constraints.Validator<String> {

	@Override
	public Tuple<String, Object[]> getErrorMessageKey() {
		return new Tuple<String, Object[]>("my.required.password", new String[] {});
	}

	@Override
	public boolean isValid(String val) {
		if (val == null || val.trim().equals("")) {
			return false;
		}
		return true;
	}
}
