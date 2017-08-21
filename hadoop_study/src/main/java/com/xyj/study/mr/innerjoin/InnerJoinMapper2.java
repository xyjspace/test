package com.xyj.study.mr.innerjoin;

import com.google.common.collect.Lists;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

/**
 * Created by banma on 2017/8/17.
 * map第二部，将enterp,user 分别与map结合，然后以mapID为key，结合结果为value，传给redeuce操作
 */
public class InnerJoinMapper2 extends Mapper<Text, Iterator<EnterpDTO>, Text, EnterpDTO> {

    @Override
    protected void map(Text key, Iterator<EnterpDTO> value, Context context) throws IOException, InterruptedException {

        List<EnterpDTO> list = Lists.newArrayList(value);
        List<EnterpDTO> enterpList = Lists.newArrayList();
        List<EnterpDTO> userList = Lists.newArrayList();
        List<EnterpDTO> mapList = Lists.newArrayList();
        for (EnterpDTO entep : list) {
            if (entep.getTable().equals("map")) {
                mapList.add(entep);
            }
            if (entep.getTable().equals("enterp")) {
                enterpList.add(entep);
            }
            if (entep.getTable().equals("manager")) {
                userList.add(entep);
            }
        }
        //遍历map，进行赋值
        for (EnterpDTO map : mapList) {
            for (EnterpDTO enterp : enterpList) {
                enterp = combineSydwCore(map, enterp);
                context.write(new Text(enterp.getMapId()), enterp);
            }
            for (EnterpDTO user : userList) {
                user = combineSydwCore(map, user);
                context.write(new Text(user.getMapId()), user);
            }
        }


    }


    /**
     * 该方法是用于相同对象不同属性值的合并，如果两个相同对象中同一属性都有值，那么sourceBean中的值会覆盖tagetBean重点的值
     * @param sourceBean    被提取的对象bean
     * @param targetBean    用于合并的对象bean
     * @return targetBean,合并后的对象
     */
    public static <T> T combineSydwCore(T sourceBean, T targetBean){
        Class sourceBeanClass = sourceBean.getClass();
        Class targetBeanClass = targetBean.getClass();

        Field[] sourceFields = sourceBeanClass.getDeclaredFields();
        Field[] targetFields = targetBeanClass.getDeclaredFields();
        for(int i=0; i<sourceFields.length; i++){
            Field sourceField = sourceFields[i];
            Field targetField = targetFields[i];
            sourceField.setAccessible(true);
            targetField.setAccessible(true);
            try {
                if( !(sourceField.get(sourceBean) == null) &&  !"serialVersionUID".equals(sourceField.getName().toString())){
                    targetField.set(targetBean,sourceField.get(sourceBean));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return targetBean;
    }


}
