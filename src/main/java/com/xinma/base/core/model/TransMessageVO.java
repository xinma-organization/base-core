package com.xinma.base.core.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xinma.base.core.util.KeyHelper;

/**
 * url参数包装类
 * 
 * @author Hoctor
 *
 */
public class TransMessageVO {

	/**
	 * 摘要算法名
	 */
	private final static String algorithmName = "SHA-256";

	private final static ObjectMapper mapper = new ObjectMapper();

	@JsonProperty("m")
	private String metadata;

	@JsonProperty("d")
	private String digest;

	@JsonProperty("k")
	private int keyIndex;

	@JsonProperty("t")
	private Date time;

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public int getKeyIndex() {
		return keyIndex;
	}

	public void setKeyIndex(int keyIndex) {
		this.keyIndex = keyIndex;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	/**
	 * 校验该签名是否有效
	 * 
	 * @return 摘要正确返回true,摘要失败返回false
	 * @throws NoSuchAlgorithmException
	 */
	public boolean validate() throws NoSuchAlgorithmException {
		String digestKey = KeyHelper.getSpecifiedKey(this.keyIndex);
		String digest = new String(Base64.getEncoder()
				.encode(MessageDigest.getInstance(algorithmName).digest((this.metadata + digestKey).getBytes())));

		if (this.digest.equals(digest)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 将JSON字符串metadata转换承认那个 T 类型实体类对象
	 * 
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public <T> T getDecodeMetadata(Class<T> clazz) throws Exception {
		return mapper.readValue(this.metadata, clazz);
	}

	/**
	 * 将Base64编码字符串解码并转换成TransMessageVO对象
	 * 
	 * @param encodeStr
	 * @return
	 * @throws Exception
	 */
	public static TransMessageVO decode(String encodeStr) throws Exception {
		String decodeStr = new String(Base64.getUrlDecoder().decode(encodeStr));
		return mapper.readValue(decodeStr, TransMessageVO.class);
	}

	/**
	 * 将metadata数据封装成TransMessageVO对象，并编码成Base64类型字符串
	 * 
	 * @param metadata
	 *            要编码的数据实体类
	 * @return Base64编码字符串
	 * @throws Exception
	 */
	public static String encodeMetadata(Object metadata) throws Exception {

		TransMessageVO transPackage = new TransMessageVO();

		transPackage.setKeyIndex(KeyHelper.randomDigestKeyIndex());
		transPackage.setMetadata(mapper.writeValueAsString(metadata));
		String digestKey = KeyHelper.getSpecifiedKey(transPackage.getKeyIndex());
		String digest = Base64.getEncoder().encodeToString(MessageDigest.getInstance(TransMessageVO.algorithmName)
				.digest((transPackage.getMetadata() + digestKey).getBytes()));

		transPackage.setDigest(digest);
		transPackage.setTime(new Date());

		return Base64.getUrlEncoder().encodeToString(mapper.writeValueAsString(transPackage).getBytes());
	}
}
