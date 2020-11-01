package shujujiegou;

import android.util.Log;

/**
 * 顺序队列
 */
public class Queue {
    final int MAXQSIZE = 100;
    int front;
    int rear;
    ElemType[] data;
    public Queue(){
        front = 0;
        rear = 0;
        data = new ElemType[MAXQSIZE];
    }

    //入队
    public void EnQueue(ElemType e){
        data[rear] = e;
        rear = (rear+1)%MAXQSIZE;
    }

    //出队
    public ElemType DeQueue(){

        ElemType e = new ElemType();
        if(!IsEmpty()){
            e = data[front];
            front = (front+1)%MAXQSIZE;
        }
        return e;
    }

    //是否队空
    public boolean IsEmpty(){
        return front==rear;
    }

    //是否队满
    public boolean IsOverFlow(){
        return (rear+1)%MAXQSIZE == front;
    }

    public ElemType getOneElem(){
        return data[front];
    }

    //输出所有元素
    public void showElem(){
        for (int i = front;i!=rear;i=(i+1)%MAXQSIZE){
            Log.d("Queue",data[i].data);
        }
    }
}
