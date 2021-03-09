### Usage

Create a topic

```java
import java.util.HashMap;
import com.clivern.asynq.Configs;
import com.clivern.asynq.Utils;


HashMap<String, String> map = new HashMap<String, String>();
map.put("bootstrap.servers", "localhost:9092");
Utils.createTopic("clivern", Configs.fromMap(map));
```
