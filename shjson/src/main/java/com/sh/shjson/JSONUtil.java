package com.sh.shjson;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class JSONUtil {
    private String json;

    public JSONUtil(String json) {
        this.json = json;
    }

    public <T> T getObject(Class<T> clazz, String name) {
        try {
            T t = parseObj(clazz, json, name);
            return t;
        } catch (Exception e) {
            Log.w("json", e.getMessage(), e);
            e.printStackTrace();
        }
        return null;
    }

    private Class[] class2;
    private int indexClass = -1;

    /**
     * 获取属性带集合的对象  传两个泛型
     *
     * @param clazz
     * @param class2
     * @param name
     * @param <T>
     * @return
     */
    public <T> T getObject(Class<T> clazz, String name, Class... class2) {
        this.class2 = class2;
        try {
            T t = parseObj(clazz, json, name);
            return t;
        } catch (Exception e) {
            Log.w("json", e.getMessage(), e);
            e.printStackTrace();
        }
        return null;
    }


    public <T> List<T> getList(Class<T> clazz, String name) {
        try {
            List<T> list = parseArray(clazz, json, name);
            return list;
        } catch (Exception e) {
            Log.w("json", e.getMessage(), e);
        }
        return Collections.emptyList();
    }

    /**
     * 获取二维数组  传两个泛型
     *
     * @param clazz
     * @param class2
     * @param name
     * @param <T>
     * @return
     */
    public <T> List<T> getList(Class<T> clazz, String name, Class... class2) {
        this.class2 = class2;
        try {
            List<T> list = parseArray(clazz, json, name);

            return list;
        } catch (Exception e) {
            e.printStackTrace();
            Log.w("json", e.getMessage(), e);
        }
        return Collections.emptyList();
    }

    public <T> T parseObj(Class<T> clazz, String json, String column) throws JSONException, IllegalAccessException, InstantiationException {
        JSONTokener to = new JSONTokener(json);
        Object oo = to.nextValue();
        if (!(oo instanceof JSONObject))
            return null;
        JSONObject o = (JSONObject) oo;
        if (column == null)
            return parse(o, clazz);
        String[] names = column.split("\\.");
        if (names.length > 1) {
            String[] cNames = new String[names.length - 1];
            System.arraycopy(names, 0, cNames, 0, names.length - 1);
            column = names[names.length - 1];
            for (String name : cNames) {
                o = o.getJSONObject(name);
            }
        }
        T t = convert(clazz, o, column);
        return t;
    }

    public <T> List<T> parseArray(Class<T> clazz, String json, String column) throws JSONException, IllegalAccessException, InstantiationException {
        JSONTokener to = new JSONTokener(json);
        JSONArray o = null;
        Object obj = to.nextValue();

        if (obj instanceof JSONArray) {
            o = (JSONArray) obj;
            if (column != null) return Collections.emptyList();
        } else if (obj instanceof JSONObject) {
            JSONObject oo = (JSONObject) obj;
            if (column == null) return Collections.emptyList();
            String[] names = column.split("\\.");
            if (names.length > 1) {
                String[] cNames = new String[names.length - 1];
                System.arraycopy(names, 0, cNames, 0, names.length - 1);
                column = names[names.length - 1];
                for (String name : cNames) {
                    oo = oo.getJSONObject(name);
                }
            }
            if (!oo.has(column)) return Collections.emptyList();
            Object array = oo.get(column);
            if (array instanceof JSONArray)
                o = oo.getJSONArray(column);
            else return Collections.emptyList();
        } else
            return Collections.emptyList();
        ArrayList<T> list = new ArrayList<T>(o.length());
        for (int i = 0; i < o.length(); i++) {
            T t = convertFromArray(clazz, o, i);
            list.add(t);
        }

        return list;
    }

    private <T> T parse(JSONObject obj, Class<T> clazz) throws IllegalAccessException, InstantiationException, JSONException {
        T t = clazz.newInstance();
        Field[] fs = clazz.getDeclaredFields();
        for (Field f : fs) {
            f.setAccessible(true);
            if (f.getType().equals(List.class)) {
                if (!obj.isNull(f.getName())) {
                    Object array = obj.get(f.getName());
                    if (array instanceof JSONArray) {

                        JSONArray o = obj.getJSONArray(f.getName());
                        indexClass=0;
                        for(int i =0;i<class2.length;i++){
                            if(class2[i] ==clazz ){
                                indexClass =i+1;
                            }
                        }
                        if(class2.length>indexClass){
                            getChildrenList(t, f, class2[indexClass], o);
                        }



                    }
                }
            } else {
                Object o = convert(f.getType(), obj, f.getName());
                if (o != null)
                    f.set(t, o);
            }
        }
        return t;
    }

    public <T> void getChildrenList(T t, Field f, Class<T> class2, JSONArray jsonArray) throws IllegalAccessException, JSONException, InstantiationException {
        List<T> children = new ArrayList<T>();
        for (int i = 0; i < jsonArray.length(); i++) {
            children.add(convertFromArray(class2, jsonArray, i));
        }
        f.set(t, children);
    }


    @SuppressWarnings("unchecked")
    private <T> T convert(Class<T> clazz, JSONObject obj, String column) throws IllegalAccessException, InstantiationException, JSONException {
        if (!obj.has(column)) return null;
        if (clazz.equals(Integer.TYPE) || clazz.equals(Integer.class)) {

            Integer i = 0;
            Object o = obj.get(column);
            if (o instanceof Number) {
                Number n = (Number) o;
                i = n.intValue();
            }
            return (T) i;
        } else if (clazz.equals(Double.TYPE) || clazz.equals(Double.class)) {
            Double d = 0D;
            Object o = obj.get(column);
            if (o instanceof Number) {
                Number n = (Number) o;
                d = n.doubleValue();
            }
            return (T) d;
        } else if (clazz.equals(Long.TYPE) || clazz.equals(Long.class)) {
            Long l = 0L;
            Object o = obj.get(column);
            if (o instanceof Number) {
                Number n = (Number) o;
                l = n.longValue();
            }
            return (T) l;
        } else if (clazz.equals(Boolean.TYPE) || clazz.equals(Boolean.class)) {
            Boolean b = false;
            Object o = obj.get(column);
            if (o instanceof Number) {
                Number n = (Number) o;
                int i = n.intValue();
                b = i != 0;
            } else if (o instanceof Boolean) {
                b = (Boolean) o;
            }
            return (T) b;
        } else if (clazz.equals(String.class) || clazz.equals(Object.class)) {
            String s = obj.getString(column);
            return (T) ("null".equalsIgnoreCase(s) ? "" : s);
        } else if (clazz.equals(Timestamp.class)) {
            String s = obj.getString(column);
            Object o = parseTimestamp(s);
            return (T) ("null".equals(o) ? "" : o);
        } else if (clazz.equals(java.sql.Date.class)) {
            String s = obj.getString(column);
            Object o = parseSqlDate(s);
            return (T) ("null".equals(o) ? "" : o);
        }

        return parse(obj.getJSONObject(column), clazz);
    }

    @SuppressWarnings({"unchecked"})
    private <T> T convertFromArray(Class<T> clazz, JSONArray obj, int index) throws JSONException, IllegalAccessException, InstantiationException {
        if ((index + 1) > obj.length()) return null;
        if (clazz.equals(Integer.TYPE) || clazz.equals(Integer.class)) {

            Integer i = 0;
            Object o = obj.get(index);
            if (o instanceof Number) {
                Number n = (Number) o;
                i = n.intValue();
            }
            return (T) i;
        } else if (clazz.equals(Double.TYPE) || clazz.equals(Double.class)) {
            Double d = 0D;
            Object o = obj.get(index);
            if (o instanceof Number) {
                Number n = (Number) o;
                d = n.doubleValue();
            }
            return (T) d;
        } else if (clazz.equals(Long.TYPE) || clazz.equals(Long.class)) {
            Long l = 0L;
            Object o = obj.get(index);
            if (o instanceof Number) {
                Number n = (Number) o;
                l = n.longValue();
            }
            return (T) l;
        } else if (clazz.equals(Boolean.TYPE) || clazz.equals(Boolean.class)) {
            Boolean b = false;
            Object o = obj.get(index);
            if (o instanceof Number) {
                Number n = (Number) o;
                int i = n.intValue();
                b = i != 0;
            } else if (o instanceof Boolean) {
                b = (Boolean) o;
            }
            return (T) b;
        } else if (clazz.equals(String.class) || clazz.equals(Object.class)) {
            String s = obj.getString(index);
            return (T) ("null".equalsIgnoreCase(s) ? "" : s);
        } else if (clazz.equals(Timestamp.class)) {
            String s = obj.getString(index);
            Object o = parseTimestamp(s);
            return (T) ("null".equals(o) ? "" : o);
        } else if (clazz.equals(java.sql.Date.class)) {
            String s = obj.getString(index);
            Object o = parseSqlDate(s);
            return (T) ("null".equals(o) ? "" : o);
        }
        return parse(obj.getJSONObject(index), clazz);
    }

    DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.CHINA);
    DateFormat dateTimeFormat = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);

    private Timestamp parseTimestamp(String time) {
        try {
            Date o = dateTimeFormat.parse(time);
            return new Timestamp(o.getTime());
        } catch (ParseException e) {
            Log.e("json", e.getMessage(), e);
        }
        return null;
    }

    private java.sql.Date parseSqlDate(String time) {
        try {
            Date o = dateTimeFormat.parse(time);
            return new java.sql.Date(o.getTime());
        } catch (ParseException e) {
            Log.e("json", e.getMessage(), e);
        }
        return null;
    }
}
