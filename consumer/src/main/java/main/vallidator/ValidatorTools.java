package main.vallidator;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ValidatorTools {

    /**
     *
     * @param obj  待验证的requestDTO
     * @param originMap  必填参数Map
     * @return map :isSuccess=true 成功
     *               isSuccess=false 失败
     */

    public static Map validatorCheck(Object obj, Map<String,Object> originMap) {
        Map<String,Object> resultMap = new HashMap<>();

        //object 转 map
        if (obj == null) {
            return null;
        }
        Map<String, Object> paramsMap = new HashMap<>();
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            try {
                paramsMap.put(field.getName(), field.get(obj));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                resultMap.put(field.getName(),e.getMessage());
                return resultMap;
            }
            field.setAccessible(false);
        }

        //验证map
        for (Object key : originMap.keySet()) {
            //字符串
            Object value=paramsMap.get(key);
            if(value instanceof String){
                if(paramsMap.get(key)==null || paramsMap.get(key).equals("")){
                    resultMap.put(key.toString(),originMap.get(key));
                }
            }else{
                if(paramsMap.get(key)==null){
                    resultMap.put(key.toString(),originMap.get(key));
                }
            }
        }
        if(resultMap.size()>0){
            resultMap.put("isSuccess",false);
        }else{
            resultMap.put("isSuccess",true);
        }
        return resultMap;
    }

    public static void main(String[] args) {
        Map<String,Object> originMap = new HashMap();
//        originMap.put("id", "id不能为空");
//        originMap.put("name", "name不能为空");
//        originMap.put("id2", "id2不能为空");
//        originMap.put("key", "key不能为空");
        OriginParams requestParams = new OriginParams();
//        requestParams.setKey("key");
        requestParams.setId(1);
        requestParams.setToken("gfhsfefrrfefeff");

       Map map= validatorCheck(requestParams,originMap);
       System.out.print(JSON.toJSONString(map));

    }

}
