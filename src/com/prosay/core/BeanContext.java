package com.prosay.core;
/**
 * @ClassName: BeanContext
 * @Description: Bean容器操作  针对注解实现
 * @author Jame
 * @date 2017年12月15日 下午10:22:00
 */

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.prosay.core.annotation.Autowired;
import com.prosay.core.annotation.Bean;
import com.prosay.core.annotation.Controller;
import com.prosay.core.annotation.RequestMapping;
import com.prosay.core.entity.BeanEntity;
import com.prosay.core.entity.Property;
import com.prosay.core.util.XmlUtil;

public class BeanContext {
    /**
     * 扫描到的所有类 必备 key 是完整的类名   value的class的实例
     */
    private Map<String, Class<?>> classes;
    //存放单例的bean对象 key 是id
    private Map<String, Object> beans = new HashMap<String, Object>();
    //存放多例bean原型 多例
    private Map<String, BeanEntity> prototypes = new HashMap<String, BeanEntity>();
    //存放控制器的实例 key 是类名
    private Map<String, Object> controllers = new HashMap<String, Object>();
    //存放路径映射 key是路径  value method对象
    private Map<String, Method> reqs = new HashMap<String, Method>();

    /**
     * 防止空构造new出来
     * @param classes
     */
    public BeanContext(Map<String, Class<?>> classes) {
        this.classes = classes;
        init();
    }

    /**
     *
     * @Title: init
     * @Description: 容器初始化
     * @param  //设定文件
     * @return void    返回类型
     * @throws
     */
    @SuppressWarnings("unchecked")
    private void init() {
        XmlUtil.log("**********PROSAY容器初始化开始***********");
        try {
            Iterator<String> itro = classes.keySet().iterator();
            while (itro.hasNext()) {
                String key = itro.next();
                Class clazz = classes.get(key);
                if (clazz.isAnnotationPresent(Bean.class)) {//bean注解
                    initBean(clazz);
                } else if (clazz.isAnnotationPresent(Controller.class)) {//控制器注解
                    initController(clazz);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        XmlUtil.log("**********PROSAY容器初始化结束***********");
    }

    /**
     *
     * @Title: initBean
     * @Description: Bean实例初始化操作
     * @param @param clazz
     * @param @return    设定文件
     * @return Object    返回类型
     * @throws
     */
    private Object initBean(Class clazz) {
        Object obj = null;
        try {

            Bean bean = (Bean) clazz.getAnnotation(Bean.class);
            if (bean.single()) {//Spring的默认作用域，容器里拥有唯一的Bean实例
                String id = bean.value();//Bean的唯一标识
                if (beans.containsKey(id)) {//如果已经被初始化了 就直接返回
                    return beans.get(id);
                }
                obj = clazz.newInstance();
                Field[] fields = clazz.getDeclaredFields();
                fieldAutowire(fields, obj);
                //放入单例容器
                beans.put(bean.value(), obj);
            } else {//针对getBean()请求容器都会创建一个bean实例
                BeanEntity beanEntity = new BeanEntity();
                beanEntity.setClazz(clazz);
                beanEntity.setId(bean.value());
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    //判断是否需要自动注入
                    if (field.isAnnotationPresent(Autowired.class)) {
                        Property prop = new Property();
                        Autowired auto = (Autowired) field.getAnnotation(Autowired.class);
                        String propName = auto.value();
                        //从Bean容器中去取对应的实例
                        prop.setRef(propName);
                        prop.setField(field);
                        beanEntity.addProperty(prop);
                    }
                }
                prototypes.put(bean.value(), beanEntity);
            }
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return obj;
    }

    /**
     *
     * @Title: initController
     * @Description: 通过控制器类的原型初始化控制器容器
     * @param @param clazz    设定文件
     * @return void    返回类型
     * @throws
     */
    private void initController(Class clazz) {
        try {
            String className = clazz.getName();//类的全路径

            boolean flag = false;//标记这个类是否具备方法
            String pPath = "";//类的上映射路径
            //如果类名上面标注了requestMapping注解，取出对应的值拼到前面
            if (clazz.isAnnotationPresent(RequestMapping.class)) {
                //取到类名上面的路径
                pPath = ((RequestMapping) clazz.getAnnotation(RequestMapping.class)).value();
            }
            //获取控制器的所有方法
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                //方法必须具备RequestMapping注解来映射
                if (method.isAnnotationPresent(RequestMapping.class)) {
                    //拿上方法的路径拼接起来
                    //放入路径映射
                    reqs.put(pPath + ((RequestMapping) method.getAnnotation(RequestMapping.class)).value(), method);
                    flag = true;
                }
            }
            if (flag) {//如果这个类上有方法
                Object obj = clazz.newInstance();
                Field[] fields = clazz.getDeclaredFields();
                fieldAutowire(fields, obj);
                controllers.put(className, obj);
            }

        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void fieldAutowire(Field[] fields, Object obj) throws IllegalAccessException {
        for (Field field : fields) {
            //判断是否需要自动注入
            if (field.isAnnotationPresent(Autowired.class)) {
                Autowired auto = (Autowired) field.getAnnotation(Autowired.class);
                String propName = auto.value();
                //从Bean容器中去取对应的实例
                Object o = getBean(propName);
                if (o == null) {
                    //拿到这个属性的Class
                    Class clazzF = field.getType();
                    initBean(clazzF);
                    o = getBean(propName);
                }
                field.setAccessible(true);
                field.set(obj, o);
            }
        }
    }

    /**
     *
     * @Title: getBean
     * @Description: 提供给外部获取Bean实例
     * @param @param beanName
     * @param @return    设定文件
     * @return Object    返回类型
     * @throws
     */
    public Object getBean(String beanName) {
        //先从单例容器中拿
        Object obj = beans.get(beanName);
        if (obj == null) {
            //通过原型去new
            BeanEntity b = prototypes.get(beanName);

            try {
                Class<?> clazz = b.getClazz();
                obj = clazz.newInstance();//单例的实例对象
                for (Property prop : b.getProps()) {
                    Field field = prop.getField();
                    //开放修改权限 （针对私有变量）
                    field.setAccessible(true);
                    field.set(obj, getBean(prop.getRef()));
                }
            } catch (Exception e) {

            }

        }
        return obj;
    }

    /**
     *
     * @Title: getExecMethod
     * @Description: 通过映射路径直接得到对应的方法
     * @param @param path
     * @param @return    设定文件
     * @return Method    返回类型
     * @throws
     */
    public Method getExecMethod(String path) {
        return reqs.get(path);
    }

    /**
     *
     * @Title: getController
     * @Description: 获取控制器的实例
     * @param @param className
     * @param @return    设定文件
     * @return BaseController    返回类型
     * @throws
     */
    public BaseController getController(String className) {
        return (BaseController) controllers.get(className);
    }
}
