package com.xt.sentense.constant;
/**
 * json返回消息的封装
 * @author XiangTao
 *
 */
public class Res {
	public static final int SUCCESS = 1001;
	public static final int ERROR = 1002;
	
	private int code;
	private String msg;
	private Object data;
	
	public static Res NEW(){
		return new Res();
	}
	
	public Res code(int code){
		this.setCode(code);
		return this;
	}
	
	public Res msg(String msg){
		this.setMsg(msg);
		return this;
	}
	
	public Res data(Object data){
		this.setData(data);
		return this;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "Res [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}
}
