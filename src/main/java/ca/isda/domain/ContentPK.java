package ca.isda.domain;

import java.io.Serializable;

public class ContentPK implements Serializable {

	private static final long serialVersionUID = 1L;

	protected String code;
	protected String locale;

	public ContentPK() {
	}

	public ContentPK(String code, String locale) {
		this.code = code;
		this.locale = locale;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (!(other instanceof ContentPK))
			return false;

		final ContentPK pk = (ContentPK) other;
		if (!pk.getCode().equals(this.code))
			return false;
		if (!pk.getLocale().equals(this.locale))
			return false;

		return true;
	}

	public int hashCode() {
		int result;
		result = getCode().hashCode();
		result = 29 * result + getLocale().hashCode();
		return result;
	}
}
