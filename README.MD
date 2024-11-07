# Java Sandpit

## About this project

It can be used to:

- Learn for the Oracle Certification 1Z0-829 - Java SE 17 Developer
- Use it as a reference for many Java features
- Add your own examples for your daily work as a Software Developer 

# Language Features

- Keywords
    - var is used in many classes
- Data Types
    - [DataTypesTest.java](./src/test/java/name/rhyn/sandpit/java/datatypes/DataTypesTest.java)
      boolean, byte, short, char, int, long, float, double
    - [EnumsTest.java](./src/test/java/name/rhyn/sandpit/java/datatypes/EnumsTest.java), enum
        - [ClothingSize.java](./src/main/java/name/rhyn/sandpit/java/datatypes/ClothingSize.java)
          example
    - [ArraysTest.java](./src/test/java/name/rhyn/sandpit/java/datatypes/ArraysTest.java)
    - [RecordTest.java](./src/test/java/name/rhyn/sandpit/java/datatypes/record/RecordTest.java)
      record
        - [Passenger.java](./src/main/java/name/rhyn/sandpit/java/datatypes/record/Passenger.java)
          Compact Constructor
- Reference Types
    - [StringTest.java](./src/test/java/name/rhyn/sandpit/java/referencetypes/StringTest.java)
      Constant pool, String methods, whitespaces and text block
    - [StringBuilderTest.java](./src/test/java/name/rhyn/sandpit/java/referencetypes/StringBuilderTest.java)
      StringBuilder capacity and methods
    - [DateAndTimeTest.java](./src/test/java/name/rhyn/sandpit/java/referencetypes/DateAndTimeTest.java)
      LocalDate, LocalTime, LocalDateTime, ZonedDateTime
    - [OptionalTest.java](./src/test/java/name/rhyn/sandpit/java/referencetypes/OptionalTest.java)
      Optional
- Operators
    - [OperatorsTest.java](./src/test/java/name/rhyn/sandpit/java/operators/OperatorsTest.java) all
      operators
- Control Flow
    - [ConditionalStatementTest.java](./src/test/java/name/rhyn/sandpit/java/flow/ConditionalStatementTest.java)
      if, if-else statement
    - [LoopStatementTest.java](./src/test/java/name/rhyn/sandpit/java/flow/LoopStatementTest.java)
      for, enhanced for, while, do-while, break, continue
    - [SwitchStatementTest.java](./src/test/java/name/rhyn/sandpit/java/flow/SwitchStatementTest.java)
      switch and enhanced switch
    - [InstanceOfTest.java](./src/test/java/name/rhyn/sandpit/java/flow/InstanceOfTest.java)
      instanceof
- Object-Oriented Programming OOP
    - [TopLevelTest.java](./src/test/java/name/rhyn/sandpit/java/oop/TopLevelTest.java) top level,
      inner and nested
      classes [TopLevel.java](./src/main/java/name/rhyn/sandpit/java/oop/TopLevel.java)
    - [InterfacesTest.java](./src/test/java/name/rhyn/sandpit/java/oop/interfaces/InterfacesTest.java)
        - [InterfaceVariables.java](./src/main/java/name/rhyn/sandpit/java/oop/interfaces/InterfaceVariables.java)
          interface variables
        - [InterfaceMethods.java](./src/main/java/name/rhyn/sandpit/java/oop/interfaces/InterfaceMethods.java)
          abstract methods, default method
        - [StaticMethods.java](./src/main/java/name/rhyn/sandpit/java/oop/interfaces/StaticMethods.java)
          static methods
    - [FunctionalInterfacesTest.java](./src/test/java/name/rhyn/sandpit/java/fp/FunctionalInterfacesTest.java)
        - [Play.java](./src/main/java/name/rhyn/sandpit/java/fp/Play.java) Simple
          @FunctionalInterface example
        - [Calculator.java](./src/main/java/name/rhyn/sandpit/java/fp/Calculator.java) Complex
          functional interface example
        - [Lion.java](./src/main/java/name/rhyn/sandpit/java/oop/interfaces/Lion.java) Methods from
          the Object class are not considered abstract
    - [InheritanceTest.java](./src/test/java/name/rhyn/sandpit/java/oop/inheritance/InheritanceTest.java)
        - [ParentClass.java](./src/main/java/name/rhyn/sandpit/java/oop/inheritance/ParentClass.java)
          implements
        - [ChildClass.java](./src/main/java/name/rhyn/sandpit/java/oop/inheritance/ChildClass.java)
          @Override extends
    - [SealedTest.java](./src/test/java/name/rhyn/sandpit/java/oop/inheritance/sealed/SealedTest.java)
        - [Food.java](./src/main/java/name/rhyn/sandpit/java/oop/inheritance/sealed/Food.java)
          Keywords sealed, permits and non-sealed
        - [Bicycle.java](./src/main/java/name/rhyn/sandpit/java/oop/inheritance/sealed/Bicycle.java)
- Collections Framework
    - [CollectionsTest.java](./src/test/java/name/rhyn/sandpit/java/collections/CollectionsTest.java)
      binarySearch, unmodifiableCollection, synchronizedCollection, reverseOrder, shuffle
        - [ListTest.java](./src/test/java/name/rhyn/sandpit/java/collections/ListTest.java) List,
          ArrayList, LinkedList
        - [SetTest.java](./src/test/java/name/rhyn/sandpit/java/collections/SetTest.java) Set,
          HashSet, TreeSet, SortedSet, Comparator
        - [MapTest.java](./src/test/java/name/rhyn/sandpit/java/collections/MapTest.java) Map,
          HashMap, EnumMap, TreeMap
        - [QueueTest.java](./src/test/java/name/rhyn/sandpit/java/collections/QueueTest.java) Queue,
          PriorityQueue, Deque, ArrayDeque, ArrayBlockingQueue, LinkedList
        - [StackTest.java](./src/test/java/name/rhyn/sandpit/java/collections/StackTest.java) Stack
- Functional Programming
    - [StreamTest.java](./src/test/java/name/rhyn/sandpit/java/fp/StreamTest.java) stream(),
      parallelStream(), Comparator
    - [LambdaTest.java](./src/test/java/name/rhyn/sandpit/java/fp/LambdasTest.java) Lambda
      Expressions, Comparator
- Generics
    - [GenericsTest.java](./src/test/java/name/rhyn/sandpit/java/generics/GenericsTest.java) Bounded
      type parameter, Wildcard types, Type erasure
        - [Container.java](./src/main/java/name/rhyn/sandpit/java/generics/Container.java)
          and [Key.java](./src/main/java/name/rhyn/sandpit/java/generics/Key.java)
- [VarargsTest.java](./src/test/java/name/rhyn/sandpit/java/VarargsTest.java) Varargs ...,
  @SafeVarargs
- [LocaleTest.java](./src/test/java/name/rhyn/sandpit/java/LocaleTest.java) Locale
- [FormattingTest.java](./src/test/java/name/rhyn/sandpit/java/FormattingTest.java)
  SimpleDateFormat, DateTimeFormatter, NumberFormat, DecimalFormat, MessageFormat
- [ResourcesTest.java](./src/test/java/name/rhyn/sandpit/java/ResourcesTest.java) ResourceBundle
- [DatabaseTest.java](./src/test/java/name/rhyn/sandpit/java/jdbc/DatabaseTest.java)
    - DriverManager, Update statement, Query statement, Prepared statement, Savepoint
    - AutoCloseable, try-with-resources
- Concurrency
    - [ExchangerTest.java](./src/test/java/name/rhyn/sandpit/java/concurrency/ExchangerTest.java)
    - [SemaphoreTest.java](./src/test/java/name/rhyn/sandpit/java/concurrency/SemaphoreTest.java)
    - [CounterTest.java](./src/test/java/name/rhyn/sandpit/java/concurrency/CounterTest.java) Thread
      safety, synchronized, AtomicInteger
    - [WriterLockTest.java](./src/test/java/name/rhyn/sandpit/java/concurrency/WriteLockTest.java)
      Mutual exclusion, ReentrantReadWriteLock
    - [ExecutorServiceTest.java](./src/test/java/name/rhyn/sandpit/java/concurrency/ExecutorServiceTest.java)
      ExecutorService, Future
    - [ThreadPoolsTest.java](./src/test/java/name/rhyn/sandpit/java/concurrency/ThreadPoolsTest.java)
      ForkJoinPool, ForkJoinTask
    - [BlockingQueueTest.java](./src/test/java/name/rhyn/sandpit/java/concurrency/BlockingQueueTest.java)
      BlockingQueue, ArrayBlockingQueue
    - [ConcurrentTest.java](./src/test/java/name/rhyn/sandpit/java/concurrency/ConcurrentTest.java)
      Deadlock, Starvation, Livelock, Race Condition
- [ExceptionsTest.java](./src/test/java/name/rhyn/sandpit/java/ExceptionsTest.java) Checked and
  unchecked exceptions, try catch finally,
    - [MyBusinessException.java](./src/main/java/name/rhyn/sandpit/java/exceptions/MyBusinessException.java)
- I/O
    - [FileHandlingTest.java](./src/test/java/name/rhyn/sandpit/java/io/FileHandlingTest.java) byte
      and character streams
    - [NewFileHandlingTest.java](./src/test/java/name/rhyn/sandpit/java/io/NewFileHandlingTest.java)
      Path, Paths, FileChannel, ByteBuffer
- Annotations
    - [AnnotationsTest.java](./src/test/java/name/rhyn/sandpit/java/AnnotationsTest.java) @Override,
      @Deprecated, @SuppressWarning
        - [MyAnnotation.java](./src/main/java/name/rhyn/sandpit/java/annotations/MyAnnotation.java)
          @interface

# Missing Topics

- JMS Java Module System