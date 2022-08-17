package com.allst.micro.Util;

import net.sf.cglib.beans.BeanCopier;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 属性拷贝工具类
 *
 * @author Hutu
 * @since 2022-08-18
 */
public class ConverUtil {
    /**
     * 将源对象属性值拷贝到目标对象对应的属性中
     *
     * @param source 源对象
     * @param target 目标对象
     * @param <S>    泛型
     * @param <T>    泛型
     *
     * @return 结果
     */
    public static <S, T> T convert(S source, T target) {
        if (source == null || target == null) {
            return null;
        }
        final BeanCopier copier = BeanCopier.create(source.getClass(), target.getClass(), false);
        T result = target;
        copier.copy(source, result, null);
        return result;
    }

    /**
     * 拷贝属性，方法重载
     */
    public static <S, T> T convert(S source, Class<T> target) {
        try {
            return convert(source, target.newInstance());
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <S, T> List<T> convertList(List<S> source, Class<T> dest) {
        return convertList(source, dest, null);
    }

    public static <S, T> List<T> convertList(List<S> source, Class<T> dest, ConvertCallback<S, T> callback) {
        if (source == null) {
            return null;
        }
        return source.stream().map(s -> {
            T result = null;
            try {
                result = dest.newInstance();
                convert(s, result);
                if (callback != null) {
                    callback.callback(s, result);
                }
            } catch (InstantiationException | IllegalAccessException e) {

            }
            return result;
        }).collect(Collectors.toList());
    }

    public interface ConvertCallback<S, D> {
        void callback(S source, D dest);
    }
}
