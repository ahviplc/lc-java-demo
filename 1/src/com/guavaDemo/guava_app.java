package com.guavaDemo;

import com.google.common.base.*;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.*;
import com.google.common.io.Files;
import com.lc.groupListByOneWithMapDemo.PersionInfo;

import java.io.File;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * google/guava: Google core libraries for Java
 * https://github.com/google/guava
 * <p>
 * Google guava工具类的介绍和使用_java_小旋锋 的博客-CSDN博客
 * https://blog.csdn.net/wwwdc1012/article/details/82228458
 */
public class guava_app {
    public static void main(String[] args) throws Exception {
        // 1.集合的创建
        // 普通Collection的创建
        List<String> list = Lists.newArrayList();
        Set<String> set = Sets.newHashSet();
        Map<String, String> map = Maps.newHashMap();

        // 不变Collection的创建
        // 创建不可变集合 先理解什么是immutable(不可变)对象
        // 在多线程操作下，是线程安全的
        // 所有不可变集合会比可变集合更有效的利用资源
        // 中途不可改变
        ImmutableList<String> iList = ImmutableList.of("a", "b", "c");
        ImmutableSet<String> iSet = ImmutableSet.of("e1", "e2");
        ImmutableMap<String, String> iMap = ImmutableMap.of("k1", "v1", "k2", "v2");
        ImmutableList<String> immutableList = ImmutableList.of("1", "2", "3", "4");

        // 当我们需要一个map中包含key为String类型，value为List类型的时候，以前我们是这样写的
        Map<String, List<Integer>> map2 = new HashMap<String, List<Integer>>();
        List<Integer> list2 = new ArrayList<Integer>();
        list2.add(1);
        list2.add(2);
        map2.put("aa", list2);
        System.out.println(map2.get("aa"));//[1, 2]

        // use guava
        Multimap<String, Integer> map3 = ArrayListMultimap.create();
        map3.put("aa", 1);
        map3.put("aa", 2);
        System.out.println(map3.get("aa"));  //[1, 2]

        // MultiSet: 无序+可重复   count()方法获取单词的次数  增强了可读性+操作简单
        // 创建方式:  Multiset<String> set = HashMultiset.create();
        //
        // Multimap: key-value  key可以重复
        // 创建方式: Multimap<String, String> teachers = ArrayListMultimap.create();
        //
        // BiMap: 双向Map(Bidirectional Map) 键与值都不能重复
        // 创建方式:  BiMap<String, String> biMap = HashBiMap.create();
        //
        // Table: 双键的Map Map--> Table-->rowKey+columnKey+value  //和sql中的联合主键有点像
        // 创建方式: Table<String, String, Integer> tables = HashBasedTable.create();
        //
        //...等等(guava中还有很多java里面没有给出的集合类型)


        // 将集合转换为特定规则的字符串
        // 以前我们将list转换为特定规则的字符串是这样写的:
        // use java
        List<String> list3 = new ArrayList<String>();
        list3.add("aa");
        list3.add("bb");
        list3.add("cc");
        String str = "";
        for (int i = 0; i < list3.size(); i++) {
            str = str + "-" + list3.get(i);
        }
        System.out.println(str);
        // str 为-aa-bb-cc

        // use guava
        List<String> list4 = new ArrayList<String>();
        list4.add("aa");
        list4.add("bb");
        list4.add("cc");
        String result = Joiner.on("-").join(list4);
        System.out.println(result);
        // result为  aa-bb-cc


        // 2.把map集合转换为特定规则的字符串
        Map<String, Integer> map4 = Maps.newHashMap();
        map4.put("xiaoming", 12);
        map4.put("xiaohong", 13);
        String result2 = Joiner.on(",").withKeyValueSeparator("=").join(map4);
        System.out.println(result2);
        // result为 xiaoming=12,xiaohong=13


        // 3.将String转换为特定的集合
        // use java
        List<String> list5 = new ArrayList<String>();
        String a = "1-2-3-4-5-6";
        String[] strs = a.split("-");
        for (int i = 0; i < strs.length; i++) {
            list5.add(strs[i]);
        }
        System.out.println(list5);

        // use guava
        String str2 = "1-2-3-4-5-6";
        List<String> list6 = Splitter.on("-").splitToList(str2);
        //list为  [1, 2, 3, 4, 5, 6]
        System.out.println(list6);


        // 处理空串和空格
        String str3 = "1-2-3-4- 5-  6  ";
        // guava还可以使用 omitEmptyStrings().trimResults() 去除空串与空格
        List<String> list7 = Splitter.on("-").omitEmptyStrings().trimResults().splitToList(str3);
        System.out.println(list7);

        // 将String转换为map
        String str4 = "xiaoming=11,xiaohong=23";
        Map<String, String> map5 = Splitter.on(",").withKeyValueSeparator("=").split(str4);
        System.out.println(map5);


        // 4.guava还支持多个字符切割，或者特定的正则分隔
        String input = "aa.dd,,ff,,.";
        List<String> result3 = Splitter.onPattern("[.|,]").omitEmptyStrings().splitToList(input);
        System.out.println(result3);

        // 关于字符串的操作 都是在Splitter这个类上进行的
        // 判断匹配结果
        boolean result4 = CharMatcher.inRange('a', 'z').or(CharMatcher.inRange('A', 'Z')).matches('K'); //true

        // 保留数字文本  CharMatcher.digit() 已过时   retain 保留
        // String s1 = CharMatcher.digit().retainFrom("abc 123 efg"); //123
        String s1 = CharMatcher.inRange('0', '9').retainFrom("abc 123 efg"); // 123

        // 删除数字文本  remove 删除
        // String s2 = CharMatcher.digit().removeFrom("abc 123 efg");    //abc  efg
        String s2 = CharMatcher.inRange('0', '9').removeFrom("abc 123 efg"); // abc  efg


        // 5. 集合的过滤
        // 我们对于集合的过滤，思路就是迭代，然后再具体对每一个数判断，这样的代码放在程序中，难免会显得很臃肿，虽然功能都有，但是很不好看。
        // guava写法

        // 按照条件过滤
        ImmutableList<String> names = ImmutableList.of("begin", "code", "Guava", "Java");
        Iterable<String> fitered = Iterables.filter(names, Predicates.or(Predicates.equalTo("Guava"), Predicates.equalTo("Java")));
        System.out.println(fitered); // [Guava, Java]

        // 自定义过滤条件   使用自定义回调方法对Map的每个Value进行操作
        ImmutableMap<String, Integer> m = ImmutableMap.of("begin", 12, "code", 15);
        // Function<F, T> F表示apply()方法input的类型，T表示apply()方法返回类型
        Map<String, Integer> m2 = Maps.transformValues(m, new Function<Integer, Integer>() {
            public Integer apply(Integer input) {
                if (input > 12) {
                    return input;
                } else {
                    return input + 1;
                }
            }
        });
        System.out.println(m2);   //{begin=13, code=15}

        // set的交集, 并集, 差集
        HashSet setA = new HashSet();
        setA.add(1);
        setA.add(2);
        setA.add(3);
        setA.add(4);
        setA.add(5);
        HashSet setB = new HashSet();
        setB.add(4);
        setB.add(5);
        setB.add(6);
        setB.add(7);
        setB.add(8);
        Sets.SetView union = Sets.union(setA, setB);
        System.out.println("union:");
        for (Object obj : union) {
            System.out.println(obj);           //union 并集:12345867
        }

        Sets.SetView difference = Sets.difference(setA, setB);
        System.out.println("difference:");
        for (Object obj2 : difference) {
            System.out.println(obj2);        //difference 差集:123
        }

        Sets.SetView intersection = Sets.intersection(setA, setB);
        System.out.println("intersection:");
        for (Object obj3 : intersection) {
            System.out.println(obj3);  //intersection 交集:45
        }


        // map的交集，并集，差集
        HashMap<String, Integer> mapA = Maps.newHashMap();
        mapA.put("a", 1);
        mapA.put("b", 2);
        mapA.put("c", 3);

        HashMap<String, Integer> mapB = Maps.newHashMap();
        mapB.put("b", 20);
        mapB.put("c", 3);
        mapB.put("d", 4);

        MapDifference differenceMap = Maps.difference(mapA, mapB);
        differenceMap.areEqual();
        Map entriesDiffering = differenceMap.entriesDiffering();
        Map entriesOnlyLeft = differenceMap.entriesOnlyOnLeft();
        Map entriesOnlyRight = differenceMap.entriesOnlyOnRight();
        Map entriesInCommon = differenceMap.entriesInCommon();

        System.out.println(entriesDiffering);   // {b=(2, 20)}
        System.out.println(entriesOnlyLeft);    // {a=1}
        System.out.println(entriesOnlyRight);   // {d=4}
        System.out.println(entriesInCommon);    // {c=3}


//        //6.检查参数
//        //use java
//        if (list != null && list.size() > 0)
//            '' '
//        if (str != null && str.length() > 0)
//            '' '
//        if (str != null && !str.isEmpty())
//
//        //use guava
//        if (!Strings.isNullOrEmpty(str))
//
//        //use java
//        if (count <= 0) {
//            throw new IllegalArgumentException("must be positive: " + count);
//        }
//
//        //use guava
//        Preconditions.checkArgument(count > 0, "must be positive: %s", count);

//        免去了很多麻烦！并且会使你的代码看上去更好看。而不是代码里面充斥着 !=null， !=""
//
//        检查是否为空,不仅仅是字符串类型，其他类型的判断，全部都封装在 Preconditions类里，里面的方法全为静态

//        方法声明（不包括额外参数）	描述	检查失败时抛出的异常
//        checkArgument(boolean)	检查boolean是否为true，用来检查传递给方法的参数。	IllegalArgumentException
//        checkNotNull(T)	检查value是否为null，该方法直接返回value，因此可以内嵌使用checkNotNull。	NullPointerException
//        checkState(boolean)	用来检查对象的某些状态。	IllegalStateException
//        checkElementIndex(int index, int size)	检查index作为索引值对某个列表、字符串或数组是否有效。 index > 0 && index < size	IndexOutOfBoundsException
//        checkPositionIndexes(int start, int end, int size)	检查[start,end]表示的位置范围对某个列表、字符串或数组是否有效	IndexOutOfBoundsException


        //7. MoreObjects 这个方法是在Objects过期后官方推荐使用的替代品，该类最大的好处就是不用大量的重写 toString，用一种很优雅的方式实现重写，或者在某个场景定制使用
        PersionInfo persionInfo = new PersionInfo("LC", "男", 18);
        String persionInfoStr = MoreObjects.toStringHelper("persionInfo").add("age", persionInfo.getAge()).toString();
        System.out.println(persionInfoStr);
        //输出persionInfo{age=18}


        //8.强大的Ordering排序器  排序器[Ordering]是Guava流畅风格比较器[Comparator]的实现，它可以用来为构建复杂的比较器，以完成集合排序的功能。
        //    natural()   对可排序类型做自然排序，如数字按大小，日期按先后排序
        //    usingToString() 按对象的字符串形式做字典排序[lexicographical ordering]
        //    from(Comparator)    把给定的Comparator转化为排序器
        //    reverse()   获取语义相反的排序器
        //    nullsFirst()    使用当前排序器，但额外把null值排到最前面。
        //    nullsLast() 使用当前排序器，但额外把null值排到最后面。
        //    compound(Comparator)    合成另一个比较器，以处理当前排序器中的相等情况。
        //    lexicographical()   基于处理类型T的排序器，返回该类型的可迭代对象Iterable<T>的排序器。
        //    onResultOf(Function)    对集合中元素调用Function，再按返回值用当前排序器排序。

        PersionInfo persionInfo2 = new PersionInfo("LC2", "男", 19);
        PersionInfo persionInfo3 = new PersionInfo("LC3", "男", 18);
        Ordering<PersionInfo> byOrdering = Ordering.natural().nullsFirst().onResultOf(new Function<PersionInfo, String>() {
            public String apply(PersionInfo persionInfo2) {
                return String.valueOf(persionInfo2.getAge());
            }
        });
        byOrdering.compare(persionInfo2, persionInfo3);
        System.out.println(byOrdering.compare(persionInfo2, persionInfo3)); //1   persionInfo2的年龄比persionInfo3大 所以输出1


        // 9.计算中间代码的运行时间
        Stopwatch stopwatch = Stopwatch.createStarted();
        for (int i = 0; i < 300000; i++) {
            // do some thing
            //System.out.println(i);
        }
        long nanos = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        System.out.println(nanos);//TimeUnit 可以指定时间输出精确到多少时间 2115 毫秒


        // 10.文件操作
        // 以前我们写文件读取的时候要定义缓冲区，各种条件判断，各种 $%#$@#
        // 而现在我们只需要使用好guava的api 就能使代码变得简洁，并且不用担心因为写错逻辑而背锅了
        File file = new File("test.txt");
        List<String> list8 = null;
        System.out.println(list8);
        try {
            list8 = Files.readLines(file, Charsets.UTF_8);
        } catch (Exception e) {
        }
        System.out.println(list8);

        //    Files.copy(from,to);  //复制文件
        //    Files.deleteDirectoryContents(File directory); //删除文件夹下的内容(包括文件与子文件夹)
        //    Files.deleteRecursively(File file); //删除文件或者文件夹
        //    Files.move(File from, File to); //移动文件
        //    URL url = Resources.getResource("abc.xml"); //获取classpath根下的abc.xml文件url
        // Files类中还有许多方法可以用，可以多多翻阅


        // 11.guava缓存
        // guava的缓存设计的比较巧妙，可以很精巧的使用。guava缓存创建分为两种，一种是CacheLoader,另一种则是callback方式
        // CacheLoader:
        //    LoadingCache<String,String> cahceBuilder= CacheBuilder
        //            .newBuilder()
        //            .build(new CacheLoader<String, String>(){
        //                @Override
        //                public String load(String key) throws Exception {
        //                    String strProValue="hello "+key+"!";
        //                    return strProValue;
        //                }
        //            });
        //    System.out.println(cahceBuilder.apply("begincode"));  //hello begincode!
        //    System.out.println(cahceBuilder.get("begincode")); //hello begincode!
        //    System.out.println(cahceBuilder.get("wen")); //hello wen!
        //    System.out.println(cahceBuilder.apply("wen")); //hello wen!
        //    System.out.println(cahceBuilder.apply("da"));//hello da!
        //    cahceBuilder.put("begin", "code");
        //    System.out.println(cahceBuilder.get("begin")); //code

        // 声明中推荐使用get方法获取值

        //callback方式:
        //    Cache<String, String> cache = CacheBuilder.newBuilder().maximumSize(1000).build();
        //    String resultVal = cache.get("code", new Callable<String>() {
        //        public String call() {
        //            String strProValue="begin "+"code"+"!";
        //            return strProValue;
        //        }
        //    });
        //    System.out.println("value : " + resultVal); //value : begin code!

        //以上只是guava使用的一小部分，guava是个大的工具类，第一版guava是2010年发布的，每一版的更新和迭代都是一种创新。
        //
        //jdk的升级很多都是借鉴guava里面的思想来进行的

        //小结
        //代码可以在 https://github.com/whirlys/elastic-example/tree/master/guava 下载
        //
        //细节请翻看 guava 文档 https://github.com/google/guava/wiki
    }
}
