package com.xinma.base.core.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.xinma.base.core.error.CustomError;

/**
 * 该数据传输对象用于接口间调用返回响应的通用模型
 * 
 * @author Alauda
 *
 * @date 2015年7月5日
 *
 */
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseVO<T> implements Serializable {

	private static final long serialVersionUID = -1157159645006381792L;

	private boolean success;

	@JsonProperty("data")
	private T result;

	@JsonProperty("errors")
	private List<ErrorFieldEO> errors = new ArrayList<ErrorFieldEO>();

	public boolean getSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public List<ErrorFieldEO> getErrors() {
		return errors;
	}

	public void setErrors(List<ErrorFieldEO> errors) {
		this.errors = errors;
	}

	public void addError(ErrorFieldEO errorFiledDTO) {
		errors.add(errorFiledDTO);
	}

	public void addError(CustomError error) {
		errors.add(new ErrorFieldEO(error.value(), error.description()));
	}

	public void addError(String code, String message) {
		errors.add(new ErrorFieldEO(code, message));
	}

	public ResponseVO() {
		super();
	}

	public ResponseVO(boolean success, T result, List<ErrorFieldEO> errors) {
		super();
		this.success = success;
		this.result = result;
		this.errors = errors;
	}

	/**
	 * 
	 * @param result
	 */
	public ResponseVO(T result) {
		this(true, result, null);
	}

	public ResponseVO(CustomError error) {
		super();
		this.success = false;
		this.addError(error);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((errors == null) ? 0 : errors.hashCode());
		result = prime * result + ((this.result == null) ? 0 : this.result.hashCode());
		result = prime * result + (success ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResponseVO other = (ResponseVO) obj;
		if (errors == null) {
			if (other.errors != null)
				return false;
		} else if (!errors.equals(other.errors))
			return false;
		if (result == null) {
			if (other.result != null)
				return false;
		} else if (!result.equals(other.result))
			return false;
		if (success != other.success)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ResponseVO [success=" + success + ", result=" + result + ", errors=" + errors + "]";
	}

}
