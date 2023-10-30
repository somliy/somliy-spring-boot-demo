package top.somliy.websocket.websocket.dto;

import java.util.Date;

/**
 * 类名： @ClassName MessageDTO 消息
 * 创建人：@author zhao dong
 * 类描述：@Description: 消息
 * 创建时间: 2023/10/16 13:40
 */
public class MessageDTO {
    /**
     * id
     */
    private String id;

    /**
     * 初次推送时间
     */
    private Date pushTime;

    /**
     * 消息数据
     */
    private String msgData;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getPushTime() {
        return pushTime;
    }

    public void setPushTime(Date pushTime) {
        this.pushTime = pushTime;
    }

    public String getMsgData() {
        return msgData;
    }

    public void setMsgData(String msgData) {
        this.msgData = msgData;
    }


    @Override
    public String toString() {
        return "MessageDTO{" + "id='" + id + '\'' + ", pushTime=" + pushTime + ", msgData='" + msgData + '\'' + '}';
    }
}
