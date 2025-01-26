package deque;

public class Comparator<T> {
    //创建一个比较器，以自己的方式比较两个数字（不论是从小到大还是从大到小亦或是其他）
    //我们只需要给予这个比较器一个比较标准即可
    //我们只在意比较的结果，至于比较的过程，留给每一个比较器对象就可以了
    //我下面的这段代码就浅薄了，呆滞地比大小，缺失了通用性
   public int compare(int a, int b){
       if(a > b){
           return 1;
       }
       else{
           return 0;
       }
   }
   }
}
