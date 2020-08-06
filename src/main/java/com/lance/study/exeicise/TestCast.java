package com.lance.study.exeicise;

import com.lance.study.aop.anno.TestAnno;
import com.lance.study.model.user.entity.User;
import com.lance.study.model.user.service.TestService;

import java.lang.reflect.Method;
import java.util.*;

public class TestCast  {
  public static void main(String[] args) {
      Method[] declaredMethods = Favorites.class.getDeclaredMethods();
      for(Method m : declaredMethods){
      System.out.println(m.isAnnotationPresent(TestAnno.class));
      }
      Favorites favorites = new Favorites();
      favorites.putFavorites(String.class,"aaaa");
      String favorites1 = favorites.getFavorites(String.class);
      Class<? extends Favorites> aClass = A.class.asSubclass(Favorites.class);
      System.out.println(aClass);
      System.out.println(favorites1);
      ArrayList<String> strings = new ArrayList<String>() {
          {
              add("qw");
              add("qq");
          }
      };
      HashSet<Object> objects = new HashSet<>();
      objects.add(new Object());
      favorites.putFavorites(List.class,strings);
      List<String> list = new ArrayList<>();
      List<String> list1 = Collections.checkedList(list, String.class);
    System.out.println(list.getClass());
    System.out.println(list1.getClass());
  }

}
class Favorites{
    private Map<Class<?>,Object> favorites = new HashMap<>();

    public <T> void putFavorites(Class<T> type, T instance){
        favorites.put(type,instance);
    }
	@TestAnno
    public <T> T getFavorites(Class<T> type){
        return type.cast(favorites.get(type));
    }
}
class  A extends Favorites{}
