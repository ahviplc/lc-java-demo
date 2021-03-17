package com.lc.offer.patterns.factory.factoryMethod;

import com.lc.offer.patterns.factory.commons.Phone;

/**
 * 工厂方法模式(Factory Method)
 * 和简单工厂模式中工厂负责生产所有产品相比，工厂方法模式将生成具体产品的任务分发给具体的产品工厂，其UML类图如下：
 * <p>
 * 也就是定义一个抽象工厂，其定义了产品的生产接口，但不负责具体的产品，将生产任务交给不同的派生类工厂。这样不用通过指定类型来创建对象了。
 * <p>
 * 接下来继续使用生产手机的例子来讲解该模式。
 * <p>
 * 其中和产品相关的Phone类、MiPhone类和IPhone类的定义不变。
 */

/**
 * AbstractFactory类：生产不同产品的工厂的抽象类
 */
public interface AbstractFactory {
	Phone makePhone();
}
