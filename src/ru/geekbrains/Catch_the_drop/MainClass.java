package ru.geekbrains.Catch_the_drop;

import java.util.Arrays;

public class MainClass {

    final int size = 10000000;
    final int h = size / 2;

    public static void main(String[] args){
        MainClass homeworkMultyThread = new MainClass();
        homeworkMultyThread.First();
        homeworkMultyThread.Second();
    }


    private void First(){
            final int size = 10000000;
            final int h = size / 2;
            float[] arr = new float[size];
            Arrays.fill(arr, 1.0f);

            long a = System.currentTimeMillis();

            for (int i = 0; i < arr.length; i++) {
                arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));

            }
            long b = System.currentTimeMillis();

            System.out.println("время завершения первого задания  " + (b - a));


        }

        private void Second() {

            final int size = 10000000;
            final int n = size / 2;
            float[] arr2 = new float[size];

            Arrays.fill(arr2, 1.0f);
            long iteration = System.currentTimeMillis();

            float[] a1 = new float[size];

            float[] a2 = new float[size];

            System.arraycopy(arr2, 0, a1, 0, n);
            System.arraycopy(arr2, 0, a2, 0, n);

            long split = System.currentTimeMillis();
            System.out.println("Время разделения" + (split - iteration));


            Thread firstThread = new Thread(() -> this.initialization(a1, 1));
            Thread secondThread = new Thread(() -> this.initialization(a2, 2));

            try {
                firstThread.join();
                secondThread.join();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());

            }


            long splice = System.currentTimeMillis();
            System.arraycopy(a1, 0, arr2, 0, n);
            System.arraycopy(a2, 0, arr2, n, n);
            long finish = System.currentTimeMillis();
            System.out.println("Время склейки " + (finish - splice));

        }

        public void initialization ( float[] arr, int number){
            long start = System.currentTimeMillis();
            for (int i = 0; i < arr.length; i++) {
                arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
            long end = System.currentTimeMillis();
            System.out.println("Время выполнния потока равно  " + (end - start));
        }

    }
