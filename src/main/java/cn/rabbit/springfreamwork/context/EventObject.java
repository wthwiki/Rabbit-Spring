package cn.rabbit.springfreamwork.context;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventObject {
    public Object source;

    public Object getSource() {
        return source;
    }
}
