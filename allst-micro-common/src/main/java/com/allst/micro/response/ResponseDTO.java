package com.allst.micro.response;

/**
 * @author Hutu
 * @since 2022-08-18 上午 12:26
 */
public class ResponseDTO<T> {
    /**
     * 操作状态
     */
    private int state;

    /**
     * 状态附加消息
     */
    private String message;

    /**
     * 响应内容
     */
    private T content;

    public ResponseDTO() {
    }

    public ResponseDTO(int state, String message, T content) {
        this.state = state;
        this.message = message;
        this.content = content;
    }

    public static <T> ResponseDTO<T> response(ResultCode resultCode) {
        return response(resultCode, null);
    }

    public static <T> ResponseDTO<T> response(ResultCode resultCode, T content) {
        return response(resultCode.getState(), resultCode.getMessage(), content);
    }

    public static <T> ResponseDTO<T> response(int state, String message) {
        return response(state, message, null);
    }

    public static <T> ResponseDTO<T> ofError(String message) {
        return new ResponseDTO<T>(ResultCode.INTERNAL_ERROR.getState(), message, null);
    }

    public static <T> ResponseDTO<T> ofError(int state, String message) {
        return ofError(state, message, null);
    }

    public static <T> ResponseDTO<T> ofError(int state, String message, T content) {
        return new ResponseDTO<T>(state, message, content);
    }

    public static <T> ResponseDTO<T> response(int state, String message, T content) {
        ResponseDTO<T> responseDTO = new ResponseDTO<>();
        responseDTO.setState(state);
        responseDTO.setMessage(message);
        responseDTO.setContent(content);
        return responseDTO;
    }

    public static <T> ResponseDTO<T> success() {
        return response(1, "success", null);
    }

    public boolean isSuccess() {
        return this.state == 1;
    }

    public static <T> ResponseDTO<T> success(T content) {
        return response(1, "success", content);
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ResponseDTO{" +
                "state=" + state +
                ", message='" + message + '\'' +
                ", content=" + content +
                '}';
    }
}
