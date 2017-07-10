# zlikun-spring-transaction
Spring事务机制研究

#### 事务特性
- A 原子性(Atomicity)
- C 一致性(Consistency)
- I 隔离性(Isolation)
- D 持久性(Durability)

#### 五种状态
- 活动状态  
事务在执行时的状态叫活动状态
- 部分提交状态  
事务中最后一条语句被执行后的状态叫部分提交状态
- 失败状态  
事务不能正常执行的状态叫失败状态
- 提交状态  
事务在部分提交后，将往硬盘上写入数据，当最后一条信息写入后的状态叫提交状态。进入提交状态的事务就成功完成了
- 中止状态  
事务回滚并且数据库已经恢复到事务开始执行前的状态叫中止状态

#### 传播机制(PROPAGATION)
- PROPAGATION_REQUIRE，如果当前没有事务， 就新建一个事务， 如果已经存在一个事务中，加入到这个事务中。该项是Spring的默认配置项。
- PROPAGATION_SUPPORTS，持当前事务，如果当前没有事务，就以非事务方式执行。
- PROPAGATION_MANDATORY，使用当前的事务，如果当前没有事务，就抛出异常。
- PROPAGATION_REQUIRES_NEW，新建事务，如果当前存在事务，把当前事务挂起。
- PROPAGATION_NOT_SUPPORTED，以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。
- PROPAGATION_NEVER，非事务方式执行，如果当前存在事务，则抛出异常。
- PROPAGATION_NESTED，如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则执行与 PROPAGATION_REQUIRED 类似的操作。

#### 隔离级别(Isolation)
- Read Uncommitted，未提交读，所有事务都可以看到其他未提交事务的执行结果，读取未提交的数据，也被称之为脏读(Dirty Read)。
- Read Committed，提交读，事务只能看见已经提交事务所做的改变。
- Repeatable Read，可重复读，是MySQL的默认事务隔离级别，它确保同一事务的多个实例在并发读取数据时，会看到同样的数据行。但可能出现幻读(Phantom Read)，幻读指当用户读取某一范围的数据行时，另一个事务又在该范围内插入了新行，当用户再读取该范围的数据行时，会发现有新的"幻影" 行。
- Serializable，串行化，是最高的隔离级别，它通过强制事务排序，使之不可能相互冲突，从而解决幻读问题。简言之，它是在每个读的数据行上加上共享锁。在这个级别，可能导致大量的超时现象和锁竞争。
- 隔离级别解决问题
    - 脏读(Drity Read)，某个事务已更新一份数据，另一个事务在此时读取了同一份数据，由于某些原因，前一个RollBack了操作，则后一个事务所读取的数据就会是不正确的。
    - 不可重复读(Non-repeatable read)，在一个事务的两次查询之中数据不一致，这可能是两次查询过程中间插入了一个事务更新的原有的数据。
    - 幻读(Phantom Read)，在一个事务的两次查询中数据笔数不一致，例如有一个事务查询了几列(Row)数据，而另一个事务却在此时插入了新的几列数据，先前的事务在接下来的查询中，就会发现有几列数据是它先前所没有的。
- MySQL中四种隔离级别

| 隔离级别 | 脏读 | 不可重复读 | 幻读 |
| :--- | :---: | :---: | :---: |
| Read uncommitted | O | O | O |
| Read committed | X | O | O |
| Repeatable read | X | X | O |
| Serializable | X | X | X|
