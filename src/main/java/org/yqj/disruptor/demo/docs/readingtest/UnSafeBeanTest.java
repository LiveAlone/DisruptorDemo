package org.yqj.disruptor.demo.docs.readingtest;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * @author yaoqijun on 2017-10-31.
 */
public class UnSafeBeanTest {
    public static void main(String[] args) throws Exception{
        Unsafe UNSAFE = getUnsafe();
//        System.out.println(UNSAFE.arrayIndexScale(Object[].class));
    }

    private static void testArray() throws Exception{
        Unsafe UNSAFE = getUnsafe();

        int[] data = new int[10];
        System.out.println(Arrays.toString(data));

        // 当前JVM 默认的数组偏移量, 同样的 还可以获取 Object 的 Offset
        long byteArrayBaseOffset = UNSAFE.arrayBaseOffset(int[].class);
        System.out.println(byteArrayBaseOffset);

        // 获取数组 Item 的大小
        long arrayIndexScale = UNSAFE.arrayIndexScale(int[].class);
        System.out.println(arrayIndexScale);

        System.out.println(Arrays.toString(data));

        // set data 直接通过 memory
        UNSAFE.putInt(data, byteArrayBaseOffset, 10);
        System.out.println(Arrays.toString(data));

        UNSAFE.putInt(data, byteArrayBaseOffset + arrayIndexScale * 2, 10);
        System.out.println(Arrays.toString(data));
    }

    private static Unsafe getUnsafe() throws Exception{
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe UNSAFE = (Unsafe) theUnsafe.get(null);
        System.out.println(UNSAFE);
        return UNSAFE;
    }
}
