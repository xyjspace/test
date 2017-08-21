package com.xyj.study.mr.innerjoin;

import com.google.common.collect.Lists;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by banma on 2017/8/17.
 */
public class InnerJoinReducer extends Reducer<Text, EnterpDTO, Text, EnterpDTO> {


    @Override
    protected void reduce(Text key, Iterable<EnterpDTO> values, Context context) throws IOException, InterruptedException {
        List<EnterpDTO> list = Lists.newArrayList(values);
        List<EnterpDTO> enterpList = Lists.newArrayList();
        List<EnterpDTO> mapList = Lists.newArrayList();
        for (EnterpDTO entep : list) {
            if (entep.getTable().equals("map")) {
                mapList.add(entep);
            }
            if (entep.getTable().equals("enterp")) {
                enterpList.add(entep);
            }
        }
        for (EnterpDTO map : mapList) {
            for (EnterpDTO enterp : enterpList) {
                enterp = combineSydwCore(map, enterp);
                context.write(new Text(enterp.getMapId()), enterp);
            }
        }


    }

    /**
     * 该方法是用于相同对象不同属性值的合并，如果两个相同对象中同一属性都有值，那么sourceBean中的值会覆盖tagetBean重点的值
     *
     * @param sourceBean 被提取的对象bean
     * @param targetBean 用于合并的对象bean
     * @return targetBean, 合并后的对象
     */
    public static <T> T combineSydwCore(T sourceBean, T targetBean) {
        Class sourceBeanClass = sourceBean.getClass();
        Class targetBeanClass = targetBean.getClass();

        Field[] sourceFields = sourceBeanClass.getDeclaredFields();
        Field[] targetFields = targetBeanClass.getDeclaredFields();
        for (int i = 0; i < sourceFields.length; i++) {
            Field sourceField = sourceFields[i];
            Field targetField = targetFields[i];
            sourceField.setAccessible(true);
            targetField.setAccessible(true);
            try {
                if (!(sourceField.get(sourceBean) == null) && !"serialVersionUID".equals(sourceField.getName().toString())) {
                    targetField.set(targetBean, sourceField.get(sourceBean));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return targetBean;
    }

}
