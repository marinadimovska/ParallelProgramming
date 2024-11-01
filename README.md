# ParallelProgramming
# **Паралелна Синхронизация: Locks vs. Atomic Operations**

Изследване на ефективността на **Locks** и **Atomic Operations** в многопоточни Java програми.

## **Описание на Проекта**
Изпълнени са два модела за паралелна синхронизация:
- **LockCounter** – използва `ReentrantLock` за контрол на достъпа.
- **AtomicCounter** – използва `AtomicInteger` за безопасна работа без заключвания.

## **Основни Цели**
- **Измерване на производителността** при различен брой потоци.
- **Сравнение на техниките** и формулиране на препоръки.

## **Структура на Проекта**
- **CounterTest.java** – основен клас за тестове
- **LockCounter.java** – брояч с `ReentrantLock`
- **AtomicCounter.java** – брояч с `AtomicInteger`

## **Изпълнение на Тестовете**
1. **Инсталирайте JDK 8+** (напр. от [Oracle](https://www.oracle.com/java/)).
2. **Стартирайте тестовете** – изпълнете `CounterTest` и сравнете времената за изпълнение на двата брояча.

## **Резултати**
- **AtomicCounter** е по-ефективен при висок брой потоци.
- **LockCounter** поддържа стабилност, но е по-бавен при голямо натоварване.

## **Заключение**
**Atomic Operations** са оптимални при висока конкурентност и ниска сложност, докато **Locks** са подходящи за по-сложни операции.

## **Лиценз**
Проектът е с отворен код под лиценза MIT.
