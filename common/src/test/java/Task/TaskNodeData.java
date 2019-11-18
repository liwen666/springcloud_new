package Task;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 任务链节点
 *
 * @author: looyii
 * @Date: 2018/8/29 15:24
 * @Description:
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class TaskNodeData {

    private String key;
    private String type;
    private String text;
    private boolean pause;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean getPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }


    @Override
    public String toString() {
        return "TaskNodeData{" +
                "key='" + key + '\'' +
                ", type='" + type + '\'' +
                ", text='" + text + '\'' +
                ", pause='" + pause + '\'' ;
    }
}
