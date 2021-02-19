package org.javaboy.vhr.utils;

import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class JsonUtils {

    public static String TransMapToString(Map<String, Object> todo) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(todo);
    }

    public static Map<String, Object> TransStringToMap(String todo) {
        return null;
    }

    public static void do1() throws IOException {
        String s = "{\"id\": 1,\"name\": \"小明\",\"array\": [\"1\", \"2\"]}";
        ObjectMapper mapper = new ObjectMapper();
        Tmp student = mapper.readValue(s, Tmp.class);
        String json = mapper.writeValueAsString(student);
        System.out.println(json);
        System.out.println(student.toString());
    }

    static class Tmp {
        private int id;
        private String name;
        private String sex;
        private ArrayList<String> array;
    }

    public static void do2() throws IOException {
        Map map = new HashMap<>(16);
        String s = "{\"id\": 1,\"name\": \"小明\",\"array\": [\"1\", \"2\"]," +
                "\"test\":\"I'm test\",\"base\": {\"major\": \"物联网\",\"class\": \"3\"}}";
        ObjectMapper mapper = new ObjectMapper();
        map = mapper.readValue(s, map.getClass());
        //获取id
        Integer studentId = (Integer) map.get("id");
        System.out.println(studentId);
        //获取数据
        ArrayList list = (ArrayList) map.get("array");
        System.out.println(Arrays.toString(list.toArray()));
        //新增加的字段可以很方便的处理
        String test = (String) map.get("test");
        System.out.println(test);
        //不存在的返回null
        String notExist = (String) map.get("notExist");
        System.out.println(notExist);
        //嵌套的对象获取
        Map base = (Map) map.get("base");
        String major = (String) base.get("major");
        System.out.println(major);

    }

    public static void do3() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        //以下是对象转化为Json
        ObjectNode root = mapper.createObjectNode();
        root.putArray("array");
        ArrayNode arrayNode = (ArrayNode) root.get("array");
        arrayNode.add("args1");
        arrayNode.add("args2");
        root.put("name", "小红");
        String json = mapper.writeValueAsString(root);
        System.out.println("使用树型模型构建的json:" + json);
        //以下是树模型的解析Json
        String s = "{\"id\": 1,\"name\": \"小明\",\"array\": [\"1\", \"2\"]," +
                "\"test\":\"I'm test\",\"nullNode\":null,\"base\": {\"major\": \"物联网\",\"class\": \"3\"}}";
        //读取rootNode
        JsonNode rootNode = mapper.readTree(s);
        //通过path获取
        System.out.println("通过path获取值：" + rootNode.path("name").asText());
        //通过JsonPointer可以直接按照路径获取
        JsonPointer pointer = JsonPointer.valueOf("/base/major");
        JsonNode node = rootNode.at(pointer);
        System.out.println("通过at获取值:" + node.asText());
        //通过get可以取对应的value
        JsonNode classNode = rootNode.get("base");
        System.out.println("通过get获取值：" + classNode.get("major").asText());

        //获取数组的值
        System.out.print("获取数组的值：");
        JsonNode arrayNode2 = rootNode.get("array");
        for (int i = 0; i < arrayNode2.size(); i++) {
            System.out.print(arrayNode2.get(i).asText() + " ");
        }
        System.out.println();
        //path和get方法看起来很相似，其实他们的细节不同，get方法取不存在的值的时候，会返回null。而path方法会
        //返回一个"missing node"，该"missing node"的isMissingNode方法返回值为true，如果调用该node的asText方法的话，
        // 结果是一个空字符串。
        System.out.println("get方法取不存在的节点，返回null:" + (rootNode.get("notExist") == null));
        JsonNode notExistNode = rootNode.path("notExist");
        System.out.println("notExistNode的value：" + notExistNode.asText());
        System.out.println("isMissingNode方法返回true:" + notExistNode.isMissingNode());

        //当key存在，而value为null的时候，get和path都会返回一个NullNode节点。
//        System.out.println(rootNode.get("nullNode") instanceof NullNode);
//        System.out.println(rootNode.path("nullNode") instanceof NullNode);
    }
}
