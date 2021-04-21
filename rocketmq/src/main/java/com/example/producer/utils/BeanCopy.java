package com.example.producer.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @User: wong
 * @Date: 2020/12/14
 * @Description:
 */
public class BeanCopy {

  public static <T> T beanCopy(Object source, Class<T> target) {
    return beanCopy(source, target, false);
  }


  public static <T> T beanCopy(Object source, Class<T> target, boolean forcedCopy) {
    T o = null;
    try {
      o = target.newInstance();
    } catch (InstantiationException | IllegalAccessException e) {
      e.printStackTrace();
    }
    beanCopy(source, o, forcedCopy);
    return  o;
  }

  public static void beanCopy(Object source, Object target) {
    beanCopy(source, target, false);
  }

  // 强制复制值
  public static void beanCopy(Object source, Object target, boolean forcedCopy) {
    //
    Assert.notNull(source, "Source must not be null");
    Assert.notNull(target, "Target must not be null");
    // 获取对象属性
    Class<?> actualEditable = target.getClass();
    PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(actualEditable);
    for (PropertyDescriptor targetPd : targetPds) {
      // setter
      Method writeMethod = targetPd.getWriteMethod();
      if (null == writeMethod)
        continue;
      PropertyDescriptor sourcePd = BeanUtils.getPropertyDescriptor(source.getClass(), targetPd.getName());
      if (null == sourcePd)
        continue;
      Method readMethod = sourcePd.getReadMethod();
      if (null == readMethod)
        continue;
      // 属性类型是否一致
      Class<?> parameterType = writeMethod.getParameterTypes()[0];
      Class<?> returnType = readMethod.getReturnType();
      boolean assignable = ClassUtils.isAssignable(parameterType, returnType);
      // 赋值
      if (!assignable && !forcedCopy)
        continue;
      try {
        if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
          readMethod.setAccessible(true);
        }
        Object value = readMethod.invoke(source);
        if (null == value)
          continue;
        if (forcedCopy && !assignable) {
          value = parseAnnotation(parameterType, returnType, value);
        }
        if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
          writeMethod.setAccessible(true);
        }
        writeMethod.invoke(target, value);
      } catch (Throwable ex) {
        throw new FatalBeanException(
                "Could not copy property '" + targetPd.getName() + "' from source to target", ex);
      }
    }

  }

  // 自定义赋值
  private static Object parseAnnotation(Class<?> parameterType, Class<?> returnType, Object value) {
    System.out.println("转换参数类型： " + returnType + " ---------》 " + parameterType);
    System.out.println("值： " + value + " 类型： " + value.getClass());
    try {
      if (String.class == parameterType) {
        if (Date.class == returnType) {
          return formatDate((Date) value);
        } else if (BigDecimal.class == returnType) {
          // 20位 约到小数点后两位
          return formatBigDecimal((BigDecimal) value);
        } else {
          return String.valueOf(value);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
    return null;
  }

  public static String formatBigDecimal(BigDecimal bigDecimal) {
    DecimalFormat decimalFormat =new DecimalFormat("#.00");
    return decimalFormat.format(bigDecimal);
  }

  public static String formatDate(String string) {
    if (StringUtils.isEmpty(string))
      return string;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = null;
    String result = null;
    try {
      date = format.parse(string);
      result = formatDate(date);
    } catch (ParseException e) {
      e.printStackTrace();
      try {
        string = string.replace("Z", " ").replace("T", " ").replace(" UTC", "");
        Date d = format.parse(string);
        return formatDate(d);
      } catch (ParseException parseException) {
        parseException.printStackTrace();
      }
      return string;
    }
    return result;
  }

  public static String formatDate(Date date) {
    if (null == date)
      return null;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    return format.format(date);
  }

  public static String setYesOrNo(String flag) {
    System.out.println("是否属性判断， 当前值： " + flag);
    if ("0".equals(flag)) {
      return "2";
    }
    if ("1".equals(flag)) {
      return "1";
    }
    return flag;
  }

}
