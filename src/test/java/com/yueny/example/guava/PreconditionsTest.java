package com.yueny.example.guava;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * 针对  guava Preconditions 的测试用例
 */
public class PreconditionsTest {
    @Test
    public void testCheckArgument(){
        DemoVO demoVO = DemoVO.builder()
                .age(1L)
                .name("demo bane")
                .history(Arrays.asList("a", "b", "c"))
                .build();

        // 非空对象校验
        Preconditions.checkArgument(demoVO != null, "入参非法!");

        // 空对象校验
        try {
            Preconditions.checkArgument(null != null, "入参非法!");
        }catch (IllegalArgumentException e){
            Assert.assertEquals(e.getMessage(), "入参非法!");
        }

        Preconditions.checkArgument(StringUtils.isNotEmpty(demoVO.getName()), "name为空!");
    }

    @Test
    public void testCheckNotNull(){
        Preconditions.checkNotNull("", "name为null");
        Preconditions.checkNotNull("a", "name为null");

        try {
            Preconditions.checkNotNull(null, "name为null");
        }catch (NullPointerException e){
            Assert.assertEquals(e.getMessage(), "name为null");
        }
    }

    @Test
    public void testCheckState(){
        DemoVO demoVO = DemoVO.builder()
                .age(1L)
                .name("demo bane")
                .history(Arrays.asList("a", "b", "c"))
                .build();

        // 表达式为true不抛异常
        Preconditions.checkState(demoVO.getHistory().size() < 5, "list size 不能大于"+5);

        try {
            Preconditions.checkState(Arrays.asList("a", "b", "c", "a", "b", "c").size() < 5, "list size 不能大于"+5);
        }catch (IllegalStateException e){
            Assert.assertEquals(e.getMessage(), "list size 不能大于5");
        }
    }

    @Test
    public void testCheckPositionIndex(){
        DemoVO demoVO = DemoVO.builder()
                .age(1L)
                .name("demo bane")
                .history(Arrays.asList("a", "b", "c"))
                .build();

        int size = demoVO.getHistory().size();
        System.out.println("size：" + size);

        int index = 0;
        Preconditions.checkPositionIndex(index, size - 1, "index： " + index + " 不在 list中， List size为：" + size);

        index = 1;
        Preconditions.checkPositionIndex(index, size - 1, "index： " + index + " 不在 list中， List size为：" + size);

        index = 2;
        Preconditions.checkPositionIndex(index, size - 1, "index： " + index + " 不在 list中， List size为：" + size);

        try {
            index = 3;
            Preconditions.checkPositionIndex(index, size - 1, "index： " + index + " 不在 list中， List size为：" + size + ".");
        }catch (IndexOutOfBoundsException e){
            Assert.assertEquals(e.getMessage(), "index： 3 不在 list中， List size为：3. (3) must not be greater than size (2)");
        }

        try {
            index = 4;
            Preconditions.checkPositionIndex(index, size - 1, "index： " + index + " 不在 list中， List size为：" + size + ".");
        }catch (IndexOutOfBoundsException e){
            Assert.assertEquals(e.getMessage(), "index： 4 不在 list中， List size为：3. (4) must not be greater than size (2)");
        }
    }

}
