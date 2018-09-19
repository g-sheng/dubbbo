package service;

import inter.IBaseService;

public class BaseService  implements IBaseService{


     public String testService(String text) {
          //branch
          return "service3:"+text;
     }
}
