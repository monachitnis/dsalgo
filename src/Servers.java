import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Servers {
    Map<String, List<Boolean>> servers;

    Servers() {
        servers = new HashMap<>();
    }

    public String allocate(String name) {
        List<Boolean> ids;
        int id = 0;
        if (servers.containsKey(name)) {
            ids = servers.get(name);
        } else {
            ids = new ArrayList<>();
        }
        for(; id < ids.size(); id++) {
            if (!ids.get(id)) break;
        }
        if (id == ids.size()) {
            ids.add(true);
        } else {
            ids.set(id, true);
        }
        //servers.put(name, ids); // value list by reference, so change will be automatically reflected
        return name + (id+1);
    }

    public int deallocate(String name) {
        // split server name and id
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(name);
        int id;
        if (m.find()) {
            String matched = m.group();
            int start = m.start();
            id = Integer.parseInt(matched);
            String sname = name.substring(0, start);
            if (servers.containsKey(sname)) {
                List<Boolean> ids = servers.get(name);
                if (id > ids.size()) {
                    throw new IllegalArgumentException("No such server");
                }
                ids.set(id-1, false);
            } else {
                throw new IllegalArgumentException("No such server");
            }
        } else {
            throw new IllegalArgumentException("server to deallocate is not of the form name+id");
        }
        return id;
    }

}