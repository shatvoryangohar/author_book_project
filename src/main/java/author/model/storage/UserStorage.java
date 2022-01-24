package author.model.storage;

import author.model.User;
import author.model.util.FileUtil;

import java.util.HashMap;
import java.util.Map;

public class UserStorage {
    Map<String, User> userMap = new HashMap<>();

    public void addUser(User user) {
        userMap.put(user.getEmail(), user);
        FileUtil.serializeUzerMap(userMap);
    }


    public void print() {
        for (User value : userMap.values()) {
            System.out.println(value);
        }
    }

    public User getByEmail(String email) {
        return userMap.get(email);

    }

    public void initData() {
        Map<String, User> userMapFromFile = FileUtil.deserializeUserMap();
        if (userMapFromFile != null) {
            userMap = userMapFromFile;
        }
    }
}

